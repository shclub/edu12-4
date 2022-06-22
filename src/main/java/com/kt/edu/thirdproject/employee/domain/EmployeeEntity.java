package com.kt.edu.thirdproject.employee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("EMPLOYEE")
@Schema(description = "employee Entity")
//json 에서 해당 값 제외
@JsonIgnoreProperties({"new"})
public class EmployeeEntity implements Persistable<Long> {
    @Id
    @Schema(description = "순서")
    //@Column("id")
    private Long id;

    @Schema(description = "직원명")
    @Column("EMPNAME")
    private String empName;

    @Schema(description = "부서명")
    @Column("EMPDEPTNAME")
    private String empDeptName;

    @Schema(description = "전화번호")
    @Column("EMPTELNO")
    private String empTelNo;

    @Schema(description = "이메일")
    @Column("EMPMAIL")
    private String empMail;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() { return isNew; }

    //@Override
    /*public Long getId(){
        return id;
    }*/

    /*public static EmployeeEntity of(Article article) throws Exception {
        return EmployeeEntity.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }*/
}
