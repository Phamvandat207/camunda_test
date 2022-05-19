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
    private String userTaskId;
    private Map<String, Object> variables;
}
