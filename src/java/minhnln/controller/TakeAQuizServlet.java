/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.dao.AnswerDAO;
import minhnln.dao.QuestionDAO;
import minhnln.dao.RandomQuizDAO;
import minhnln.dto.AnswerDTO;
import minhnln.dto.QuestionDTO;
import minhnln.dto.RandomQuizDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "TakeAQuizServlet", urlPatterns = {"/TakeAQuizServlet"})
public class TakeAQuizServlet extends HttpServlet {

    private final String DO_QUIZ_PAGE = "doquiz.jsp";
    private final String DISPLAY_QUIZ_SERVLET = "DisplayQuizServlet";
    private final String ERROR_PAGE = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;

        String timeString = request.getParameter("cbbTime");
        String subjectID = request.getParameter("txtSubjectID");
        try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role.equals("Student")) {
                url = DO_QUIZ_PAGE;
                QuestionDAO Qdao = new QuestionDAO();
                AnswerDAO Adao = new AnswerDAO();
                //Về Số lượng câu hỏi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                int NumOfQuestionBaseTime = 0;
                if (timeString.equals("60:00")) {
                    NumOfQuestionBaseTime = 60;
                } else if (timeString.equals("45:00")) {
                    NumOfQuestionBaseTime = 45;
                } else if (timeString.equals("30:00")) {
                    NumOfQuestionBaseTime = 30;
                } else if (timeString.equals("15:00")) {
                    NumOfQuestionBaseTime = 15;
                } else if (timeString.equals("10:00")) {
                    NumOfQuestionBaseTime = 10;
                } else if (timeString.equals("05:00")) {
                    NumOfQuestionBaseTime = 5;
                }
                //Nếu số lượng câu hỏi chọn k đủ thì quăng lỗi
                if (NumOfQuestionBaseTime > Qdao.numQuestionbaseSubjectID(subjectID)) {
                    request.setAttribute("ERROR", "The number of the question is not enough to take a test!!!");
                    url = DISPLAY_QUIZ_SERVLET;
                    return;
                }
                //Về thời gian
                Calendar now = Calendar.getInstance();///Thời gian hiện tại
                now.add(Calendar.MINUTE, NumOfQuestionBaseTime);//Thời gian hiện tại + n phút
                Date QuizTime = now.getTime();//Chuyển từ dạng calendar sang Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");//Đổi sang định dạng Date cho JavaScript
                String QuizTimeString = dateFormat.format(QuizTime);
                //Về hiển thị~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                String[] QuestionIDArray = Qdao.getRandomQuestionID(NumOfQuestionBaseTime, subjectID);
                List<RandomQuizDTO> RQList = new ArrayList<RandomQuizDTO>();
                //Xử lý RandomID
                for (int i = 0; i < QuestionIDArray.length; i++) {
                    RQList.add(new RandomQuizDTO((i + 1), QuestionIDArray[i], ""));
                }
                url = DO_QUIZ_PAGE;
                int RandomID = 1;

                List<QuestionDTO> Qlist = Qdao.getAllQuestion();
                Map<String, List<AnswerDTO>> questionmap = new HashMap<>();
                for (int i = 0; i < Qlist.size(); i++) {
                    String QuestionID = Qlist.get(i).getQuestionID();
                    List<AnswerDTO> Alist = Adao.displayAnswerPerQuestion(QuestionID);
                    questionmap.put(QuestionID, Alist);
                }

                session.setAttribute("RQLIST", RQList);
                request.setAttribute("QLIST", Qlist);
                request.setAttribute("MAP", questionmap);
                request.setAttribute("QUIZPOS", RandomID);
                session.setAttribute("SUBJECTID", subjectID);
                session.setAttribute("QUIZTIME", QuizTimeString);
                session.setAttribute("NUMOFQUESTION", NumOfQuestionBaseTime);
            }
        } catch (Exception e) {
            log("Error at Take a Quiz Servlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
