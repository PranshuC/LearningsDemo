package com.pranshu.LearningsDemo.dbInheritance;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Instructor extends ScalerUser{
    private double instructorRating;
}
