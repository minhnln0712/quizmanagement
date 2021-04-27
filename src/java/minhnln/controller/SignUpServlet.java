/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.sql.SQLException;
import minhnln.dao.RegistrationDAO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String REGISTRATION_PAGE = "signup.jsp";
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
        boolean geterror = false;
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String confirmpassword = request.getParameter("txtConfirmPassword");
        try {
            //Check Valid
            if (!email.matches("\\w+[@]\\w+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,4}$")) {
                request.setAttribute("ERROR", "Wrong Email Type!! Example:nhatminh@gmail.com.vn");
                url = REGISTRATION_PAGE;
                geterror = true;
                return;
            }
            if (!confirmpassword.equals(password)) {
                request.setAttribute("ERROR", "Confirm password must be exactly like password!!");
                url = REGISTRATION_PAGE;
                geterror = true;
                return;
            }
            //Lưu trữ thông tin nhập liệu
            RegistrationDAO dao = new RegistrationDAO();
            //Xử lý mã hóa Mật khẩu
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String passInDatabse = dao.bytesToHex(encodedhash);
            if (!geterror) {
                boolean signup = dao.signup(email, name, passInDatabse);
                if (signup) {
                    url = LOGIN_PAGE;
                } else {
                    request.setAttribute("ERROR", "This Email is already in List!");
                    url = REGISTRATION_PAGE;
                    geterror = true;
                    return;
                }
            }
        } catch (Exception e) {
            log("Error at SignUp Servlet: " + e.getMessage());
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
