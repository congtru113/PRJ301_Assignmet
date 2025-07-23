/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;
import utils.DbUtils;
import utils.PasswordUtils;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
        String url = "login.jsp";
        try{
            String action = request.getParameter("action");
            if("login".equals(action)){
                url = handleLogin(request, response);
            }else if ("logout".equals(action)){
                url = handleLogout(request, response);
            }else if ("register".equals(action)){
                url = handleRegister(request, response);
            }else {
                request.setAttribute("message", "Invalid action: " + action);
                url = "login.jsp";
            }
        }catch (Exception e){
        }finally {
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

    private String handleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "login.jsp";
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = PasswordUtils.encryptSHA256(password);
        UserDAO dao = new UserDAO();
        UserDTO user = dao.checkLogin(username, password);
        if (user != null) {
            url = "MainController?action=viewAll";
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
        } else {
            url = "login.jsp";
            request.setAttribute("message", "Invalid user or password");
        }
        return url;
    }
    private String handleLogout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "login.jsp";
    }
    private String handleRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String url = "login.jsp";
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        UserDAO dao = new UserDAO();
        if (dao.isEmailExist(email)) {
        request.setAttribute("error", "Email này đã được sử dụng!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
        boolean success = dao.register(user, pass, email);
        if (success) {
            request.setAttribute("MESSAGE", "Đăng ký thành công! Mời đăng nhập.");
            url = "login.jsp";
        } else {
            request.setAttribute("ERROR", "Tên đăng nhập đã tồn tại!");
            url = "register.jsp";
        }
        }
        return url;
    }
}
