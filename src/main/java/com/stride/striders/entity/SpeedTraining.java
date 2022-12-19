package com.stride.striders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class SpeedTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private String description;
    private String weatherConditions;
    private Integer participants;

}
