package controller;


import entity.Risk;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.intf.RiskServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/risks")
@RequiredArgsConstructor
@CrossOrigin("*")

public class RiskController {

    private final RiskServiceIntf riskService;

    @GetMapping
    public List<Risk> getAll() {
        return riskService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Risk> getById(@PathVariable Long id) {
        return riskService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Risk create(@RequestBody Risk risk) {
        return riskService.save(risk);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Risk> update(@PathVariable Long id, @RequestBody Risk risk) {
        risk.setId(id);
        return ResponseEntity.ok(riskService.save(risk));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        riskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
