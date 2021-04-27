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
import java.util.ArrayList;
import java.util.List;
import minhnln.dto.HistoryDetailDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class HistoryDetailDAO implements Serializable {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
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
    
    public boolean createHistoryDetail(String DetailID, String HistoryID, String QuestionID, String AnswerChoseID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblHistoryDetail(HistoryDetailID,HistoryID,QuestionID,AnswerChoseID) VALUES(?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, DetailID);
            stm.setString(2, HistoryID);
            stm.setString(3, QuestionID);
            stm.setString(4, AnswerChoseID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            CloseConnection();
        }
    }
    
    public String getLastHistoryDetailIDbyHistoryID(String HistoryID) throws Exception {
        String LastHistoryDetailID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 HistoryDetailID \n"
                    + "FROM tblHistoryDetail "
                    + "WHERE HistoryID = ?\n"
                    + "ORDER BY HistoryDetailID DESC";
            stm = con.prepareStatement(sql);
            stm.setString(1, HistoryID);
            rs = stm.executeQuery();
            if (rs.next()) {
                LastHistoryDetailID = rs.getString("HistoryDetailID");
            }
        } finally {
            CloseConnection();
            return LastHistoryDetailID;
        }
    }
    List<HistoryDetailDTO> list;
    
    public List<HistoryDetailDTO> displayDetailbyHistoryID(String HistoryID) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "SELECT HistoryDetailID,HistoryID,QuestionID,AnswerChoseID "
                    + "FROM tblHistoryDetail "
                    + "WHERE HistoryID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, HistoryID);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HistoryDetailDTO(rs.getString("HistoryDetailID"), rs.getString("HistoryID"), rs.getString("QuestionID"), rs.getString("AnswerChoseID")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }
}
