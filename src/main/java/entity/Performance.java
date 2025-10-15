package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double plannedExpenditure;
    private Double actualExpenditure;
    private Integer plannedSchedule;
    private Integer actualSchedule;

    @OneToOne
    private Project project;
}
