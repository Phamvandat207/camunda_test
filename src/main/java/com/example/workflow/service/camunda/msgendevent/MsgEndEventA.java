package com.example.workflow.service.camunda.msgendevent;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MsgEndEventA implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int dayOffs = (int) execution.getVariable("offDays");

        execution.getProcessEngineServices()
                .getRuntimeService()
                .createMessageCorrelation("sentOffDaysRequest")
                .processInstanceBusinessKey(execution.getProcessBusinessKey())
                .setVariable("offDays", dayOffs)
                .correlate();
    }
}
