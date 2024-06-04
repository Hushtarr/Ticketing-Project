package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
@Entity
@EqualsAndHashCode

//@Where(clause = "is_deleted=false")

public class User extends BaseEntity {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;

    private String passWord;
    private boolean enabled;
    private String phone;

    @ManyToOne
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}
