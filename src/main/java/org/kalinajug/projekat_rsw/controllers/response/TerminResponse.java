package org.kalinajug.projekat_rsw.controllers.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TerminResponse {
    private Long id;
    private LocalDateTime pocetak;
    private LocalDateTime kraj;
    private Long rokId;
    private List<Long> grupeIds;
}