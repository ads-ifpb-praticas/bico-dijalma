package br.edu.ifpb.praticas.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 16/02/17 - 20:42
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GenericGenerator(name = "pessoaGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "pessoaGenerator"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")

            })
    private Long id;
    private String name;
    private String lastName;
    private String telephone;
    @Column(unique = true)
    private String email;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Lazy
    @Column(columnDefinition = "varchar(255) default '/static/img/man.png'")
    private String pathPhoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id.equals(person.id) && cpf.equals(person.cpf);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + cpf.hashCode();
        return result;
    }
}
