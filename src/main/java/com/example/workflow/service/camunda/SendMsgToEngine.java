package com.example.workflow.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("SendMsgToEngine")
public class SendMsgToEngine implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
