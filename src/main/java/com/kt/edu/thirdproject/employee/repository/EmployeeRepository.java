package com.kt.edu.thirdproject.employee.repository;

import com.kt.edu.thirdproject.employee.domain.EmployeeEntity;
import com.kt.edu.thirdproject.employee.repository.sql.QueryEmployeeSqls;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    @Query(QueryEmployeeSqls.RETV_NEXT_VAL)
    Long retvNextVal();

}
