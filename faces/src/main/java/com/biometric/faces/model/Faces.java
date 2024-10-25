package com.biometric.faces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Faces {
    /*private int id;
    private String hospitalName;
    private String boneType;
    private String segmentBone;
    private String fractureType;
    private boolean infection;
    private int patientsNumber;*/

    private String companyName;
    private String modelName;
    private String modelCode;
    private double Price;
    private double Accuracy;
    private double energyConsumption;
    private String technology;
    private double speed;
}
