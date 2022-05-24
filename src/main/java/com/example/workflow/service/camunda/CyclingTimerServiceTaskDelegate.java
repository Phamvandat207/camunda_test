package com.example.workflow.service.camunda;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CyclingTimerServiceTaskDelegate")
public class CyclingTimerServiceTaskDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("This is CyclingTimerServiceTaskDelegate ");
        int count = (int)delegateExecution.getVariable("count");
        count++;
        delegateExecution.setVariable("count", count);
    }
}
