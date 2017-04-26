package br.edu.ifpb.praticas.form;

import br.edu.ifpb.praticas.enums.TypePeople;
import br.edu.ifpb.praticas.model.User;

import java.io.Serializable;

/**
 * Created by dijalma on 25/04/17.
 */
public class UserVO implements Serializable {

    private Long id;
    private TypePeople type;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePeople getType() {
        return type;
    }

    public void setType(TypePeople type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
