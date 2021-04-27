/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import minhnln.db.db;
import minhnln.dto.RegistrationDTO;

/**
 *
 * @author Welcome
 */
public class RegistrationDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void CloseConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean signup(String email, String name, String password) throws Exception {
        boolean status = false;
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblRegistration(Email,Name,Password,RoleID,Status) VALUES(?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setString(3, password);
            stm.setString(4, "2");
            stm.setString(5, "New");
            int row = stm.executeUpdate();
            if (row > 0) {
                status = true;
            }
        } finally {
            CloseConnection();
            return status;
        }
    }

    public RegistrationDTO login(String email, String password) throws Exception {
        RegistrationDTO dto = null;
        try {
            con = db.openConnection();
            String sql = "SELECT RE.Email,RE.Name,RO.RoleName,RE.Status "
                    + "FROM tblRegistration RE JOIN tblRole RO ON RE.RoleID = RO.RoleID "
                    + "WHERE RE.Email = ? AND RE.Password = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new RegistrationDTO(email, rs.getString("Name"), rs.getString("Status"), rs.getString("RoleName"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            CloseConnection();
            return dto;
        }
    }

    public boolean checkEmailExisted(String email) throws Exception {
        boolean exist = false;
        try {
            con = db.openConnection();
            String sql = "SELECT Email "
                    + "FROM tblRegistration "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } finally {
            CloseConnection();
            return exist;
        }
    }

    public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
