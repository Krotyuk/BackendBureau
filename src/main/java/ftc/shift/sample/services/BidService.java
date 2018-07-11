package ftc.shift.sample.services;

import ftc.shift.sample.models.Bid;
import ftc.shift.sample.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class BidService {

    private final BidRepository bidRepository;



    @Autowired
    public BidService(final BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public Bid provideBid(String id) {
        return bidRepository.fetchBid(id);
    }

    public Bid updateBid(Bid bid) {
        bidRepository.updateBid(bid);
        return bid;
    }

    public void deleteBid(String id) {
        bidRepository.deleteBid(id);
    }


    public Bid createBid(Bid bid) {
        bidRepository.createBid(bid);
        return bid;
    }

    public Collection<Bid> provideBids(String task_id) {
        return bidRepository.getAllBids(task_id);
    }

}
