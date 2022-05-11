package com.example.workflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {
    private final RuntimeService runtimeService;

    public HomeController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("create")
    public String startProcess(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-project-process");
        return processInstance.getProcessInstanceId();
    }
}
