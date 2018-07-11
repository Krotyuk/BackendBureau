package ftc.shift.sample.mapper;

import ftc.shift.sample.models.Bid;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BidMapper implements RowMapper<Bid> {

    public static final String SELECT_REQUEST = "SELECT * FROM applies";
    @Override
    public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
        int bid_id = rs.getInt(1);
        int task_id = rs.getInt(2);
        int user_id = rs.getInt(3);
        String user_name = rs.getString(4);
        String text = rs.getString(5);
        String date = rs.getString(6);
        return new Bid(String.valueOf(bid_id), String.valueOf(task_id), String.valueOf(user_id), user_name, text, date);
    }
}
