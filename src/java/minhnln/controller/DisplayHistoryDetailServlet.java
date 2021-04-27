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
import minhnln.dao.HistoryDetailDAO;
import minhnln.dao.QuestionDAO;
import minhnln.dto.AnswerDTO;
import minhnln.dto.HistoryDetailDTO;
import minhnln.dto.QuestionDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "DisplayHistoryDetailServlet", urlPatterns = {"/DisplayHistoryDetailServlet"})
public class DisplayHistoryDetailServlet extends HttpServlet {

    private final String HISTORY_DETAIL_PAGE = "historydetail.jsp";
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
            if (!role.isEmpty()) {
                String historyID = request.getParameter("historyID");
                HistoryDetailDAO HDdao = new HistoryDetailDAO();
                AnswerDAO Adao = new AnswerDAO();
                QuestionDAO Qdao = new QuestionDAO();
                List<QuestionDTO> Qlist = Qdao.getAllQuestion();
                List<HistoryDetailDTO> HDlist = HDdao.displayDetailbyHistoryID(historyID);
                Map<String, List<AnswerDTO>> questionmap = new HashMap<>();
                for (int i = 0; i < HDlist.size(); i++) {
                    String QuestionID = HDlist.get(i).getQuestionID();
                    List<AnswerDTO> Alist = Adao.displayAnswerPerQuestion(QuestionID);
                    questionmap.put(QuestionID, Alist);
                }
                url = HISTORY_DETAIL_PAGE;
                request.setAttribute("MAP", questionmap);
                request.setAttribute("HDLIST", HDlist);
                request.setAttribute("QLIST", Qlist);
            }
        } catch (Exception e) {
            log("Error at DisplayHistoryDetailServlet: " + e.getMessage());
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
