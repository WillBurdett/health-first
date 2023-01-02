package com.healthfirst.memberservice.models;

import com.healthfirst.memberservice.enums.Gender;
import com.healthfirst.memberservice.enums.Interest;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "member")
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Integer age;
    @Column
    private Gender gender;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Interest interest;

}