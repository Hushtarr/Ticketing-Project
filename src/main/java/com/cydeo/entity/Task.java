package com.cydeo.entity;


import com.cydeo.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@Table(name = "tasks")
@Getter
@Setter
public class Task extends BaseEntity {

    private String taskSubject;
    private String taskDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(columnDefinition = "Date")
    private LocalDate assignedDate;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private User assignedEmployee;

}
