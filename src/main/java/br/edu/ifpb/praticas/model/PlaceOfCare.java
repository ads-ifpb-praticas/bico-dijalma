package br.edu.ifpb.praticas.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 16/02/17 - 21:25
 */
@Embeddable
public class PlaceOfCare implements Serializable {

    private String city;
    private String district;
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
