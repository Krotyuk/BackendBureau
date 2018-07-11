package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Bid;

import java.util.Collection;

public interface BidRepository {

    Bid fetchBid(String id);

    Bid updateBid(Bid bid);

    void deleteBid(String id);

    Bid createBid(Bid bid);

    Collection<Bid> getAllBids(String task_id);
}
