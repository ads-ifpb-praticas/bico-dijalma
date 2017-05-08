package br.edu.ifpb.praticas.model;

import br.edu.ifpb.praticas.enums.StatusJob;
import br.edu.ifpb.praticas.enums.TypeService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 17/02/17 - 10:52
 */
@Entity
public class Job implements Serializable {

    @Id
    @GeneratedValue(generator = "jobGenerator")
    @GenericGenerator(name = "jobGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "jobGenerator"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")

            })
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeService typeOfService;
    @OneToOne
    private Client client;
    @OneToMany(mappedBy = "job")
    private List<Bid> bids;
    @OneToOne
    private Bid dealBid;
    private String details;
    private LocalDate dealDate;
    private StatusJob status;
    private BigDecimal willingToPay;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public void removeBid(Bid bid) {
        this.bids.remove(bid);
    }

    public StatusJob getStatus() {
        return status;
    }

    public void setStatus(StatusJob status) {
        this.status = status;
    }

    public LocalDate getDealDate() {
        return dealDate;
    }

    public void setDealDate(LocalDate dealDate) {
        this.dealDate = dealDate;
    }

    public Bid getDealBid() {
        return dealBid;
    }

    public void setDealBid(Bid dealBid) {
        this.dealBid = dealBid;
    }

    public BigDecimal getWillingToPay() {
        return willingToPay;
    }

    public void setWillingToPay(BigDecimal willingToPay) {
        this.willingToPay = willingToPay;
    }
}
