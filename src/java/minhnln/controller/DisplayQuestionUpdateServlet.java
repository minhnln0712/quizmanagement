/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.dao.AnswerDAO;
import minhnln.dao.QuestionDAO;
import minhnln.dao.SubjectDAO;
import minhnln.dto.AnswerDTO;
import minhnln.dto.QuestionDTO;
import minhnln.dto.SubjectDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "DisplayQuestionUpdateServlet", urlPatterns = {"/DisplayQuestionUpdateServlet"})
public class DisplayQuestionUpdateServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String UPDATE_PAGE = "update.jsp";

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
                QuestionDAO Qdao = new QuestionDAO();
                AnswerDAO Adao = new AnswerDAO();
                SubjectDAO Sdao = new SubjectDAO();
                List<QuestionDTO> Qlist = Qdao.getAllQuestion();
                Map<String, List<AnswerDTO>> answermap = new HashMap<>();
                for (int i = 0; i < Qlist.size(); i++) {
                    String QuestionID = Qlist.get(i).getQuestionID();
                    List<AnswerDTO> Alist = Adao.displayAnswerPerQuestion(QuestionID);
                    answermap.put(QuestionID, Alist);
                }
                List<SubjectDTO> Slist = Sdao.getAllSubject();
                request.setAttribute("QLIST", Qlist);
                request.setAttribute("MAP", answermap);
                request.setAttribute("SLIST", Slist);
                request.setAttribute("UPDATEID", request.getParameter("txtQuestionID"));
                request.setAttribute("QUESTIONNAME", request.getParameter("txtQuestionName"));
                request.setAttribute("STATUS", request.getParameter("cboStatus"));
                request.setAttribute("SUBJECT", request.getParameter("cboSubject"));
                request.setAttribute("PAGENO", request.getParameter("pageNo"));
                url = UPDATE_PAGE;
            }
        } catch (Exception e) {
            log("Error at DisplayQuestionUpdateServlet: " + e.getMessage());
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
