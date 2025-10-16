package controller;

import entity.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.intf.PerformanceServiceIntf;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
@RequiredArgsConstructor
@CrossOrigin("*")

public class PerformanceController {

    private final PerformanceServiceIntf performanceService;

    @GetMapping
    public List<Performance> getAll() {
        return performanceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Performance> getById(@PathVariable Long id) {
        return performanceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Performance create(@RequestBody Performance performance) {
        return performanceService.save(performance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Performance> update(@PathVariable Long id, @RequestBody Performance performance) {
        performance.setId(id);
        return ResponseEntity.ok(performanceService.save(performance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        performanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
