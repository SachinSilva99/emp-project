package com.sachin.employeeregister.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "department")
public class Department implements  SuperEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
