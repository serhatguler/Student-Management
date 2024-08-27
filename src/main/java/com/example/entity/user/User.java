package com.example.entity.user;

import com.example.entity.business.LessonProgram;
import com.example.entity.business.Meet;
import com.example.entity.business.StudentInfo;
import com.example.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String ssn;

    private String name;

    private String surName;


    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String birthPlace;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//DB ye giden JSon dosyada password olsun,Db den Clienta giden Dosyada password olmasin
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private Boolean built_in;// 2 admin oldugunu dusun .adminkler birbirini siler. admin kalmayabilir
                            // bir tane admin asla silinemeyecek.


    private String motherName;


    private String fatherName;

    private int studentNumber;

    private boolean isActive;

    private boolean isAdvisor;

    private Long advisorTeacherId; //bu field studentlar icin eklendi.

    private Gender gender;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//DB ye giden JSon dosyada role olsun,Db den Clienta giden Dosyada roleolmasin
    private UserRole userRole;


    //Not:StudentInfo-LessonProgram-Meet

    @OneToMany(cascade = CascadeType.REMOVE)//user silinirse student info da silinecek
    private List<StudentInfo> studentInfos; //set olablir??

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_lessonprogram",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_program_id")
    )
    private Set<LessonProgram> lessonsProgramList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "meet_student_table",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "meet_id")
    )
    private List<Meet> meetList;







}
