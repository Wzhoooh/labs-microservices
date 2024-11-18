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

    public String createFaces(Faces faces, String companyName, Locale locale/*, String modelName, String modelCode, String technology,
                              double price, double accuracy, double energyConsumption, double speed*/) {
        String responseMessage = null;
        if (faces != null) {
            faces.setCompanyName(companyName);
            /*faces.setModelName(modelName);
            faces.setModelCode(modelCode);
            faces.setPrice(price);
            faces.setAccuracy(accuracy);
            faces.setEnergyConsumption(energyConsumption);
            faces.setTechnology(technology);
            faces.setSpeed(speed);*/
            facesList.add(faces);
            //responseMessage = String.format("This is the post and the object is: %s", faces.toString());
            responseMessage = String.format(messages.getMessage("faces.create.message", null, locale),
                    faces.toString());
        }

        return responseMessage;
    }

    public /*List<Faces>*/ String getFaces(String companyName, double price, String technology) {
        //Faces faces = new Faces();
        List<Faces> terminals = new ArrayList<>();
        String responseMessage = "";

        /*faces.setCompanyName(companyName);
        faces.setPrice(price);
        faces.setTechnology(technology);
        faces.setAccuracy(0.89);
        faces.setSpeed(new Random().nextDouble(1000));
        faces.setModelCode("1AV34");
        faces.setEnergyConsumption(new Random().nextDouble(10000));
        faces.setModelName("ElectroFaceinator");*/
        //for (int i = 0; i < facesList.)
        //responseMessage += companyName + price + technology;
        for (Faces terminal : facesList) {
            //responseMessage += terminal.getTechnology() + terminal.getPrice() + terminal.getCompanyName();
            if ((terminal.getCompanyName().equals(companyName)) &&
                    (terminal.getPrice() <= price) &&
                    (terminal.getTechnology().equals(technology))) {
                //responseMessage += terminal.getTechnology();
                terminals.add(terminal);
            }
        }

        for (int i = 0; i < terminals.size(); i++) {
            responseMessage += String.format("%d: %s \n", i, terminals.get(i).toString());
        }

        //return terminals;
        return responseMessage;
    }

    public String modifyFaces(Faces faces, String companyName, String modelCode, Locale locale) {
        String responseMessage = null;

        if (faces != null) {
            for (int i = 0; i < facesList.size(); i++) {
                if (facesList.get(i).getModelCode().equals(modelCode)) {
                    faces.setCompanyName(companyName);
                    facesList.set(i, faces);
                    //responseMessage = String.format("This is the put and the object is: %s", faces.toString());
                    responseMessage = String.format(messages.getMessage("faces.update.message", null, locale),
                            faces.toString());
                }
            }
        }
        return responseMessage;
    }

    public String deleteFaces(/*Faces faces, */String modelCode, Locale locale) {
        String responseMessage = null;

        Iterator<Faces> it = facesList.iterator();
        while(it.hasNext()) {
            Faces next = it.next();
            if (next.getModelCode().equals(modelCode)) {
                //responseMessage = String.format("This is the delete and the object is: %s", next.toString());
                responseMessage = String.format(messages.getMessage("faces.delete.message", null, locale),
                        modelCode, next.getCompanyName());
                it.remove();
            }
        }

        return responseMessage;
    }
}
