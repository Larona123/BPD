package com.bitri.co.bw.Bitri_Projects_Dash.controller;


import com.bitri.co.bw.Bitri_Projects_Dash.entity.Risk;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskRequest;
import com.bitri.co.bw.Bitri_Projects_Dash.model.RiskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bitri.co.bw.Bitri_Projects_Dash.services.intf.RiskServiceIntf;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/risks")
@RequiredArgsConstructor
@CrossOrigin("*")

public class RiskController {

    private final RiskServiceIntf riskService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<RiskResponse>> getRisksByProjectId(@PathVariable Long projectId) {
        List<RiskResponse> risks = riskService.getRisksByProjectId(projectId);
        return ResponseEntity.ok(risks);
    }

    @GetMapping("/{riskId}")
    public ResponseEntity<RiskResponse> getRiskById(@PathVariable Long riskId) {
        Optional<RiskResponse> optionalResponse = riskService.getRiskById(riskId);
        return optionalResponse
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<RiskResponse> create(@RequestBody RiskRequest riskRequest) {
        RiskResponse createdRisk = riskService.save(riskRequest);
        return new ResponseEntity<>(createdRisk, HttpStatus.CREATED);
    }

    @PutMapping("/{riskId}")
    public ResponseEntity<RiskResponse> update(@PathVariable Long riskId, @RequestBody RiskRequest riskRequest) {
        RiskResponse updatedRisk = riskService.updateRisk(riskId, riskRequest);
        return ResponseEntity.ok(updatedRisk);
    }

    @DeleteMapping("/{riskId}")
    public ResponseEntity<Void> delete(@PathVariable Long riskId) {
        riskService.delete(riskId);
        return ResponseEntity.noContent().build();
    }
}
