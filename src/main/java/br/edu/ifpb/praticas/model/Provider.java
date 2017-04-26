package br.edu.ifpb.praticas.model;

import br.edu.ifpb.praticas.enums.StatusEnum;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:16
 */
@Entity
public class Provider extends Person {

    @Embedded
    private User user;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private int electric;
    private int hydraulic;
    private int paint;
    private int masonry;
    private int repairs;

    public Provider() {
        this.electric = 0;
        this.hydraulic = 0;
        this.paint = 0;
        this.masonry = 0;
        this.repairs = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getElectric() {
        return electric;
    }

    public void setElectric(int electric) {
        this.electric = electric;
    }

    public int getHydraulic() {
        return hydraulic;
    }

    public void setHydraulic(int hydraulic) {
        this.hydraulic = hydraulic;
    }

    public int getPaint() {
        return paint;
    }

    public void setPaint(int paint) {
        this.paint = paint;
    }

    public int getMasonry() {
        return masonry;
    }

    public void setMasonry(int masonry) {
        this.masonry = masonry;
    }

    public int getRepairs() {
        return repairs;
    }

    public void setRepairs(int repairs) {
        this.repairs = repairs;
    }
}
