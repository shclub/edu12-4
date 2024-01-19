package com.kt.edu.thirdproject.employee.repository.sql;

public class QueryEmployeeSqls {


    //profile is prd ( maria, mysql )
    public static final String RETV_NEXT_VAL="""
    SELECT NEXTVAL(hibernate_sequence);    
           """;
    //profile is local,dev ( h2 )
    public static final String RETV_NEXT_VAL_H2="""
    select nextval ('hibernate_sequence');  
           """;
}