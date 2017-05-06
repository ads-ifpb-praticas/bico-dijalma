package br.edu.ifpb.praticas.enums;

/**
 * Created by dijalma on 25/04/17.
 */
public enum TypePeople {

    CLIENT("Client"), PROVIDER("Provider"), ADMIN("Admin");

    private String name;

    TypePeople(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
