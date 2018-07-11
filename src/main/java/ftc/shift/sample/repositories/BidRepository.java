package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Bid;

import java.util.Collection;

public interface BidRepository {

    Bid fetchBid(String id);

    Bid updateBid(Bid bid, String id);

    void deleteBid(String id);

    Bid createBid(Bid bid, String user_phone);

    Collection<Bid> getAllBids(String task_id);
}
