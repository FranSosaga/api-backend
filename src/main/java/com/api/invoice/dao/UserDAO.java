package com.api.invoice.dao;


import com.api.invoice.dto.UserDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    private final String ISERT_USER = "INSERT INTO Client (name, surname, typeDoc, document, country, location, address, postalcode , phone, email) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SELECT_USERS = "SELECT * FROM Client c;";
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser( UserDTO user){
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(ISERT_USER);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getTypeDoc());
                ps.setString(4, user.getDocument());
                ps.setString(5, user.getCountry());
                ps.setString(6, user.getLocation());
                ps.setString(7, user.getAddress());
                ps.setLong(8, user.getPostalCode());
                ps.setString(9, user.getPhone());
                ps.setString(10, user.getEmail());
                System.out.println(ps);
                return ps;
            });
        }catch (DataAccessException e){
            System.out.println("Error al ingresar cliente: "+e);
        }
    }


    public List<UserDTO> getUser() {
        try {
            return jdbcTemplate.query(SELECT_USERS, (rs, rowNum) -> mapToCart(rs));
        }catch (DataAccessException e){
            System.out.println("Error al ingresar cliente: "+e);
            return null;
        }
    }

    private UserDTO mapToCart(ResultSet rs) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO.setName(rs.getString("name"));
            userDTO.setSurname(rs.getString("surname"));
            userDTO.setTypeDoc(rs.getString("typeDoc"));
            userDTO.setDocument(rs.getString("document"));
            userDTO.setCountry(rs.getString("country"));
            userDTO.setAddress(rs.getString("address"));
            userDTO.setPostalCode(rs.getInt("postalcode"));
            userDTO.setId(rs.getInt("id"));
            userDTO.setEmail(rs.getString("email"));
            userDTO.setPhone(rs.getString("phone"));
            userDTO.setLocation(rs.getString("location"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDTO;

    }
}
