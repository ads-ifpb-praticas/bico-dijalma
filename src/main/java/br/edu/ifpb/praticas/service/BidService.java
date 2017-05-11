package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.enums.StatusJob;
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
    @Autowired
    private EmailTask emailTask;

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

    public boolean acceptBid(Long id, Bid bid) {
        Job job = jobService.findOne(bid.getJob().getId());
        List<Bid> bidsFromJob = findBidsFromJob(job.getId());
        bidsFromJob = removeBidFromID(bid.getId(), bidsFromJob);
        if (bidsFromJob.size() > 0) {
            sendEmailsToBidRefused(bidsFromJob);
        }
        dao.delete(bidsFromJob);
        sendEmailTOBidAccept(bid);
        job.setDealBid(bid);
        job.setStatus(StatusJob.FECHADO);
        Job edit = jobService.edit(id, job);
        return edit != null;
    }

    private void sendEmailsToBidRefused(List<Bid> bids) {
        for (Bid b : bids) {
            new Thread(() -> emailTask.sendEmailToProvidersRefusedBid(b.getProvider().getEmail(), b)).start();
        }
    }

    private void sendEmailTOBidAccept(Bid bid) {
        new Thread(() -> emailTask.sendEmailToProvidersAccept(bid.getProvider().getEmail(), bid)).start();
    }

    private List<Bid> removeBidFromID(Long id, List<Bid> bids) {
        List<Bid> bidsCopy = bids;

        for (Bid b : bidsCopy) {
            if (Objects.equals(b.getId(), id)) {
                bids.remove(b);
            }
        }

        return bids;
    }
}
