package com.biometric.faces.service;

import java.util.Random;
import org.springframework.stereotype.Service;

import com.biometric.faces.model.Faces;

@Service
public class FacesService {
    //private List<Faces> facesList = new ArrayList<>();

    /* public Fractures getFractures(String hospitalName, String boneType, int patientsNumber){
        Fractures fractures = new Fractures();
        fractures.setId(new Random().nextInt(1000));
        fractures.setHospitalName(hospitalName);
        fractures.setBoneType(boneType);
        fractures.setSegmentBone("diaphyseal");
        fractures.setFractureType("closed");
        fractures.setInfection(false);
        fractures.setPatientsNumber(patientsNumber);

        return fractures;
       }*/

    /*public String createFractures(Fractures fractures, String hospitalName){
        String responseMessage = null;
        if(fractures != null) {
            fractures.setHospitalName(hospitalName);
            responseMessage = String.format("This is the post and the object is: %s", fractures.toString());
        }

        return responseMessage;
    }*/

    public String createFaces(Faces faces, String companyName) {
        String responseMessage = null;
        if (faces != null) {
            faces.setCompanyName(companyName);
            responseMessage = String.format("This is the post and the object is: %s", faces.toString());
        }
        return responseMessage;
    }

    public Faces getFaces(String companyName, double price, String technology) {
        Faces faces = new Faces();

        faces.setCompanyName(companyName);
        faces.setPrice(price);
        faces.setTechnology(technology);
        faces.setAccuracy(0.89);
        faces.setSpeed(new Random().nextDouble(1000));
        faces.setModelCode("1AV34");
        faces.setEnergyConsumption(new Random().nextDouble(10000));
        faces.setModelName("ElectroFaceinator");

        return faces;
    }
}
