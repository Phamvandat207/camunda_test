package com.example.workflow.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgToEngineDTO {
    private String msgName;
    private String businessKey;
    private Map<String, Object> variables;
}
