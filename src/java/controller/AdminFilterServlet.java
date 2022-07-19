/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.User;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdminFilterServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminUserListServlet";

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
        String url = fail;
        try {
            String gender = request.getParameter("gender");
            String roleID = request.getParameter("roleID");

            UserDAO dao = new UserDAO();
            ArrayList<User> lst = new ArrayList<>();

            if (gender.equals("all") && roleID.equals("all")) {
                lst = dao.getAllUsers();
            } else if (!gender.equals("all") && roleID.equals("all")) {
                lst = dao.getAllUserbyGender(gender);
            } else if (gender.equals("all") && !roleID.equals("all")) {
                lst = dao.getAllUserbyRole(roleID);
            } else if (!gender.equals("all") && !roleID.equals("all")) {
                lst = dao.getAllUserbyFilter(roleID, gender);
            }

            HttpSession session = request.getSession();
            session.setAttribute("change", lst);
            request.setAttribute("USERSLIST_change", lst);
            url = success;

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AdminFilterServlet: " + e.getMessage());
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
