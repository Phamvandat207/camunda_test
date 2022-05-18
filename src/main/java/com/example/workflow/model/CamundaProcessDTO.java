package com.example.workflow.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamundaProcessDTO {
    private String processKey;
    private String userTaskId;
}
