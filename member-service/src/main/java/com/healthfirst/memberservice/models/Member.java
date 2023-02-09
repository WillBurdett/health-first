package com.healthfirst.memberservice.models;

import com.healthfirst.memberservice.enums.Gender;
import com.healthfirst.memberservice.enums.Interest;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.*;
import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "member")
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Size(min = 2, max = 30)
    @Column
    private String firstName;

    @Size(min = 2, max = 30)
    @Column
    private String lastName;

    @Min(value = 18, message = "age must be 18 or higher")
    @Max(value = 130, message = "age cannot be more than 130")
    @Column
    private Integer age;

    @Column
    private Gender gender;

    @Email
    @Column
    private String email;

    @Size(min = 8)
    @Column
    private String password;

    @ElementCollection(targetClass=Interest.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="member_interest")
    @Column(name="interests") // Column name in person_interest
    private List<Interest> interests;

}