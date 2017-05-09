package br.edu.ifpb.praticas.form;

import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.model.Job;
import br.edu.ifpb.praticas.model.Provider;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
public class BidVO implements Serializable {

    private Job job;
    private Provider provider;
    private Date possibleDate;
    private BigDecimal value;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Date getPossibleDate() {
        return possibleDate;
    }

    public void setPossibleDate(Date possibleDate) {
        this.possibleDate = possibleDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Bid toBid() {
        Bid bid = new Bid();
        bid.setJob(job);
        bid.setProvider(provider);
        bid.setValue(value);
        bid.setPossibleDate(possibleDate.toLocalDate());
        return bid;
    }
}
