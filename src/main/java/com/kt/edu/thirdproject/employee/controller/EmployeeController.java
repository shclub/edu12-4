package com.kt.edu.thirdproject.employee.controller;

import com.kt.edu.thirdproject.employee.domain.EmployeeEntity;
import com.kt.edu.thirdproject.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "posts", description = "Employee API")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
@RequestMapping("/api/v1/")
public class EmployeeController {

    private final EmployeeService employeeService;


    //@Cacheable(cacheNames = "employee")
    @GetMapping("/employees")
    @Operation(summary ="임직원 전체 조회",description="임직원 전체를 조회 합니다.")
    public List<EmployeeEntity> getEmployeeList() {
        return this.employeeService.getEmployeeList();
    }

    @Operation(summary ="임직원 단건 조회",description="특정 임직원 단건에 대한 정보 조회 합니다.")
    @GetMapping("/employees/{id}")
    public EmployeeEntity getEmployee(@PathVariable Long id) {
        return this.employeeService.getEmployee(id);
    }

    @Operation(summary ="임직원 등록",description="임직원을 등록합니다.")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity employeeEntity) {
        EmployeeEntity createdEntity = employeeService.create(employeeEntity);
        return ResponseEntity.ok(createdEntity);
    }

    @PostMapping("/employees/{id}")
    @Operation(summary ="임직원 수정",description="임직원 정보를  수정합니다.")
    public ResponseEntity<EmployeeEntity> update(@PathVariable Long id, @RequestBody EmployeeEntity employeeEntity) {
        EmployeeEntity updatedEntity = employeeService.update(id,employeeEntity);
        return ResponseEntity.ok(updatedEntity);
    }

    @PostMapping("/employee/{id}")
    @Operation(summary ="임직원 정보 삭제",description="임직원 정보를 삭제합니다.")
    public ResponseEntity<EmployeeEntity> delete(@PathVariable Long id) {
        EmployeeEntity deletedEntity = employeeService.delete(id);
        return ResponseEntity.ok(deletedEntity);
    }
}