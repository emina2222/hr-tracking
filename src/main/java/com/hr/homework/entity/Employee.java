package com.hr.homework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="employee")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personalId;

    @Column(name="name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Team team;

    @Column(name="team_lead")
    private String teamLead;

}
