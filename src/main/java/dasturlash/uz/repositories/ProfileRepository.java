package dasturlash.uz.repositories;

import dasturlash.uz.dtos.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.utils.ConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;

public class ProfileRepository {
    public ProfileDTO getByLogin(String login) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from profile where login = ?";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
           ResultSet resultSet =  preparedStatement.executeQuery();
           if(resultSet.next()) {
               ProfileDTO profileDTO = new ProfileDTO();
               profileDTO.setId(resultSet.getInt("id"));
               profileDTO.setLogin(resultSet.getString("login"));
               profileDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               profileDTO.setName(resultSet.getString("name"));
               profileDTO.setPassword(resultSet.getString("password"));
               profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
               profileDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
               profileDTO.setSurname(resultSet.getString("surname"));
               profileDTO.setUpdatedAt(null);

               return profileDTO;
           }
           return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Boolean add(ProfileDTO adminProfile) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into profile(name,surname,password,login,role,status,created_at,updated_at)" +
                "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,adminProfile.getName());
            preparedStatement.setString(2, adminProfile.getSurname());
            preparedStatement.setString(3,adminProfile.getPassword());
            preparedStatement.setString(4, adminProfile.getLogin());
            preparedStatement.setString(5, String.valueOf(adminProfile.getRole()));
            preparedStatement.setString(6, String.valueOf(adminProfile.getStatus()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(8,null);

           var effectedRow =  preparedStatement.executeUpdate();
            return effectedRow != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
