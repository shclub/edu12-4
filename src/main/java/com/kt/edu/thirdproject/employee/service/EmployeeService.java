package com.kt.edu.thirdproject.employee.service;

import com.kt.edu.thirdproject.employee.domain.EmployeeEntity;
import com.kt.edu.thirdproject.common.exception.ResourceNotFoundException;
import com.kt.edu.thirdproject.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    // jasypt로 저장된 비밀번호가  복호화 된다.
    @Value("${spring.datasource.password}")
    private String h2Password;

    @Autowired
    public EmployeeService(RestTemplateBuilder restTemplateBuilder, EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;

    }
    public List<EmployeeEntity> getEmployeeList() {
        log.info("Request to get all Employees");
        log.info("h2 password : " + h2Password );
        List<EmployeeEntity> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    // 플러시를 생략해서 dirty checking등을 하지 않으므로 약간의 성능 향상
    @Transactional(readOnly = true)
    public EmployeeEntity getEmployee(Long id) {
        log.info("Request to get Employee : {}", id);
        return employeeRepository.findById(id).get();
    }

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        log.info("Request to create Employee : " +  employeeEntity);
        employeeEntity.setId(employeeRepository.retvNextVal());
        employeeEntity.setNew(true);
        return this.employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity update(Long id,EmployeeEntity employeeEntity) {
        log.info("Request to update Employee : " +  employeeEntity);
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employee.setId(id);
        employee.setEmpName(employeeEntity.getEmpName());
        employee.setEmpDeptName(employeeEntity.getEmpDeptName());
        employee.setEmpTelNo(employeeEntity.getEmpTelNo());
        employee.setEmpMail(employeeEntity.getEmpMail());
        employee.setNew(false);
        return this.employeeRepository.save(employee);
    }

    public EmployeeEntity delete(Long id) {
        log.info("Request to delete Employee id : " +  id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not exist with id" +id));
        employeeRepository.delete(employeeEntity);
        return  employeeEntity;
    }
}
