package com.example.workflow.controller;

import com.example.workflow.model.CamundaProcessDTO;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public HomeController(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @PostMapping("create")
    public String startProcess(@RequestBody CamundaProcessDTO camundaProcessDTO){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(camundaProcessDTO.getProcessKey());
        String processInstanceId = processInstance.getProcessInstanceId();
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
    }

    @PostMapping("/comp-task")
    public String completeTask(@RequestBody CamundaProcessDTO camundaProcessDTO){
        Map<String, Object> variables = camundaProcessDTO.getVariables();
        String processInstanceId = taskService.createTaskQuery().taskId(camundaProcessDTO.getUserTaskId()).singleResult().getProcessInstanceId();
        taskService.complete(camundaProcessDTO.getUserTaskId(), variables);
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
    }


}
