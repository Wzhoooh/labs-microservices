package com.biometric.faces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@ToString
public class FacesStringList extends RepresentationModel<FacesStringList> {
    private String stringFacesList;
    private List<Faces> facesList;
}
