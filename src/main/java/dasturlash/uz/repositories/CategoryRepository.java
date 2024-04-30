package dasturlash.uz.repositories;

import dasturlash.uz.dtos.CategoryDTO;
import dasturlash.uz.utils.ConnectionUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryRepository {
    public CategoryDTO getByName(String name) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from category where name = ?";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
           ResultSet resultSet =  preparedStatement.executeQuery();
           if(resultSet.next()) {
               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("id"));
               categoryDTO.setName(resultSet.getString("name"));
               categoryDTO.setVisible(resultSet.getBoolean("visible"));
               categoryDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               return categoryDTO;
           }
           return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean add(CategoryDTO categoryDTO) {

        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into category(name,visible,created_at) values(?,?,?)";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,categoryDTO.getName());
            preparedStatement.setBoolean(2,categoryDTO.getVisible());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(categoryDTO.getCreatedAt()));
           int effectedRow =  preparedStatement.executeUpdate();
            return effectedRow != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoryDTO> allCategories() {
        List<CategoryDTO> categoryDTOS = new LinkedList<>();
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from category where visible = true";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
           ResultSet resultSet =  preparedStatement.executeQuery();
           while (resultSet.next()) {
               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("id"));
               categoryDTO.setName(resultSet.getString("name"));
               categoryDTO.setVisible(Boolean.valueOf(resultSet.getString("visible")));
               categoryDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               categoryDTOS.add(categoryDTO);
           }
           return categoryDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean delete(String name) {
        Connection connection = ConnectionUtil.getConnection();

        String sql = "update category set visible = false where name = ?";;
        try {
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,name);
           var effectedRow =  preparedStatement.executeUpdate();
            return effectedRow != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
