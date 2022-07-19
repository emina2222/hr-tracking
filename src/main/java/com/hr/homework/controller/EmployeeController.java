package com.hr.homework.controller;

import com.hr.homework.controller.exception.ErrorCode;
import com.hr.homework.entity.Employee;
import com.hr.homework.entity.Team;
import com.hr.homework.service.EmployeeService;
import com.hr.homework.utils.EmployeeSearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles CRUD operations over REST and provides search endpoint with optional filter params.
 */
@RestController
@RequestMapping("/hr")
public class EmployeeController {

    private static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    /**
     * Returns all employees.
     * @return List<Employee>
     */
    @GetMapping("/all")
    public List<Employee> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    /**
     * Saves new employee's data and provides auto-generated personalId value.
     * @param employee new employee's data
     * @return ResponseEntity<String>
     */
    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody Employee employee){
        if(employee!=null){
            employeeService.save(employee);

            logger.info("Employee {} is saved successfully.", employee);

            return new ResponseEntity<>("Employee successfully saved.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Please provide the new employee's data.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Updates employee's data based on personalId value.
     * @param employee updated employee's data
     * @return ResponseEntity<String>
     */
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Employee employee){
        if(employee!=null && employee.getPersonalId()!=null){
            if(employeeService.findById(employee.getPersonalId())!=null){
                employeeService.save(employee);

                logger.info("Employee {} is updated successfully.", employee.getName());

                return new ResponseEntity<>("Employee successfully updated.", HttpStatus.OK);
            }
            else{
                logger.error("Employee with id {} was not found in database and it cannot be updated.", employee.getPersonalId());

                return new ResponseEntity<>(ErrorCode.ENTRY_NOT_FOUND.getDescription(), HttpStatus.BAD_REQUEST);
            }
        }

        logger.error("Request body does not provide enough data for update (it's missing either personalID or no data is provided).");

        return new ResponseEntity<>("Please provide employee's data, including id.", HttpStatus.BAD_REQUEST);
    }

    /**
     * In order to delete employee, request body must contain personalId.
     * @param employee employee who needs to be deleted
     * @return ResponseEntity<String>
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee){
        if(employee!=null && employee.getPersonalId()!=null){
            employeeService.delete(employee);
            return new ResponseEntity<>("Employee successfully deleted.", HttpStatus.OK);
        }

        logger.error("Request body does not provide enough data for delete operation (it's missing personalID or no data is provided).");

        return new ResponseEntity<>("Please provide employee's data.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Finds employee by personalID.
     * @param id personalId value
     * @return employee object or error message
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id){
        Employee employee=employeeService.findById(id);

        if(employee!=null){
            logger.info("Employee with id {} is found. ", id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }

        logger.info("Employee with id {} is not found. ", id);
        return new ResponseEntity<>("Employee with provided personal id is not found.", HttpStatus.BAD_REQUEST);
    }

    /**
     * Performs search and filtering by optional params.
     * @param name optional param
     * @param team optional param
     * @param teamLead optional param
     * @return List<Employee> that matches the filters
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchEmployeeByCriteria(@RequestParam (required = false) String name,
                                                                   @RequestParam (required = false) Team team,
                                                                   @RequestParam (required = false) String teamLead){

        EmployeeSearchParams params = new EmployeeSearchParams(name, team, teamLead);

        logger.info("Filter params: {} ", params);

        List<Employee> filteredEmployees=employeeService.searchForEmployee(params);

        if(filteredEmployees.isEmpty()){
            return new ResponseEntity<>("No record matches provided filter.", HttpStatus.OK);
        }

        return new ResponseEntity<>(filteredEmployees, HttpStatus.OK);
    }

}
