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

         String sqlForCategory = "create table if not exists category(" +
                 "id serial primary key," +
                 "name varchar(25) unique not null," +
                 "visible boolean not null," +
                 "created_at timeStamp not null" +
                 ")";

        String sqlForBook = "create table if not exists book(" +
                "id serial primary key," +
                "title varchar(25) not null," +
                "author varchar(25)," +
                "category_id int not null," +
                "visible boolean not null," +
                "created_at timeStamp not null," +
                "published_at date not null," +
                "available_days int not null," +
                " constraint fk_category_id foreign key(category_id) references category(id)" +
                ")";

           create(sql);
           create(sqlForCategory);
           create(sqlForBook);
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
