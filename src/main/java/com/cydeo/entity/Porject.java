package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="projects")
@Where(clause = "is_deleted=false")
public class Porject extends BaseEntity{

    private String projectCode;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
}
