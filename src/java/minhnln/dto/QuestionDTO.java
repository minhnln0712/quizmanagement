/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Welcome
 */
public class QuestionDTO implements Serializable {

    private String questionID;
    private String questionContent;
    private Date createDate;
    private String subjectID;
    private String status;

    public QuestionDTO(String questionID, String questionContent, Date createDate, String subjectID, String status) {
        this.questionID = questionID;
        this.questionContent = questionContent;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    /**
     * @return the questionID
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * @param questionContent the questionContent to set
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
