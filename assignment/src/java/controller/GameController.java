/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.CategoryDAO;
import model.CategoryDTO;
import model.GameDAO;
import model.GameDTO;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GameController", urlPatterns = {"/GameController"})
public class GameController extends HttpServlet {

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
            String keyword = request.getParameter("keyword");
            if("search".equals(action)&& keyword.isEmpty()){
                url = handleviewAll(request, response);
            }else if("viewAll".equals(action)){ 
                url = handleviewAll(request, response);
            }else if ("GameDetail".equals(action)){
                url = handleGameDetail(request, response);
            }else if ("search".equals(action)){
                url = handleSearch(request, response);
            }else if ("addgame".equals(action)){
                url = handleAddGame(request, response);
            }else if ("showAddGameForm".equals(action)){
                url = handleAddGameForm(request, response);
            }else if ("EditGameForm".equals(action)){
                url = handleEditGameForm(request, response);
            }else if ("UpdateGame".equals(action)){
                url = handleUpdateGame(request, response);
            }else if ("DeleteGame".equals(action)){
                url = handleDeleteGame(request, response);
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
        private String handleviewAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
            GameDAO dao = new GameDAO();
            ArrayList<GameDTO> list = dao.getAllGames();
            request.setAttribute("GAMES", list);
        return "home.jsp";
    }
        private String handleGameDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
            String idRaw = request.getParameter("gameID");
            int id = Integer.parseInt(idRaw);
            GameDAO dao = new GameDAO();
            GameDTO game = dao.getGameByID(id);
            request.setAttribute("GAME", game);
        return "game_detail.jsp";
    }
        private String handleSearch(HttpServletRequest request, HttpServletResponse response) throws Exception{
            String keyword = request.getParameter("keyword");
            GameDAO dao = new GameDAO();
            List<GameDTO> result = dao.searchByName(keyword);
            request.setAttribute("GAMES", result);
            if (result == null || result.isEmpty()) {
            request.setAttribute("message", "Không có game nào có tên vậy");
        }
        return "home.jsp";        
        }
        private String handleAddGame(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imageURL = request.getParameter("imageURL");
        String fileURL = request.getParameter("fileURL");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));

        GameDAO dao = new GameDAO();
        if (dao.isTitleExist(title)) {
        request.setAttribute("error", "Tên game đã tồn tại. Vui lòng chọn tên khác!");
        } else{
            boolean result = dao.insertGame(new GameDTO(0, title, description, imageURL, fileURL, categoryID));

            if (result) {
                request.setAttribute("success", "Thêm game thành công!");
            } else {
                request.setAttribute("error", "Lỗi khi thêm game!");
            }
        }

        // Load lại categories cho form
        List<CategoryDTO> categories = new CategoryDAO().getAllCategories();
        request.setAttribute("CATEGORIES", categories);

        return "addgame.jsp";
        }
        private String handleAddGameForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
            List<CategoryDTO> categories = new CategoryDAO().getAllCategories();
            request.setAttribute("CATEGORIES", categories);
            return "addgame.jsp";
        }
        private String handleEditGameForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            GameDAO gameDAO = new GameDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            GameDTO game = gameDAO.getGameByID(gameID);
            List<CategoryDTO> categories = categoryDAO.getAllCategories();

            request.setAttribute("game", game);
            request.setAttribute("categories", categories);
            return "edit.jsp";
        }
        private String handleUpdateGame(HttpServletRequest request, HttpServletResponse response) throws Exception{
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String imageURL = request.getParameter("imageURL");
            String fileURL = request.getParameter("fileURL");
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));

            GameDTO game = new GameDTO(gameID, title, description, imageURL, fileURL, categoryID);
            GameDAO dao = new GameDAO();
            dao.updateGame(game);

           return "MainController?action=viewAll";
        }
        private String handleDeleteGame(HttpServletRequest request, HttpServletResponse response) throws Exception {
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            GameDAO dao = new GameDAO();
            boolean deleted = dao.deleteGame(gameID);

            if (deleted) {
                request.setAttribute("message", "Xóa game thành công.");
            } else {
                request.setAttribute("message", "Không thể xóa game.");
            }

            return "MainController?action=viewAll";
        }
}
