package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Bid;

import java.util.Collection;

public interface BidRepository {

    Bid fetchBid(Integer id);

    Bid updateBid(Bid bid);

    void deleteBid(Integer id);

    Bid createBid(Bid bid);

    Collection<Bid> getAllBids();
}
