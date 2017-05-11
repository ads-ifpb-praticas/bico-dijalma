package br.edu.ifpb.praticas.enums;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:08
 */
public enum TypeService {

    ELECTRIC("Elétrica"), HYDRAULIC("Hidráulica"), PAINT("Pintura"), MASONRY("Alvenaria"), REPAIR("Reparo");

    private String name;

    TypeService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
