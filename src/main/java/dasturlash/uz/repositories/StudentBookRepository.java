package dasturlash.uz.repositories;

import dasturlash.uz.dtos.BookDTO;
import dasturlash.uz.dtos.CategoryDTO;
import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.dtos.StudentBookDTO;
import dasturlash.uz.utils.ConnectionUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentBookRepository {
    public Boolean add(StudentBookDTO studentBookDTO) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into studentBook(student_id,book_id,created_at,returned_at,status)" +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,studentBookDTO.getStudentId());
            preparedStatement.setInt(2,studentBookDTO.getBookId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(studentBookDTO.getCreatedAt()));
            preparedStatement.setTimestamp(4,null);
            preparedStatement.setString(5, String.valueOf(studentBookDTO.getStatus()));
           var effectedRow =  preparedStatement.executeUpdate();
           return effectedRow !=0 ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<StudentBookDTO> booksOnHand(Integer studentId) {
        List<StudentBookDTO> studentBookDTOS = new LinkedList<>();
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select  studentBook.id,profile.id as profile_id,profile.name as profile_name,book.id as book_id,book.title as book_title," +
                " book.category_id as category_id, " +
                "category.name as category_name, book.available_days as available_days," +
                " studentBook.created_at,book.author as book_author from studentBook " +
                "inner join book on studentBook.book_id = book.id " +
                "inner join profile on profile.id = studentBook.student_id " +
                " inner join category on category.id = book.category_id" +
                " where studentBook.status = 'TAKEN' and profile.id = ?";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setInt(1,studentId);
           ResultSet resultSet =  preparedStatement.executeQuery();
           if(resultSet.next()) {
               StudentBookDTO studentBookDTO = new StudentBookDTO();
               studentBookDTO.setId(resultSet.getInt("id"));
               studentBookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               studentBookDTO.setReturnedAt(null);

               BookDTO bookDTO = new BookDTO();
               bookDTO.setId(resultSet.getInt("book_id"));
               bookDTO.setTitle(resultSet.getString("book_title"));
               bookDTO.setAuthor(resultSet.getString("book_author"));
               bookDTO.setAvailableDays(resultSet.getInt("available_days"));

               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("category_id"));
               categoryDTO.setName(resultSet.getString("category_name"));

               bookDTO.setCategoryDTO(categoryDTO);

               ProfileDTO profileDTO = new ProfileDTO();
               profileDTO.setId(resultSet.getInt("profile_id"));
               profileDTO.setName(resultSet.getString("profile_name"));

               studentBookDTO.setProfileDTO(profileDTO);
               studentBookDTO.setBookDTO(bookDTO);
               studentBookDTOS.add(studentBookDTO);
           }
            return studentBookDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
