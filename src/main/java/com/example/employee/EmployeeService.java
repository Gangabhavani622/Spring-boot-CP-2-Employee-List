/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */

package com.example.employee;

import com.example.employee.Employee;
import com.example.employee.EmployeeRepository;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;

import javax.validation.OverridesAttribute;

// Do not modify the below code

public class EmployeeService implements EmployeeRepository {

    private static HashMap<Integer, Employee> employeeList = new HashMap<>();
    int uniqueId = 7;

    public EmployeeService() {
        employeeList.put(1, new Employee(1, "John Doe", "johndoe@example.com", "Marketing"));
        employeeList.put(2, new Employee(2, "Jane Smith", "janesmith@example.com", "Human Resources"));
        employeeList.put(3, new Employee(3, "Bob Johnson", "bjohnson@example.com", "Sales"));
        employeeList.put(4, new Employee(4, "Alice Lee", "alee@example.com", "IT"));
        employeeList.put(5, new Employee(5, "Mike Brown", "mbrown@example.com", "Finance"));
        employeeList.put(6, new Employee(6, "Sara Lee", "slee@example.com", "Operations"));

    }

    // Do not modify the above code

    // Write your code here
    @Override
    public ArrayList<Employee> getEmployeesList() {
        Collection<Employee> employeeCollections = employeeList.values();
        ArrayList<Employee> employeesList = new ArrayList<>(employeeCollections);
        return employeesList;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = employeeList.get(employeeId);
        if (employee == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(uniqueId);
        employeeList.put(uniqueId, employee);
        uniqueId++;
        return employee;
    }

    public Employee updateEmployee(int employeeId, Employee employee) {
        Employee employeeExists = employeeList.get(employeeId);
        if (employeeExists == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (employee.getEmployeeName() != null)
            employeeExists.setEmployeeName(employee.getEmployeeName());
        if (employee.getEmail() != null)
            employeeExists.setEmail(employee.getEmail());
        if (employee.getDepartment() != null)
            employeeExists.setDepartment(employee.getDepartment());

        return employeeExists;

    }

    public void deleteEmployee(int employeeId) {
        Employee employeeExists = employeeList.get(employeeId);
        if (employeeExists == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else {
            employeeList.remove(employeeId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}
