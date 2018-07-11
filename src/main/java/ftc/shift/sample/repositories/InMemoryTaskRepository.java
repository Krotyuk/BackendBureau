package ftc.shift.sample.repositories;

import ftc.shift.sample.mapper.TaskMapper;
import ftc.shift.sample.models.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryTaskRepository extends JdbcDaoSupport implements TaskRepository {

  JdbcTemplate jdbcTemplate;


  public InMemoryTaskRepository(@Qualifier("dataSource") DataSource dataSource) {
    this.setDataSource(dataSource);
  }

  @Override
  public Task fetchTask(final String id) {
    String sql = TaskMapper.SELECT_REQUEST + " where task_id = ?";
    Object[] params = new Object[] { id };
    try{
      Task task = this.getJdbcTemplate().queryForObject(sql, params, new TaskMapper());
      return task;
    }catch(EmptyResultDataAccessException e) {
      return null;
    }

  }

  @Override
  public Task updateTask(final Task task) {
    String sql = "UPDATE tasks SET title = ?, description = ?, short_desc = ?, task_date = ?  WHERE id = ?";
    jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getShort_description(), task.getDate());
    return task;
  }

  @Override
  public void deleteTask(final String id) {
    String sql = "DELETE FROM tasks WHERE task_id = ?";
    jdbcTemplate.update(sql, id);
  }

  @Override
  public Task createTask(final Task task, String user_name) {
    String sql = "INSERT INTO tasks (title, description, short_desc, task_date)\n" +
            "VALUES (?, ?, ?, ?);\n" +
            "\n" +
            "UPDATE tasks\n" +
            "SET user_id = (SELECT id from users where name = ?);";

    jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getShort_description(), task.getDate(), user_name);
    return task;
  }

  @Override
  public Collection<Task> getAllTasks() {
    String sql = "SELECT * FROM tasks";
  Collection<Task> result = new ArrayList<>();
    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
    for (Map row : rows) {
      Task task = new Task();
      task.setTask_id(String.valueOf(row.get("task_id")));
      //task.setUser_id(String.valueOf(row.get("user_id")));
      task.setTitle((String) row.get("title"));
      task.setShort_description((String) row.get("short_desc"));
      task.setDate((String) row.get("date"));
      result.add(task);
    }

    return result;
  }

}
