package br.edu.ifpb.praticas.service;

import br.edu.ifpb.praticas.Exception.ClientException;
import br.edu.ifpb.praticas.enums.StatusJob;
import br.edu.ifpb.praticas.enums.TypeService;
import br.edu.ifpb.praticas.model.Bid;
import br.edu.ifpb.praticas.model.Client;
import br.edu.ifpb.praticas.model.Job;
import br.edu.ifpb.praticas.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 07/05/17.
 */
@Service
public class JobService {

    @Autowired
    private JobRepository dao;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BidService bidService;

    public Job findOne(Long id) {
        return dao.findOne(id);
    }

    public Job save(Job job) {
        job.setStatus(StatusJob.ABERTO);
        return dao.save(job);
    }

    public Job edit(Long id, Job job) {
        Job finded = dao.findOne(id);
        finded = job;
        return dao.save(finded);
    }

    public List<Job> jobsClientOpen(Long idClient) throws ClientException {
        return jobsClient(idClient, StatusJob.ABERTO);
    }

    public List<Job> jobsClientClose(Long idClient) throws ClientException {
        return jobsClient(idClient, StatusJob.FECHADO);
    }

    public List<Job> jobsClientFinish(Long idClient) throws ClientException {
        return jobsClient(idClient, StatusJob.FINALIZADO);
    }

    public List<Job> jobsOpenWithoutBidProviderAndTypeService(Long idProvider, TypeService typeService) {
        List<Job> jobs = jobsTypeServiceOpen(typeService);
        List<Job> jobsResult = new ArrayList<>();
        for (Job j : jobs) {
            List<Bid> bids = bidService.findBidsFromJob(j.getId());
            if (!verifyBidToProvider(idProvider, bids)) {
                jobsResult.add(j);
            }
        }

        return jobsResult;
    }

    private boolean verifyBidToProvider(Long idProvider, List<Bid> bids) {
        for (Bid b : bids) {
            if (Objects.equals(b.getProvider().getId(), idProvider)) {
                return true;
            }
        }

        return false;
    }

    public List<Job> jobsTypeServiceOpen(TypeService typeService) {
        return jobsStatusAndTypeService(StatusJob.ABERTO, typeService);
    }

    public List<Job> jobsCloseDateDeal(LocalDate dateDeal) {
        return jobsStatusAndDateDeal(StatusJob.FECHADO, dateDeal);
    }

    public List<Job> jobsFinishDateDeal(LocalDate dateDeal) {
        return jobsStatusAndDateDeal(StatusJob.FINALIZADO, dateDeal);
    }

    private List<Job> jobsStatusAndTypeService(StatusJob statusJob, TypeService typeService) {
        return dao.findByStatusAndTypeOfService(statusJob, typeService);
    }

    private List<Job> jobsStatusAndDateDeal(StatusJob statusJob, LocalDate date) {
        return dao.findByStatusAndDealDate(statusJob, date);
    }


    private List<Job> jobsClient(Long idClient, StatusJob statusJob) throws ClientException {
        Client client = clientService.findById(idClient);
        return dao.findByClientAndStatus(client, statusJob);
    }
}
