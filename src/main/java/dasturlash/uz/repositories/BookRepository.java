package dasturlash.uz.repositories;

import dasturlash.uz.dtos.BookDTO;
import dasturlash.uz.dtos.CategoryDTO;
import dasturlash.uz.utils.ConnectionUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookRepository {

    public Boolean add(BookDTO bookDTO) {

        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into book(title,author,visible,published_at,created_at,available_days,category_id)" +
                "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,bookDTO.getTitle());
            preparedStatement.setString(2,bookDTO.getAuthor());
            preparedStatement.setBoolean(3, (bookDTO.getVisible()));
            preparedStatement.setDate(4, Date.valueOf((bookDTO.getPublishedAt())));
            preparedStatement.setTimestamp(5, Timestamp.valueOf((bookDTO.getCreatedAt())));
            preparedStatement.setInt(6,bookDTO.getAvailableDays());
            preparedStatement.setInt(7,bookDTO.getCategory_id());

             var effectedRow = preparedStatement.executeUpdate();
             return effectedRow != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookDTO> bookLists() {
        List<BookDTO> bookDTOS = new LinkedList<>();
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select book.id,book.visible, book.published_at,book.title,book.created_at,book.available_days,book.author, category.id as cid, category.name as cname " +
                " from book inner join category on category.id = book.category_id" +
                " where book.visible = true";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
           ResultSet resultSet =  preparedStatement.executeQuery();
           while (resultSet.next()) {
               BookDTO bookDTO = new BookDTO();
               bookDTO.setId(resultSet.getInt("id"));
               bookDTO.setVisible(resultSet.getBoolean("visible"));
               bookDTO.setPublishedAt(resultSet.getDate("published_at").toLocalDate());
               bookDTO.setTitle(resultSet.getString("title"));
               bookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
//               bookDTO.setCategory_id(resultSet.getInt("category_id"));
               bookDTO.setAvailableDays(resultSet.getInt("available_days"));
               bookDTO.setAuthor(resultSet.getString("author"));

               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("cid"));
               categoryDTO.setName(resultSet.getString("cname"));
               bookDTO.setCategoryDTO(categoryDTO);
               bookDTOS.add(bookDTO);
           }
           return bookDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookDTO> search(String query) {
        List<BookDTO> bookDTOS = new LinkedList<>();
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select book.*, category.id as cid,category.name as cname from book " +
                " inner join category on book.category_id = category.id " +
                " where (book.title like ? or book.author like ?) and "  +
                " book.visible = true";
        try {
            String fixedQuery = "%" + query +"%";
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,fixedQuery);
            preparedStatement.setString(2,fixedQuery);

           ResultSet resultSet =  preparedStatement.executeQuery();
           while (resultSet.next()) {
               BookDTO bookDTO = new BookDTO();
               bookDTO.setId(resultSet.getInt("id"));
               bookDTO.setVisible(resultSet.getBoolean("visible"));
               bookDTO.setPublishedAt(resultSet.getDate("published_at").toLocalDate());
               bookDTO.setTitle(resultSet.getString("title"));
               bookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
//               bookDTO.setCategory_id(resultSet.getInt("category_id"));
               bookDTO.setAvailableDays(resultSet.getInt("available_days"));
               bookDTO.setAuthor(resultSet.getString("author"));

               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("cid"));
               categoryDTO.setName(resultSet.getString("cname"));
               bookDTO.setCategoryDTO(categoryDTO);
               bookDTOS.add(bookDTO);
           }
           return bookDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean delete(int id) {
        Connection connection = ConnectionUtil.getConnection();

        String sql = "update book set visible = false where id = ?";;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            var effectedRow =  preparedStatement.executeUpdate();
            return effectedRow != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public BookDTO getById(int id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from book where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
           ResultSet resultSet =  preparedStatement.executeQuery();
           if(resultSet.next()) {
               BookDTO bookDTO = new BookDTO();
               bookDTO.setId(resultSet.getInt("id"));
               bookDTO.setVisible(resultSet.getBoolean("visible"));
               bookDTO.setPublishedAt(resultSet.getDate("published_at").toLocalDate());
               bookDTO.setTitle(resultSet.getString("title"));
               bookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               bookDTO.setCategory_id(resultSet.getInt("category_id"));
               bookDTO.setAvailableDays(resultSet.getInt("available_days"));
               bookDTO.setAuthor(resultSet.getString("author"));
               return bookDTO;
           }
           return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
