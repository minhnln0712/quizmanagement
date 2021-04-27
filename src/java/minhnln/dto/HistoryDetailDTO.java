/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.dto;

import java.io.Serializable;

/**
 *
 * @author Welcome
 */
public class HistoryDetailDTO implements Serializable{
    private String HistoryDetailID,HistoryID,QuestionID,AnswerChoseID;

    public HistoryDetailDTO() {
    }

    public HistoryDetailDTO(String HistoryDetailID, String HistoryID, String QuestionID, String AnswerChoseID) {
        this.HistoryDetailID = HistoryDetailID;
        this.HistoryID = HistoryID;
        this.QuestionID = QuestionID;
        this.AnswerChoseID = AnswerChoseID;
    }

    /**
     * @return the HistoryDetailID
     */
    public String getHistoryDetailID() {
        return HistoryDetailID;
    }

    /**
     * @param HistoryDetailID the HistoryDetailID to set
     */
    public void setHistoryDetailID(String HistoryDetailID) {
        this.HistoryDetailID = HistoryDetailID;
    }

    /**
     * @return the HistoryID
     */
    public String getHistoryID() {
        return HistoryID;
    }

    /**
     * @param HistoryID the HistoryID to set
     */
    public void setHistoryID(String HistoryID) {
        this.HistoryID = HistoryID;
    }

    /**
     * @return the QuestionID
     */
    public String getQuestionID() {
        return QuestionID;
    }

    /**
     * @param QuestionID the QuestionID to set
     */
    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    /**
     * @return the AnswerChoseID
     */
    public String getAnswerChoseID() {
        return AnswerChoseID;
    }

    /**
     * @param AnswerChoseID the AnswerChoseID to set
     */
    public void setAnswerChoseID(String AnswerChoseID) {
        this.AnswerChoseID = AnswerChoseID;
    }
    
}
