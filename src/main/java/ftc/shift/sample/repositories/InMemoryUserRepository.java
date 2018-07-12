package ftc.shift.sample.repositories;



import com.mysql.cj.xdevapi.Statement;
import ftc.shift.sample.mapper.UserMapper;
import ftc.shift.sample.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
//import java.sql.*;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryUserRepository extends JdbcDaoSupport implements UserRepository {

  private Map<String, User> userCache = new HashMap<>();
  JdbcTemplate jdbcTemplate;


  @Autowired
  public InMemoryUserRepository(@Qualifier("dataSource") DataSource dataSource){
    this.setDataSource(dataSource);

  }
//Karma start
   @Override
  public User updateKarmaUp(final User user) {
    String sql = "UPDATE users SET karma = karma+10 WHERE id = id";
    jdbcTemplate.update(sql, user.setKarma());
    return user;

    @Override
  public User updateKarmaDown(final User user) {
    String sql = "UPDATE users SET karma = karma-10 WHERE id = id";
    jdbcTemplate.update(sql, user.setKarma());
    return user;
  }
  //Karma end

  @Override
  public User fetchUser(final String id) {
    String sql = UserMapper.SELECT_REQUEST + " where id = ?";
    Object[] params = new Object[] { id };
    UserMapper mapper = new UserMapper();
    try{
      User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
      return user;
    }catch(EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public void updateUser(final User user) {
    String sql = "UPDATE users SET name = ?, phone = ? WHERE id = ?";
    jdbcTemplate.update(sql,user.getName(),user.getPhone(), user.getId());
  }

  @Override
  public void deleteUser(final String id) {
    String sql = "DELETE FROM users WHERE id = ?";
    jdbcTemplate.update(sql, id);

  }

  @Override
  public User createUser(final User user) {
    String sql = "INSERT INTO users (name, phone) VALUES(?, ?)";
    jdbcTemplate.update(sql, user.getName(), user.getId());
    return user;
  }

  @Override
  public Collection<User> getAllUsers() {
    String sql = "SELECT * FROM users";
    UserMapper mapper = new UserMapper();
    Collection <User> userCollection = jdbcTemplate.query(sql,mapper);
    return userCollection;

  }


}
