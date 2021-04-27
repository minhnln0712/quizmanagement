/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import minhnln.dto.HistoryDTO;
import minhnln.dto.SubjectDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class HistoryDAO implements Serializable {

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

    List<HistoryDTO> list = null;

    public boolean createHistory(String HistoryID, String Email, String SubjectID, float Mark, Date createDate) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblHistory(HistoryID,Email,SubjectID,Mark,createDate) VALUES(?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, HistoryID);
            stm.setString(2, Email);
            stm.setString(3, SubjectID);
            stm.setFloat(4, Mark);
            stm.setDate(5, createDate);
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

    public String getLastHistoryID() throws Exception {
        String LastHistoryID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 HistoryID \n"
                    + "FROM tblHistory \n"
                    + "ORDER BY HistoryID DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                LastHistoryID = rs.getString("HistoryID");
            }
        } finally {
            CloseConnection();
            return LastHistoryID;
        }
    }

    public int getTotalOfHistory() throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(HistoryID) AS TOTAL "
                    + "FROM tblHistory ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public int getTotalOfHistorybyStudent(String email) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(HistoryID) AS TOTAL "
                    + "FROM tblHistory "
                    + "WHERE Email = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public List<HistoryDTO> displayAllHistoryWithPaging(int pageNo, int itemPerPage) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (SELECT ROW_NUMBER() OVER(ORDER BY HistoryID ASC) as STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "                 FROM tblHistory)\n"
                    + "SELECT STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*? ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, pageNo);
            stm.setInt(2, itemPerPage);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HistoryDTO(rs.getString("HistoryID"), rs.getString("Email"), rs.getString("SubjectID"), rs.getFloat("Mark"), rs.getDate("createDate")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<HistoryDTO> displayAllHistoryWithPagingbyStudent(int pageNo, int itemPerPage, String email) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (SELECT ROW_NUMBER() OVER(ORDER BY HistoryID ASC) as STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "                 FROM tblHistory)\n"
                    + "SELECT STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "FROM LIST\n "
                    + "WHERE Email = ? AND STT BETWEEN ?*?-(?-1) AND ?*? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setInt(2, pageNo);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, itemPerPage);
            stm.setInt(5, pageNo);
            stm.setInt(6, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HistoryDTO(rs.getString("HistoryID"), rs.getString("Email"), rs.getString("SubjectID"), rs.getFloat("Mark"), rs.getDate("createDate")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<HistoryDTO> searchHistorywithPaging(String SubjectID, int pageNo, int itemPerPage) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY HistoryID ASC) as STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "                     	FROM tblHistory \n"
                    + "				WHERE SubjectID LIKE ? )\n"
                    + "SELECT STT,STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setString(1, SubjectID);
            stm.setInt(2, pageNo);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, itemPerPage);
            stm.setInt(5, pageNo);
            stm.setInt(6, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HistoryDTO(rs.getString("HistoryID"), rs.getString("Email"), rs.getString("SubjectID"), rs.getFloat("Mark"), rs.getDate("createDate")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<HistoryDTO> searchHistorywithPagingbyStudent(String SubjectID, int pageNo, int itemPerPage, String email) throws Exception {
        list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY HistoryID ASC) as STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "                     	FROM tblHistory \n"
                    + "				WHERE SubjectID LIKE ? )\n"
                    + "SELECT STT,STT,HistoryID,Email,SubjectID,Mark,createDate\n"
                    + "FROM LIST\n"
                    + "WHERE Email = ? AND STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setString(1, SubjectID);
            stm.setString(2, email);
            stm.setInt(3, pageNo);
            stm.setInt(4, itemPerPage);
            stm.setInt(5, itemPerPage);
            stm.setInt(6, pageNo);
            stm.setInt(7, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new HistoryDTO(rs.getString("HistoryID"), rs.getString("Email"), rs.getString("SubjectID"), rs.getFloat("Mark"), rs.getDate("createDate")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getTotalOfHistoryAfterSearch(String SubjectID) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(HistoryID) AS TOTAL "
                    + "FROM tblHistory "
                    + "WHERE SubjectID LIKE ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, SubjectID);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public int getTotalOfHistoryAfterSearchbyStudent(String SubjectID, String email) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(HistoryID) AS TOTAL "
                    + "FROM tblHistory "
                    + "WHERE SubjectID LIKE ? AND Email = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, SubjectID);
            stm.setString(2, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
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
            list = new ArrayList<>();
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

    public String getLastHistoryDetailID() throws Exception {
        String LastHistoryDetailID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 HistoryDetailID \n"
                    + "FROM tblHistoryDetail \n"
                    + "ORDER BY HistoryDetailID DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                LastHistoryDetailID = rs.getString("HistoryDetailID");
            }
        } finally {
            CloseConnection();
            return LastHistoryDetailID;
        }
    }
}
