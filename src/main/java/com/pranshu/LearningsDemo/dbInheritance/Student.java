package com.pranshu.LearningsDemo.dbInheritance;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends ScalerUser {
    private String batchName;
    private double psp;
}
