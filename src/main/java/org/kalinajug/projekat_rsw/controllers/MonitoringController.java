package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.services.MonitoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringController {
    private final MonitoringService monitoringService;

    @GetMapping("/termin/{terminId}/statistika")
    public MonitoringService.Statistika statistika(@PathVariable Long terminId) {
        return monitoringService.statistika(terminId);
    }

    @GetMapping("/termin/{terminId}")
    public List<MonitoringService.RedStudenta> pregled(@PathVariable Long terminId) {
        return monitoringService.pregled(terminId);
    }
}