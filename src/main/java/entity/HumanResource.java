package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HumanResource {

    @Id
    private Long id;
    private String role;
    private Double hourlyRate;
    private Double hoursAllocated;
    private LocalDate startDate;
    private LocalDate endDate;
}
