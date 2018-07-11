package ftc.shift.sample.mapper;

import ftc.shift.sample.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User>{
    public static final String SELECT_REQUEST = "SELECT * FROM users";


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        int id = rs.getInt(1);
        String name = rs.getString(2);
        String phone = rs.getString(3);
        return new User (String.valueOf(id), name, phone);
    }



}
