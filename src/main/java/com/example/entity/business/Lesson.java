package com.example.entity.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lessonName;

    private Integer creditScore;

    private boolean isCompulsory;


    //Note:LessonProgram
    @JsonIgnore//url den gelen ilk istedigi olan yere yazmamiz gerek
    @ManyToMany(mappedBy = "lessons",cascade = CascadeType.REMOVE)
    private Set<LessonProgram> lessonPrograms;



}
