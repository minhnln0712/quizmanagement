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
public class HistoryDTO implements Serializable {

    private String HistoryID, Email, SubjectID;
    private float Mark;
    private Date createDate;

    public HistoryDTO() {
    }

    public HistoryDTO(String HistoryID, String Email, String SubjectID, float Mark, Date createDate) {
        this.HistoryID = HistoryID;
        this.Email = Email;
        this.SubjectID = SubjectID;
        this.Mark = Mark;
        this.createDate = createDate;
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
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the SubjectID
     */
    public String getSubjectID() {
        return SubjectID;
    }

    /**
     * @param SubjectID the SubjectID to set
     */
    public void setSubjectID(String SubjectID) {
        this.SubjectID = SubjectID;
    }

    /**
     * @return the Mark
     */
    public float getMark() {
        return Mark;
    }

    /**
     * @param Mark the Mark to set
     */
    public void setMark(float Mark) {
        this.Mark = Mark;
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

}
