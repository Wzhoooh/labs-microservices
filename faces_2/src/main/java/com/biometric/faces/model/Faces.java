package com.biometric.faces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Faces extends RepresentationModel<Faces> {
    private String companyName;
    private String modelName;
    private int modelCode;
    private double price;
    private double accuracy;
    private double energyConsumption;
    private String technology;
    private double speed;
}
