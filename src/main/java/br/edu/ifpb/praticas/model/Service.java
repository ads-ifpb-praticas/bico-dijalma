package br.edu.ifpb.praticas.model;

import br.edu.ifpb.praticas.enums.TypeService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:52
 */
@Entity
public class Service implements Serializable {

    @Id
    @GenericGenerator(name = "servicoGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "servicoGenerator"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")

            })
    private Long id;
    @Transient
    private TypeService typeOfService;
    @OneToOne
    private Client client;
    @OneToOne
    private Provider provider;
    private String details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeService getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(TypeService typeOfService) {
        this.typeOfService = typeOfService;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
