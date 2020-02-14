package com.gayan.demo.service;

import com.gayan.demo.model.Allocation;
import com.gayan.demo.model.Employee;
import com.gayan.demo.model.Telephone;
import com.gayan.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Value("${service.host}")
    private String allocationServiceHost;

    @Value("${service.allocation.findAll}")
    private String findAll;

    @Value("${service.allocation.findByEmployeeId}")
    private String findByEmployeeIdURI;

    /*
    @Value("#{${valuesMap}}")
    private Map<String, Integer> valuesMap;
     @Value annotation to inject a Map property.
     */

    /*@Value("#{systemProperties['priority']}")
    private String spelValue;
    We can also use SpEL expressions to get the value. If we have a system property named priority,
    then its value will be applied to the field
    */

    @Override
    public Employee save(Employee employee) {

        for (Telephone telephone:employee.getTelephones()) {
            telephone.setEmployee(employee);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.isPresent())
            return employee.get();
        return null;
    }

    @Override
    public List<Allocation> fetchAllocation() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Allocation[]> responseResult = restTemplate.getForEntity(allocationServiceHost + findAll, Allocation[].class);

        Allocation[] resultSet = responseResult.getBody();

        List<Allocation> allocations = new ArrayList<>();

        for (Allocation allocation : resultSet) {
            allocation.setEmployee(this.findById(allocation.getEmpId()));
            allocations.add(allocation);

        }

        return allocations;

    }


}
