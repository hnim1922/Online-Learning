/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Registration;
import daos.RegistrationDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh
 */
public class FilterRegistrationServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            RegistrationDAO regisDAO = new RegistrationDAO();
            List<Registration> regisList = new ArrayList<>();
            String sortType = request.getParameter("filtering");
            System.out.println(sortType);
            if (sortType.equalsIgnoreCase("sort-by-id")){
                regisList = regisDAO.getRegistrationByUID();
            } else if (sortType.equalsIgnoreCase("sort-by-email")){
                regisList = regisDAO.getRegistrationByEmail();
            } else if (sortType.equalsIgnoreCase("sort-by-registrationTime")){
                regisList = regisDAO.getRegistrationByRegistrationTime();
            } else if (sortType.equalsIgnoreCase("sort-by-status")){
                regisList = regisDAO.getRegistrationByStatus();
            }
            
            session.setAttribute("regisList", regisList);
            request.getRequestDispatcher("registration-list.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("FilterRegistrationServlet :: " + e);
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
