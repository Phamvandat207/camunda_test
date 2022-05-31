package com.example.workflow.service.camunda.msgendevent;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("LogOffDays")
public class LogOffDays implements JavaDelegate {
  private final RepositoryService repositoryService;

  public LogOffDays(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String name =
        repositoryService
            .createProcessDefinitionQuery()
            .processDefinitionId(execution.getProcessDefinitionId())
            .singleResult()
            .getName();
    if (execution.getVariable("offDays").toString().matches("^[A-z]")){

    }
    int offDays = (int) execution.getVariable("offDays");
    StringBuilder stringBuilder = new StringBuilder(name);
    stringBuilder.append("::");
    stringBuilder.append("Số ngày nghỉ muốn xin là: ");
    stringBuilder.append(offDays);
    log.info(stringBuilder.toString());
  }
}
