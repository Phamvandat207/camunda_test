package com.example.workflow.service.camunda.housechore;


import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Slf4j
@Service("WeatherCheckingServiceDelegate")
public class WeatherCheckingServiceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("[WeatherCheckingServiceDelegate]");
        Random random = new SecureRandom();
        int i = random.nextInt(2);
        if (i == 1){
            delegateExecution.setVariable("rain", 1);
            log.info("Đang mưa!");
        } else {
            delegateExecution.setVariable("rain", 0);
            log.info("Không mưa!");

        }
    }
}
