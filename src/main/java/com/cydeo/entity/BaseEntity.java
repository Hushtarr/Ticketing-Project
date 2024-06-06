package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)

public class BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Boolean isDeleted=false;

    @Column(nullable = false,updatable =false)
    private LocalDateTime insertDateTime;

    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;

    @Column(nullable = false,updatable =false)
    private Long insertUserId;

    @Column(nullable = false)
    private Long lastUpdateUserId;

//    @PrePersist //todo
//    private void onPrePersist(){
//        this.insertDateTime=LocalDateTime.now();
//        this.lastUpdateDateTime=LocalDateTime.now();
//        this.insertUserId=1L;
//        this.lastUpdateUserId=1L;
//    }

//    @PreUpdate // todo
//    private void onPreUpdate (){
//        this.lastUpdateDateTime=LocalDateTime.now();
//        this.lastUpdateUserId=1L;
//    }
}
