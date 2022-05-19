package com.example.workflow.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseChoreProcessStarterDTO {
    private String area;
    private boolean changeArea;
    private boolean cleanOtherRoom;
    private boolean cleanBedRoom;
    private String timer;
}
