package com.biometric.faces.repository;

import com.biometric.faces.model.Faces;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacesRepository extends CrudRepository<Faces,String> {
    /*public List<Fractures> findByHospitalName
            (String hospitalName);
    public Fractures findByHospitalNameAndBoneType
            (String hospitalName,
             String boneType);*/
    public List<Faces> findByCompanyNameAndPrice(String companyName, double price);
}
