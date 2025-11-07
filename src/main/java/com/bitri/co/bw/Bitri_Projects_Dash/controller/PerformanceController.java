package com.bitri.co.bw.Bitri_Projects_Dash.controller;

import com.bitri.co.bw.Bitri_Projects_Dash.entity.Performance;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.PerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.PerformanceServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PerformanceController {

    private final PerformanceServiceIntf performanceService;

    @GetMapping
    public List<PerformanceResponse> getAll() {
        return performanceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponse> getById(@PathVariable Long id) {
        return performanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PerformanceResponse> create(@RequestBody PerformanceRequest requestDTO) {
        try {
            PerformanceResponse response = performanceService.save(requestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResponse> update(@PathVariable Long id, @RequestBody PerformanceRequest requestDTO) {
        return performanceService.update(id, requestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        performanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
