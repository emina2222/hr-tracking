package com.hr.homework.utils;

import com.hr.homework.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeSearchParams {

    private String name;

    private Team team;

    private String teamLead;

    @Override
    public String toString(){
        return new StringBuilder("Name: ").append(name)
                .append(" Team: ").append(team)
                .append(" Team lead: ").append(teamLead)
                .toString();
    }

}
