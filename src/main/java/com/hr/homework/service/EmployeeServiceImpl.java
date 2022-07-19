package com.hr.homework.service;

import com.hr.homework.entity.Employee;
import com.hr.homework.repository.EmployeeRepository;
import com.hr.homework.repository.EmployeeSpecification;
import com.hr.homework.utils.EmployeeSearchParams;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository crudRepository;

    public EmployeeServiceImpl(EmployeeRepository crudRepository){
        this.crudRepository=crudRepository;
    }

    @Override
    public void save(Employee employee){
        crudRepository.save(employee);
    }

    @Override
    public void delete(Employee employee){
        crudRepository.deleteById(employee.getPersonalId());
    }

    @Override
    public Employee findById(Long id){
        Optional<Employee> employee=crudRepository.findById(id);

        return employee.orElse(null);
    }

    @Override
    public List<Employee> findAllEmployees(){
        return crudRepository.findAll();
    }


    @Override
    public List<Employee> searchForEmployee(EmployeeSearchParams params) {
        Specification<Employee> nameSpec = EmployeeSpecification.employeeNameEquals(params.getName());
        Specification<Employee> teamSpec = EmployeeSpecification.employeeTeamEquals(params.getTeam());
        Specification<Employee> teamleadSpec = EmployeeSpecification.employeeTeamLeaderEquals(params.getTeamLead());

        Specification<Employee> searchSpec = Specification.where(nameSpec).and(teamSpec).and(teamleadSpec);

        return crudRepository.findAll(searchSpec, Sort.by(Sort.Direction.ASC, "personalId"));
    }

}
