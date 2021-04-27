/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.dao.HistoryDAO;
import minhnln.dao.HistoryDetailDAO;
import minhnln.dao.RandomQuizDAO;
import minhnln.dto.RandomQuizDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {

    private final String RESULT_PAGE = "result.jsp";
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
        try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role.equals("Student")) {
                RandomQuizDAO RQdao = new RandomQuizDAO();
                HistoryDAO Hdao = new HistoryDAO();
                HistoryDetailDAO HDdao = new HistoryDetailDAO();
                //SubjectID
                String SubjectID = (String) session.getAttribute("SUBJECTID");
                //Xử lý HistoryID
                String HistoryID = Hdao.getLastHistoryID();
                if (HistoryID.isEmpty()) {
                    HistoryID = "H" + "-0000001";
                } else {
                    String[] arrayHistoryID = HistoryID.split("-");
                    HistoryID = "H" + "-" + String.format("%07d", (Integer.parseInt(arrayHistoryID[1]) + 1));
                }
                //Email
                String Email = (String) session.getAttribute("EMAIL");
                //Mark 
                List<RandomQuizDTO> RQList = (List<RandomQuizDTO>) session.getAttribute("RQLIST");
                int NumOfQuestion = (int) session.getAttribute("NUMOFQUESTION");
                float Mark = RQdao.getMark(NumOfQuestion, RQList);
                //Date
                Date createDate = Calendar.getInstance().getTime();
                java.sql.Date createDateSQL = new java.sql.Date(createDate.getTime());
                if (Hdao.createHistory(HistoryID, Email, SubjectID, Mark, createDateSQL)) {
                    for (int i = 0; i < RQList.size(); i++) {
                        String HistoryDetailID = HDdao.getLastHistoryDetailIDbyHistoryID(HistoryID);
                        if (HistoryDetailID.isEmpty()) {
                            HistoryDetailID = HistoryID + "-1";
                        } else {
                            String[] arrayHistoryDetailID = HistoryDetailID.split("-");
                            HistoryDetailID = HistoryID + "-" + String.format("%d", (Integer.parseInt(arrayHistoryDetailID[2]) + 1));
                        }
                        String QuestionID = RQList.get(i).getQuestionID();
                        String AnswerChoseID = RQList.get(i).getChoseAnswerID();
                        if (AnswerChoseID.trim().isEmpty()) {
                            AnswerChoseID = "Q-0000000-0";
                        }
                        if (HDdao.createHistoryDetail(HistoryDetailID, HistoryID, QuestionID, AnswerChoseID)) {
                            url = RESULT_PAGE;
                        } else {
                            url = ERROR_PAGE;
                        }
                    }
                    request.setAttribute("MARK", Mark);
                    session.removeAttribute("RQLIST");
                    session.removeAttribute("SUBJECTID");
                    session.removeAttribute("QUIZTIME");
                    session.removeAttribute("NUMOFQUESTION");
                }
            }
        } catch (Exception e) {
            log("Error at SubmitQuizServlet:" + e.getMessage());
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
