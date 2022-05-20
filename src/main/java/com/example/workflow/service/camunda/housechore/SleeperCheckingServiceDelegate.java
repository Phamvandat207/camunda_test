package com.example.workflow.service.camunda.housechore;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Slf4j
@Service("SleeperCheckingServiceDelegate")
public class SleeperCheckingServiceDelegate implements JavaDelegate{
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[SleeperCheckingServiceDelegate]");
        Random random = new SecureRandom();
        int i = random.nextInt(2);
        if (i == 1){
            log.info("Có người đang ngủ!");
        }
    }
}
