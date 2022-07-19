/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import beans.Course;
import beans.Registration;
import beans.User;
import daos.CourseDAO;
import daos.RegistrationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Minh
 */
public class DeleteCourseServlet extends HttpServlet {

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
            RegistrationDAO regisDAO = new RegistrationDAO();
            CourseDAO courseDAO = new CourseDAO();
            // some initial parameters
            User user = (User) session.getAttribute("USER");
            int userID = user.getUserID();
            int regisID = regisDAO.returnRegisID(userID);

            // show information of that registration --> show on myRegistration,jsp
            Registration regis = regisDAO.getRegistration(regisID);
            session.setAttribute("regisID", regisID);
            session.setAttribute("registrationTime", regis.getRegisTime());
            session.setAttribute("validTo", regis.getValidTo());
            // check if the current date is over from validTo
            System.out.println(regis.getRegisTime());
            System.out.println(regis.getValidTo());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date now = new Date();
            System.out.println(formatter.parse(formatter.format(now)).compareTo(regis.getValidTo()));
            session.setAttribute("currentDate", formatter.parse(formatter.format(now)));
            // show courses list of that registration --> show on myRegistration,jsp
            List<Course> courseList = courseDAO.getCourseFromRegistration(regisID);
            session.setAttribute("courseList", courseList);
            // calculate the total of price
            float total = 0;
            for (Course course : courseList) {
                total = total + course.getSubject().getPricePackage().getSalePrice();
            }
            session.setAttribute("totalCost", total);

            // delete course
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            boolean result = courseDAO.deleteCourse(courseID);
            if (result){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Complete delete course from registration');");
                 out.println("location='Homepage';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Fail to delete course from registration');");
                 out.println("location='Homepage';");
                out.println("</script>");
            }
            request.getRequestDispatcher("MyRegisrationServlet").forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("DeleteCourseServlet :: " + e);
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
