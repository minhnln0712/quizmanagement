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
import minhnln.dto.QuestionDTO;
import minhnln.db.db;

/**
 *
 * @author Welcome
 */
public class QuestionDAO implements Serializable {

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

    public List<QuestionDTO> getAllQuestion() throws Exception {
        List<QuestionDTO> list = null;
        try {
            con = db.openConnection();
            String sql = "SELECT QuestionID,QuestionContent,CreateDate,SubjectID,Status "
                    + "FROM tblQuestion ";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getString("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getTotalOfQuestion() throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(QuestionID) AS TOTAL "
                    + "FROM tblQuestion "
                    + "WHERE Status = 1";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            CloseConnection();
            return total;
        }
    }

    public List<QuestionDTO> displayAllQuestionWithPaging(int pageNo, int itemPerPage) throws Exception {
        List<QuestionDTO> list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY QuestionContent DESC) as STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "				FROM tblQuestion "
                    + "                         WHERE Status = 1)\n"
                    + "SELECT STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, pageNo);
            stm.setInt(2, itemPerPage);
            stm.setInt(3, itemPerPage);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getString("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public List<QuestionDTO> searchQuestionwithPaging(String keyword, String status, String SubjectID, int pageNo, int itemPerPage) throws Exception {
        List<QuestionDTO> list = null;
        try {
            con = db.openConnection();
            String sql = "WITH LIST AS (	SELECT ROW_NUMBER() OVER(ORDER BY QuestionContent DESC) as STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "				FROM tblQuestion "
                    + "                         WHERE QuestionContent LIKE ? AND Status LIKE ? AND SubjectID LIKE ? )\n"
                    + "SELECT STT,QuestionID,QuestionContent,CreateDate,SubjectID,Status\n"
                    + "FROM LIST\n"
                    + "WHERE STT BETWEEN ?*?-(?-1) AND ?*? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, status);
            stm.setString(3, SubjectID);
            stm.setInt(4, pageNo);
            stm.setInt(5, itemPerPage);
            stm.setInt(6, itemPerPage);
            stm.setInt(7, pageNo);
            stm.setInt(8, itemPerPage);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new QuestionDTO(rs.getString("QuestionID"), rs.getString("QuestionContent"), rs.getDate("CreateDate"), rs.getString("SubjectID"), rs.getString("Status")));
            }
        } finally {
            CloseConnection();
            return list;
        }
    }

    public int getTotalOfQuestionAfterSearch(String keyword, String status, String SubjectID) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(QuestionID) AS TOTAL "
                    + "FROM tblQuestion "
                    + "WHERE QuestionContent LIKE ? AND Status LIKE ? AND SubjectID LIKE ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, status);
            stm.setString(3, SubjectID);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public String getLastQuestionID() throws Exception {
        String lastquestionID = "";
        try {
            con = db.openConnection();
            String sql = "SELECT TOP 1 QuestionID "
                    + "FROM tblQuestion "
                    + "ORDER BY QuestionID DESC";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                lastquestionID = rs.getString("QuestionID");
            }
        } finally {
            CloseConnection();
            return lastquestionID;
        }
    }

    public boolean createQuestion(String QuestionID, String QuestionContent, Date CreateDate, String SubjectID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) "
                    + "VALUES(?,?,?,?,1)";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
            stm.setString(2, QuestionContent);
            stm.setDate(3, CreateDate);
            stm.setString(4, SubjectID);
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

    public boolean deleteQuestion(String QuestionID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblQuestion \n"
                    + "SET Status = 0\n"
                    + "WHERE QuestionID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
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

    public boolean revertQuestion(String QuestionID) throws Exception {
        try {
            con = db.openConnection();
            String sql = "UPDATE tblQuestion \n"
                    + "SET Status = 1\n"
                    + "WHERE QuestionID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, QuestionID);
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

    public boolean updateQuestion(String QuestionID, String QuestionContent, String SubjectID) throws Exception {
        boolean success = false;
        try {
            con = db.openConnection();
            String sql = "UPDATE tblQuestion \n"
                    + "	SET QuestionContent = ? ,SubjectID = ?\n"
                    + "	WHERE QuestionID = ?";
            stm = con.prepareStatement(sql);
            stm.setNString(1, QuestionContent);
            stm.setString(2, SubjectID);
            stm.setString(3, QuestionID);
            int row = stm.executeUpdate();
            if (row > 0) {
                success = true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            CloseConnection();
            return success;
        }
    }

    public String[] getRandomQuestionID(int NumOfQuestion, String subjectID) throws Exception {
        String[] questionIDArray = null;
        List<String> questionList = null;
        try {
            con = db.openConnection();
            String sql = "SELECT TOP (?) QuestionID "
                    + "FROM tblQuestion "
                    + "WHERE SubjectID = ? AND Status = 1 "
                    + "ORDER BY NEWID() ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, NumOfQuestion);
            stm.setString(2, subjectID);
            rs = stm.executeQuery();
            questionList = new ArrayList<>();
            while (rs.next()) {
                questionList.add(rs.getString("QuestionID"));
            }
            questionIDArray = questionList.toArray(new String[0]);
        } finally {
            CloseConnection();
            return questionIDArray;
        }
    }

    public String getContentBaseID(String QuestionID, List<QuestionDTO> Qlist) throws Exception {
        String QuestionContent = "";
        try {
            for (int i = 0; i < Qlist.size(); i++) {
                if (Qlist.get(i).getQuestionID().equals(QuestionID)) {
                    QuestionContent = Qlist.get(i).getQuestionContent();
                }
            }
        } finally {
            return QuestionContent;
        }
    }

    public int numQuestionbaseSubjectID(String subjectID) throws Exception {
        int total = 0;
        try {
            con = db.openConnection();
            String sql = "SELECT COUNT(QuestionID) AS TOTAL "
                    + "FROM tblQuestion "
                    + "WHERE Status = 1 AND SubjectID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectID);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }
        } finally {
            CloseConnection();
            return total;
        }
    }

    public boolean checkStringInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).matches("[a-zA-Z0-9]*")) {
            } else if (input.substring(i, i + 1).matches("\\s")) {
            } else {
                return true;
            }
        }
        return false;
    }
}
