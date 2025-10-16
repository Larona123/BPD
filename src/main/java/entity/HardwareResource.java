package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HardwareResource {
    @Id
    private Long id;
    private String serialNumber;
    private String vendor;
    private Boolean underWarranty;
    private LocalDate allocationDate;
    private LocalDate returnDate;
}
