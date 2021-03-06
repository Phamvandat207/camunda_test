package com.example.workflow.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamundaProcessDTO {
    private String processKey;
    private String processDefKey;
    private String processInstanceId;
    private String businessKey;
    private String userTaskId;
    private Map<String, Object> variables;
}
