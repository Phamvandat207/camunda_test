package com.example.workflow.service.camunda.housechore;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CleaningServiceDelegate")
public class CleaningServiceDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[CleaningServiceDelegate]");
        log.info("Đang dọn dẹp");
    }
}
