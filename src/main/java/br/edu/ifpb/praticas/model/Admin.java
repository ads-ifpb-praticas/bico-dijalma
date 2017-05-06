package br.edu.ifpb.praticas.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 06/05/17.
 */
@Entity
public class Admin extends Person {

    @Embedded
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
