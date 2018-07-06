package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Bid;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryBidRepository implements BidRepository {

  private Map<String, Bid> bidCache = new HashMap<>();

  public InMemoryBidRepository() {
    bidCache.put("1", new Bid(1, "Андрей", "Поднять диван девчушке", "15.06.2020",
            false, 1));
    bidCache.put("2", new Bid(2, "Максим", "Не поднять бабла ", "15.06.2019",
            false, 2));
  }


  @Override
  public Bid fetchBid(final Integer id) {
    return bidCache.get(id);
  }

  @Override
  public Bid updateBid(final Bid bid) {
    bidCache.put(bid.getId_bid().toString(), bid);
    return bid;
  }

  @Override
  public void deleteBid(final Integer id) {
    bidCache.remove(id);
  }

  @Override
  public Bid createBid(final Bid bid) {
    bid.setId_bid(Integer.valueOf((int)(System.currentTimeMillis())));  //очень плохой способ генерировать Id, тут только для примера
    bidCache.put(bid.getId_bid().toString(), bid);
    return bid;
  }

  @Override
  public Collection<Bid> getAllBids() {
    return bidCache.values();
  }
}
