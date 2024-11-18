package com.biometric.faces.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.biometric.faces.model.Faces;

import static java.sql.DriverManager.println;

@Service
public class FacesService {
    private List<Faces> facesList = new ArrayList<>();

    @Autowired
    MessageSource messages;

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

    public String createFaces(Faces faces, String companyName, Locale locale) {
        String responseMessage = null;
        if (faces != null) {
            faces.setCompanyName(companyName);
            //facesList.add(faces);
            responseMessage = String.format(messages.getMessage("faces.create.message", null, locale),
                    faces.toString());
        }

        return responseMessage;
    }

    public Faces getFaces(String companyName, String modelName, double price, String technology) {
        Faces terminal = new Faces();

        terminal.setModelCode(new Random().nextInt(5000));
        terminal.setCompanyName(companyName);
        terminal.setPrice(price);
        terminal.setTechnology(technology);
        terminal.setModelName(modelName);
        terminal.setAccuracy(90.5);
        terminal.setEnergyConsumption(2000.6);
        terminal.setSpeed(8000);

        return terminal;
    }

    public String modifyFaces(Faces faces, String companyName, Locale locale) {
        String responseMessage = null;

        /*if (faces != null) {
            for (int i = 0; i < facesList.size(); i++) {
                if (facesList.get(i).getModelName().equals(modelName)) {
                    faces.setCompanyName(companyName);
                    facesList.set(i, faces);
                    responseMessage = String.format(messages.getMessage("faces.update.message", null, locale),
                            faces.toString());
                }
            }
        }*/
        if (faces != null) {
            faces.setCompanyName(companyName);
            responseMessage = String.format(messages.getMessage("faces.update.message", null, locale),
                    faces.toString());
        }
        return responseMessage;
    }

    public String deleteFaces(String companyName, String modelName, double price, Locale locale) {
        String responseMessage = null;
        responseMessage = String.format(messages.getMessage("faces.delete.message", null, locale),
                modelName, price, companyName);

        /* Iterator<Faces> it = facesList.iterator();
        while(it.hasNext()) {
            Faces next = it.next();
            if (next.getModelName().equals(modelName)) {
                responseMessage = String.format(messages.getMessage("faces.delete.message", null, locale),
                        modelCode, next.getCompanyName());
                it.remove();
            }
        }*/

        return responseMessage;
    }
}
