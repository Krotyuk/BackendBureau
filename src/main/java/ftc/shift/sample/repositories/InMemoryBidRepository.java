package ftc.shift.sample.repositories;

import ftc.shift.sample.mapper.BidMapper;
import ftc.shift.sample.mapper.TaskMapper;
import ftc.shift.sample.models.Bid;
import ftc.shift.sample.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryBidRepository extends JdbcDaoSupport implements BidRepository {

  JdbcTemplate jdbcTemplate;

  @Autowired
  public InMemoryBidRepository(@Qualifier("dataSource") DataSource dataSource){
    this.setDataSource(dataSource);

  }


  @Override
  public Bid fetchBid(final String id) {
    String sql = BidMapper.SELECT_REQUEST + " where bid_id = ?";
    Object[] params = new Object[] { id };
    try{
      Bid bid = this.getJdbcTemplate().queryForObject(sql, params, new BidMapper());
      return bid;
    }catch(EmptyResultDataAccessException e) {
      return null;
    }

  }

  @Override
  public Bid updateBid(final Bid bid, String idBid) {
    String sql = "UPDATE applies SET text = ?, date = ?  WHERE bid_id = ?";
    jdbcTemplate.update(sql, bid.getText(), bid.getDate(),idBid);
    return bid;
  }

  @Override
  public void deleteBid(final String id) {
    String sql = "DELETE FROM applies WHERE bid_id = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public Bid createBid(final Bid bid, String user_phone) {
    String sql = "INSERT INTO applies (task_id, text, date)\n" +
    "VALUES (?, ?, ?);\n" +
    "\n" +
    "UPDATE applies \n" +
    "SET author_name = (SELECT name FROM users WHERE phone = ?);\n" +
    "UPDATE applies\n" +
    "SET author_id = (SELECT id FROM users WHERE phone = ?);";
    jdbcTemplate.update(sql, bid.getTask_id(), bid.getText(), bid.getDate(), user_phone, user_phone);
    return bid;
  }

  @Override
  public Collection<Bid> getAllBids(String task_id) {

    String sql = "SELECT * FROM applies where task_id = " + task_id;
    Collection<Bid> result = new ArrayList<>();
    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
    for (Map row : rows) {
      Bid bid = new Bid();
      bid.setBid_id(String.valueOf(row.get("bid_id")));
      bid.setTask_id(String.valueOf(row.get("task_id")));
      bid.setUser_id(String.valueOf(row.get("author_id")));
      bid.setUser_name((String)row.get("author_name"));
      bid.setText((String)row.get("text"));
      bid.setDate((String)row.get("date"));
      result.add(bid);
    }

    return result;
  }
}
