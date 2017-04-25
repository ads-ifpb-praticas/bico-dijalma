package br.edu.ifpb.praticas.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 16/02/17 - 20:40
 */
@Embeddable
public class User implements Serializable {

    @Column(unique = true)
    private String username;
    private String password;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

}
