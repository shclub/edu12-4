package com.kt.edu.thirdproject.employee.repository.sql;

public class QueryEmployeeSqls {

    public static final String RETV_NEXT_VAL="""
     SELECT hibernate_sequence.nextval FROM  dual 
           """;
}
