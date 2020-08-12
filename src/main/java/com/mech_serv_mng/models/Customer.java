package com.mech_serv_mng.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cars"})
@EqualsAndHashCode(exclude = {"cars"})
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String identNum;

    private String name;

    private String familyName;

    private String phoneNumber;

    private String email;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Car> cars = new ArrayList<>();
}
