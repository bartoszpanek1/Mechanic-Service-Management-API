package com.mech_serv_mng.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"car"})
@EqualsAndHashCode(exclude = {"car"})
@Getter
@Setter
public class ServiceOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private String description;

    private LocalDate accepted;

    private LocalDate deadline;

    private Boolean completed;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Car car;
}
