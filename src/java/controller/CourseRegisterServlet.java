/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.Course;
import beans.PricePackage;
import beans.Registration;
import beans.Subject;
import beans.User;
import daos.CategoryDAO;
import daos.CourseDAO;
import daos.PricePackageDAO;
import daos.RegistrationDAO;
import daos.SubjectDAO;
import daos.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh
 */
public class CourseRegisterServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int pricePackageID = Integer.parseInt(request.getParameter("salingPrice"));

            SubjectDAO subjectDAO = new SubjectDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            PricePackageDAO pricePackageDAO = new PricePackageDAO();
            UserDAO userDAO = new UserDAO();
            RegistrationDAO regisDAO = new RegistrationDAO();
            CourseDAO courseDAO = new CourseDAO();

            List<PricePackage> pricePackageList = pricePackageDAO.getPricePackageBySubID(subjectID);
            ArrayList<Category> allCategoryList = categoryDAO.getAllCategory();
            ArrayList<Subject> featuredSubjectList = subjectDAO.getFeaturedSubject();

            Subject subject = subjectDAO.getSubjectById(subjectID);
            request.setAttribute("subjectID", subjectID);
            request.setAttribute("subjectThumbnail", subject.getThumbnail());
            request.setAttribute("subjectTitle", subject.getName());
            request.setAttribute("subjectCategory", subject.getCategoryName());
            request.setAttribute("subjectInfo", subject.getInformation());
            request.setAttribute("subjectPricePackage", subject.getPricePackage());
            request.setAttribute("featuredSubjectList", featuredSubjectList);
            request.setAttribute("categoryList", allCategoryList);
            request.setAttribute("pricepackageList", pricePackageList);
            request.setAttribute("salingPrice", pricePackageID);
            request.getRequestDispatcher("course-details.jsp").forward(request, response);

            System.out.println(pricePackageID);
            User user = (User) session.getAttribute("USER");
            int userID = user.getUserID();
            boolean hasRegis = userDAO.checkRegisExist(userID);

            if (!hasRegis) {
                // create registrationDAO to add a new registration
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate localDate = LocalDate.now();
                Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                LocalDate plus3months = localDate.plusMonths(3);
                Date validTo = Date.from(plus3months.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Registration registration = new Registration(formatter.parse(formatter.format(currentDate)), formatter.parse(formatter.format(currentDate)), formatter.parse(formatter.format(validTo)), userID);
                boolean result = regisDAO.createNewRegistration(registration);
                if (result) {
                    // add pricepackage to total fields

                    // add a course according to subID and add regisID into course
                    int regisID = regisDAO.returnRegisID(userID);
                    Course course = new Course(subjectID, regisID, subject.getName(), subject.getCategoryName(), subject.getInformation(), 0);
                    boolean hasAdded = courseDAO.createCourse(course);
                    if (hasAdded) {
                        System.out.println("Added");
                    } else {
                        System.out.println("Add failed");
                    }
                    // print out the result
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add course to registrations successful');");
                    out.println("location='course-details.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add course to registrations failed');");
                    out.println("location='course-details.jsp';");
                    out.println("</script>");
                }

            } else {
                // add pricepackage to total fields according to current regisID
                // 1. find regis that status = 0 and userID = userID
                int regisID = regisDAO.returnRegisID(userID);
                boolean hasDuplicate = regisDAO.checkDuplicate(regisID, subjectID);
                if (!hasDuplicate) {
                    // 2. get total from that regis and then add the salePrice to that total
                    // 3. update regis according to new total
                    // add a course according to subID and add regisID into course

                    Course course = new Course(subjectID, regisID, subject.getName(), subject.getCategoryName(), subject.getInformation(), 0);

                    boolean hasAdded = courseDAO.createCourse(course);
                    if (hasAdded) {
                        System.out.println("Added");
                    } else {
                        System.out.println("Add failed");
                    }
                } else {

                    out.println("<script type=\"text/javascript\">");
                    out.println("delete window.alert;");
                    out.println("alert('You have added this course');");
                    out.println("location='course-details.jsp';");
                    out.println("</script>");
                }
            }
        } catch (Exception e) {
            System.out.println("CourseRegisterServlet :: " + e);
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
