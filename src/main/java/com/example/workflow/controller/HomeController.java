package com.example.workflow.controller;

import com.example.workflow.model.CamundaProcessDTO;
import com.example.workflow.model.MsgToEngineDTO;
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
  public String startProcess(@RequestBody CamundaProcessDTO camundaProcessDTO) {
    ProcessInstance processInstance =
        runtimeService.startProcessInstanceByKey(camundaProcessDTO.getProcessKey());
    String processInstanceId = processInstance.getProcessInstanceId();
    return taskService
        .createTaskQuery()
        .processInstanceId(processInstanceId)
        .singleResult()
        .getId();
  }

  @PostMapping("create-process-instance-with-business-key")
  public CamundaProcessDTO startProcessWithBusinessKey(
      @RequestBody CamundaProcessDTO camundaProcessDTO) {
    ProcessInstance execute =
        runtimeService
            .createProcessInstanceByKey(camundaProcessDTO.getProcessKey())
            .businessKey(camundaProcessDTO.getBusinessKey())
            .setVariables(camundaProcessDTO.getVariables())
            .execute();
    String taskId = "Không có Task nào!";
    if (taskService
            .createTaskQuery()
            .processInstanceId(execute.getProcessInstanceId())
            .singleResult()
            .getId()
        != null) {
      taskId =
          taskService
              .createTaskQuery()
              .processInstanceId(execute.getProcessInstanceId())
              .singleResult()
              .getId();
    }
    return CamundaProcessDTO.builder()
        .processDefKey(execute.getProcessDefinitionId())
        .processInstanceId(execute.getProcessInstanceId())
        .businessKey(execute.getBusinessKey())
        .userTaskId(taskId)
        .build();
  }

  @PostMapping("void-create")
  public void voidStartProcess(@RequestBody CamundaProcessDTO camundaProcessDTO) {
    runtimeService
        .createProcessInstanceByKey(camundaProcessDTO.getProcessKey())
        .setVariables(camundaProcessDTO.getVariables())
        .execute();
  }

  @PostMapping("/comp-task")
  public CamundaProcessDTO completeTask(@RequestBody CamundaProcessDTO camundaProcessDTO) {
    Map<String, Object> variables = camundaProcessDTO.getVariables();
    String processInstanceId =
        taskService
            .createTaskQuery()
            .taskId(camundaProcessDTO.getUserTaskId())
            .singleResult()
            .getProcessInstanceId();
    taskService.complete(camundaProcessDTO.getUserTaskId(), variables);

    String taskId = "Không có Task nào!";
    if (taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult() != null) {
      taskId =
          taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult().getId();
    }
    return CamundaProcessDTO.builder().userTaskId(taskId).build();
  }

  @PostMapping("sent-msg-to-Engine")
  public void sentMsgToEngine(@RequestBody MsgToEngineDTO msgToEngineDTO) {
    runtimeService
        .createMessageCorrelation(msgToEngineDTO.getMsgName())
        .processInstanceBusinessKey(msgToEngineDTO.getBusinessKey())
        .setVariables(msgToEngineDTO.getVariables())
        .correlate();
  }
}
