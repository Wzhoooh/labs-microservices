package com.biometric.faces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="face_rec_terminals")
public class Faces /*extends RepresentationModel<Faces>*/ {
    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "model_name", nullable = false)
    private String modelName;
    @Id
    @Column(name = "model_code", nullable = false)
    private int modelCode;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "accuracy", nullable = false)
    private double accuracy;
    @Column(name = "energy_consumption", nullable = false)
    private double energyConsumption;
    @Column(name = "technology", nullable = false)
    private String technology;
    @Column(name = "speed", nullable = false)
    private double speed;
    @Column(name = "comment")
    private String comment;

    public com.biometric.faces.model.Faces withComment(String comment) {
        this.setComment(comment);
        return this;
    }
}
