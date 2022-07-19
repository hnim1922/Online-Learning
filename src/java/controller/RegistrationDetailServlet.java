/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import beans.Course;
import beans.Registration;
import beans.User;
import daos.CourseDAO;
import daos.RegistrationDAO;
import daos.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Minh
 */
public class RegistrationDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            int userID = Integer.parseInt(request.getParameter("userID"));
            int regisID = Integer.parseInt(request.getParameter("regisID"));
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserbyID(userID);
            session.setAttribute("user", user);
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courseList = courseDAO.getFullCourseFromRegistration(regisID);
            session.setAttribute("courseList", courseList);
            float total = 0;
            for (Course course : courseList){
                total = total + course.getSubject().getPricePackage().getSalePrice();
            }
            session.setAttribute("totalCost", total);
            RegistrationDAO regisDAO = new RegistrationDAO();
            Registration registration = regisDAO.getFullRegistration(regisID);
            System.out.println(registration.toString());
            session.setAttribute("registration", registration);
            String status = "";
            if (registration.getStatus()==0){
                status = "Unpaid";
            } else {
                status = "Paid";
            }
            session.setAttribute("status", status);
            session.setAttribute("regisID", regisID);
            request.getRequestDispatcher("registration-detail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("RegistrationDetailServlet :: " + e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
