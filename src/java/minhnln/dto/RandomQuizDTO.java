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
public class RandomQuizDTO implements Serializable{

    private int RandomID;
    private String QuestionID;
    private String ChoseAnswerID;

    public RandomQuizDTO() {
    }

    public RandomQuizDTO(int RandomID, String QuestionID, String ChoseAnswerID) {
        this.RandomID = RandomID;
        this.QuestionID = QuestionID;
        this.ChoseAnswerID = ChoseAnswerID;
    }

    /**
     * @return the RandomID
     */
    public int getRandomID() {
        return RandomID;
    }

    /**
     * @param RandomID the RandomID to set
     */
    public void setRandomID(int RandomID) {
        this.RandomID = RandomID;
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
     * @return the ChoseAnswerID
     */
    public String getChoseAnswerID() {
        return ChoseAnswerID;
    }

    /**
     * @param ChoseAnswerID the ChoseAnswerID to set
     */
    public void setChoseAnswerID(String ChoseAnswerID) {
        this.ChoseAnswerID = ChoseAnswerID;
    }

}
