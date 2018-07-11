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
    bidCache.put("1", new Bid("1", "1", "1", "Андрей",
            "Я могу лучше, блджад!", "16.06.2020"));
    bidCache.put("2", new Bid("2", "1", "2 ", "Максим",
            "Я могу!", "15.06.2020"));
  }


  @Override
  public Bid fetchBid(final String id) {
    return bidCache.get(id);
  }

  @Override
  public Bid updateBid(final Bid bid) {
    bidCache.put(bid.getBid_id().toString(), bid);
    return bid;
  }

  @Override
  public void deleteBid(final String id) {
    bidCache.remove(id);
  }

  @Override
  public Bid createBid(final Bid bid) {
    bid.setBid_id(String.valueOf((int)(System.currentTimeMillis())));  //очень плохой способ генерировать Id, тут только для примера
    bidCache.put(bid.getBid_id().toString(), bid);
    return bid;
  }

  @Override
  public Collection<Bid> getAllBids(String task_id) {
    return bidCache.values(); //TODO: find by task_id
  }
}
