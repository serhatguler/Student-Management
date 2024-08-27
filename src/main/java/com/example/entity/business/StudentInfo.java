package com.example.entity.business;

import com.example.entity.enums.Note;
import com.example.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer absentee;

    private Double midtermExam;

    private Double finalExam;

    private Double examAverage;

    private String infoNote;

    private Note letterGrade;

    @ManyToOne
    @JsonIgnore
    private User teacher;


    @ManyToOne
    @JsonIgnore
    private User student;

    //Note:Lesson-Education Term

    @ManyToOne
    private Lesson lesson;

    @OneToOne
    private EducationTerm educationTerm;

}
