/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.dao.RegistrationDAO;
import minhnln.dto.RegistrationDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String SIGN_UP_PAGE = "signup.jsp";
    private final String DISPLAY_QUESTION_SERVLET = "DisplayQuestionServlet";
    private final String DISPLAY_QUIZ_LIST_SERVLET = "DisplayQuizServlet";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String logingoogle = request.getParameter("logingoogle");
        String url = ERROR_PAGE;
        boolean geterror = false;
        try {
            RegistrationDAO Rdao = new RegistrationDAO();
            HttpSession session = request.getSession();
            //Check Valid
            if (!email.matches("\\w+[@]\\w+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,4}$")) {
                request.setAttribute("ERROR", "Wrong Email Type!! Example:nhatminh@gmail.com.vn");
                url = LOGIN_PAGE;
                geterror = true;
                return;
            }
            //Check xem gmail này đã tồn tại trong hệ thống chưa,chưa thì chuyển yêu cầu qua dký
            if (!logingoogle.isEmpty()) {
                if (!Rdao.checkEmailExisted(email)) {
                    url = SIGN_UP_PAGE;
                    return;
                }
            }
            //Xử lý Password
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String passInDatabase = Rdao.bytesToHex(encodedhash);
            if (!geterror) {
                RegistrationDTO dto = Rdao.login(email, passInDatabase);
                if (dto != null) {
                    session.setAttribute("NAME", dto.getName());
                    session.setAttribute("ROLE", dto.getRole());
                    session.setAttribute("EMAIL", email);
                    if (dto.getRole().equals("Admin")) {
                        url = DISPLAY_QUESTION_SERVLET;
                    } else {
                        url = DISPLAY_QUIZ_LIST_SERVLET;
                    }
                } else {
                    request.setAttribute("ERROR", "Email or Password are NOT existed! Please try again!");
                    geterror = true;
                    url = LOGIN_PAGE;
                    return;
                }
            }
        } catch (Exception e) {
            log("Error At LoginServlet" + e.getMessage());
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
