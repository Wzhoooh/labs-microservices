package com.biometric.faces.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.biometric.faces.config.ServiceConfig;
import com.biometric.faces.model.FacesStringList;
import com.biometric.faces.repository.FacesRepository;
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
    @Autowired
    private FacesRepository facesRepository;
    @Autowired
    ServiceConfig config;

    public Faces createFaces(Faces faces, String companyName/*, Locale locale*/) {
        //String responseMessage = null;
        //responseMessage = String.format(messages.getMessage("faces.create.message", null, locale), faces.toString());
        //return responseMessage;

        if (faces != null) {
            faces.setCompanyName(companyName);
            facesRepository.save(faces);
        }
        String comment = config.getProperty();
        System.out.println("Setting comment: " + comment);
        return faces.withComment(config.getProperty());
    }

    public FacesStringList getFaces(String companyName, double price, Locale locale) {
        /*Faces terminal = new Faces();

        terminal.setModelCode(new Random().nextInt(5000));
        terminal.setCompanyName(companyName);
        terminal.setPrice(price);
        terminal.setTechnology(technology);
        terminal.setModelName(modelName);
        terminal.setAccuracy(90.5);
        terminal.setEnergyConsumption(2000.6);
        terminal.setSpeed(8000);

        return terminal;*/

        List<Faces> terminals = facesRepository.findByCompanyNameAndPriceLessThanEqual(companyName, price);
        if (terminals == null) {
            throw new IllegalArgumentException(String.format(messages.getMessage("faces.search.error.message", null, null), price,
                    companyName));
        }
        for (Faces terminal : terminals) {
            terminal.withComment(config.getProperty());
        }
        FacesStringList flist = new FacesStringList();
        flist.setFacesList(terminals);
        String resp = " ";
        for (int i = 0; i < terminals.size(); i++) {
            resp += String.format("%s ;", terminals.get(i).toString());
        }
        flist.setStringFacesList(String.format(messages.getMessage("faces.get.message", null, locale),
                resp));

        return flist;
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
            facesRepository.save(faces); //?
            responseMessage = String.format(messages.getMessage("faces.update.message", null, locale),
                    faces.toString());
        }
        return responseMessage;
    }

    public String deleteFaces(String companyName, String modelName, Locale locale) {
        String responseMessage = null;
        Faces terminal = facesRepository.findByCompanyNameAndModelName(companyName, modelName);
        if (terminal != null) {
            facesRepository.delete(terminal);
            responseMessage = String.format(messages.getMessage("faces.delete.message", null, locale),
                    modelName, companyName);
        }

        return responseMessage;
    }
}
