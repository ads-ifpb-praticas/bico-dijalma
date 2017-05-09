package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 09/05/17.
 */
@Service
public class BidService {

    @Autowired
    private BidRepository dao;

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

}
