package dasturlash.uz.repositories;

import dasturlash.uz.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableRepository {

    public void createTable() {
        String sql = "create table if not exists profile(" +
                "id serial primary key," +
                "name varchar(25) not null," +
                "surname varchar(25) not null," +
                "login varchar(25) unique not null," +
                "password varchar(50) not null," +
                "role varchar(25) not null," +
                "status varchar(25) not null," +
                "created_at timestamp not null," +
                "updated_at timestamp " +
                ")";
           create(sql);
    }

    public void create(String sql) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
