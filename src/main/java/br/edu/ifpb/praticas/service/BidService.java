package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.model.Job;
import br.edu.ifpb.praticas.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
@Service
public class BidService {

    @Autowired
    private BidRepository dao;
    @Autowired
    private JobService jobService;

    public Bid save(Bid bid) {
        return dao.save(bid);
    }

    public Bid edit(Long id, Bid bid) {
        Bid finded = dao.findOne(id);
        finded = bid;
        return dao.save(finded);
    }

    public Bid findOne(Long id) {
        return dao.findOne(id);
    }

    public List<Bid> findBidsFromJob(Long idJob) {
        Job one = jobService.findOne(idJob);
        return dao.findBidsByJob(one);
    }

    public List<Bid> findBidsFromIdClient(Long idCLient, Long id_job) {
        List<Bid> bids = dao.findBidsByIdClient(idCLient);
        List bidsFinal = new ArrayList();

        for (Bid b : bids) {
            if (Objects.equals(b.getJob().getId(), id_job))
                bidsFinal.add(b);
        }

        return bidsFinal;
    }
}
