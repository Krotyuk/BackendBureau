package ftc.shift.sample.mapper;

import ftc.shift.sample.models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    public static final String SELECT_REQUEST = "SELECT * FROM tasks";
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        int task_id = rs.getInt(1);
        int user_id = rs.getInt(2);
        String title = rs.getString(3);
        String description = rs.getString(4);
        String short_desc = rs.getString(5);
        String date = rs.getString(6);
        return new Task(String.valueOf(task_id), String.valueOf(user_id), title, description, short_desc, date);
    }
}
