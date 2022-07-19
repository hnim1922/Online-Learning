/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Course;
import beans.Registration;
import beans.User;
import daos.CourseDAO;
import daos.RegistrationDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Minh
 */
public class ChangeStatusServlet extends HttpServlet {

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
            int status = Integer.parseInt(request.getParameter("statusNum"));
            int regisID = Integer.parseInt(request.getParameter("registrationID"));
            boolean result;
            if (status == 0) {
                result = regisDAO.updateStatus(regisID, 1);
            } else {
                result = regisDAO.updateStatus(regisID, 0);
            }

            if (result) {
                System.out.println("successful");
            } else {
                System.out.println("failed");
            }

            Registration registration = regisDAO.getFullRegistration(regisID);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserbyID(registration.getUserID());
            session.setAttribute("user", user);
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courseList = courseDAO.getFullCourseFromRegistration(regisID);
            session.setAttribute("courseList", courseList);
            float total = 0;
            for (Course course : courseList) {
                total = total + course.getSubject().getPricePackage().getSalePrice();
            }
            session.setAttribute("totalCost", total);
            session.setAttribute("registration", registration);
            String statusName = "";
            if (registration.getStatus() == 0) {
                statusName = "Unpaid";
            } else {
                statusName = "Paid";
            }
            session.setAttribute("status", statusName);
            session.setAttribute("regisID", regisID);
            request.getRequestDispatcher("registration-detail.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ChangeStatusServlet :: " + e);
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
