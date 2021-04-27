/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import minhnln.dto.AnswerDTO;
import minhnln.dto.QuestionDTO;
import minhnln.dto.RandomQuizDTO;

/**
 *
 * @author Welcome
 */
public class RandomQuizDAO implements Serializable {

    QuestionDAO Qdao = new QuestionDAO();
    AnswerDAO Adao = new AnswerDAO();

    public void setChoseAnswerID(String choseAnswerID, int QuestionPos, List<RandomQuizDTO> RQlist) throws Exception {
        for (int i = 0; i < RQlist.size(); i++) {
            RQlist.get(QuestionPos - 1).setChoseAnswerID(choseAnswerID);
        }
    }

    public float getMark(int NumofQuestion, List<RandomQuizDTO> RQlist) throws Exception {
        float MarkPerQuestion = 10 / NumofQuestion;
        int CorrectAnswer = 0;
        float Mark = 0;
        List<AnswerDTO> Alist = Adao.getAllCorrectAnswer();
        for (int i = 0; i < Alist.size(); i++) {
            for (int j = 0; j < RQlist.size(); j++) {
                if (Alist.get(i).getAnswerID().equals(RQlist.get(j).getChoseAnswerID())) {
                    CorrectAnswer++;
                }
            }
        }
        Mark = CorrectAnswer * MarkPerQuestion;
        return Mark;
    }
}
