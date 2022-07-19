/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.ErrorBlog;
import beans.User;
import daos.BlogDAO;
import daos.CategoryDAO;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class AddPostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "/PostListServlet?index=1";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "/PostListServlet?index=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try  {

            /* TODO output your page here. You may use following sample code. */
            int userID = Integer.parseInt(request.getParameter("userID"));
            int catID = Integer.parseInt(request.getParameter("catID"));
            String thumbnail = request.getParameter("thumbnail");
            String title = request.getParameter("title");
            String date = request.getParameter("date");
            String detail = request.getParameter("detail");
            boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            BlogDAO blogDAO = new BlogDAO();
            
            boolean valid = true;
            ErrorBlog errorblog = new ErrorBlog();
            
            if (thumbnail.trim().isEmpty()) {
                errorblog.setErrorThumbnail("Thumbnail should not be Empty!");
                valid = false;
            }
            if (title.trim().isEmpty()) {
                errorblog.setErrorTitle("Title should not be Empty!");
                valid = false;
            }

            if (date.trim().isEmpty()) {
                errorblog.setErrorDate("Date should not be Empty!");
                valid = false;
            }
            if (valid) {
                boolean rs = blogDAO.AddPost(userID, catID, thumbnail, title, date, detail, flag, status);
                if (rs) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Add failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorblog);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AddSliderServlet: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
