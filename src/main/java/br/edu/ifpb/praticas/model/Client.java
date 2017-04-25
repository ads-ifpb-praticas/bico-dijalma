package br.edu.ifpb.praticas.model;

import br.edu.ifpb.praticas.enums.StatusEnum;

import javax.persistence.*;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 16/02/17 - 21:16
 */
@Entity
public class Client extends Person {

    @Embedded
    private User user;
    @Embedded
    private PlaceOfCare placeOfCare;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public Client() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlaceOfCare getPlaceOfCare() {
        return placeOfCare;
    }

    public void setPlaceOfCare(PlaceOfCare placeOfCare) {
        this.placeOfCare = placeOfCare;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
