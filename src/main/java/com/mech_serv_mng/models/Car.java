package com.mech_serv_mng.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customer", "services"})
@EqualsAndHashCode(exclude = {"customer", "services"})
@Getter
@Setter
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String regNum;

    private String brand;

    private String model;

    private String color;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ServiceOrdered> servicesOrdered;
}
