/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.dao.AnswerDAO;
import minhnln.dao.QuestionDAO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

    private final String DISPLAY_SERVLET = "DisplayQuestionServlet";
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
            if (role.equals("Admin")) {
                String questionID = request.getParameter("txtQuestionID");
                String questionContent = request.getParameter("txtQuestionContent");
                String subject = request.getParameter("cboSubject");
                String[] answerIDArray = request.getParameterValues("txtAnswerID");
                String[] answerContentArray = request.getParameterValues("txtAnswerContent");
                QuestionDAO Qdao = new QuestionDAO();
                AnswerDAO Adao = new AnswerDAO();
                if (Qdao.updateQuestion(questionID, questionContent, subject)) {
                    for (int i = 0; i < answerIDArray.length; i++) {
                        String status = "";
                        if (request.getParameter("chkStatus" + i) == null) {
                            status = "0";
                        } else {
                            status = "1";
                        }
                        if (Adao.updateAnswer(answerIDArray[i], answerContentArray[i], status)) {
                            String pageNo = request.getParameter("pageNo");
                            String questionname = request.getParameter("txtQuestionName");
                            String statuss = request.getParameter("cboStatuss");
                            String subjectt = request.getParameter("cboSubjectt");
                            url = "SearchQuestionServlet?pageNo=" + pageNo + "&txtQuestionName=" + questionname + "&cboStatus=" + statuss + "&cboSubject=" + subjectt;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at UpdateQuestionServlet: " + e.getMessage());
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
