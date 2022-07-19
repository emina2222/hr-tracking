package com.hr.homework.repository;

import com.hr.homework.entity.Employee;
import com.hr.homework.entity.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * Creates filters based on params. Those filters are applied in findAll() method which returns only matching records.
 * If certain param is null, specification is ignored.
 */

@Component
public class EmployeeSpecification {

    public static Specification<Employee> employeeNameEquals(String name) {
        return (root, query, builder) ->
                name == null ?
                        builder.conjunction() :
                        builder.equal(root.get("name"), name);
    }

    public static Specification<Employee> employeeTeamEquals(Team team) {
        return (root, query, builder) ->
            team == null ?
                    builder.conjunction() :
                    builder.equal(root.get("team"), team);

    }

    public static Specification<Employee> employeeTeamLeaderEquals(String teamLead) {
        return (root, query, builder) ->
                teamLead == null ?
                        builder.conjunction() :
                        builder.equal(root.get("teamLead"), teamLead);
    }

}
