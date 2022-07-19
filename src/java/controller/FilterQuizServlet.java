/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Quiz;
import beans.User;
import daos.QuizDAO;
import daos.SubjectDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ihtng
 */
public class FilterQuizServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                int pageId = Integer.parseInt(request.getParameter("page"));
                int total = 3;
                if (pageId == 1) {
                } else {
                    pageId = pageId - 1;
                    pageId = pageId * total + 1;
                }
                
                String subjectName = request.getParameter("subjectName");

                SubjectDAO subjectDAO = new SubjectDAO();
                QuizDAO quizDAO = new QuizDAO();
                ArrayList<Quiz> allQuizSearch = new ArrayList<>();
                ArrayList<Quiz> allQuizSearchPaginated = new ArrayList<>();
                ArrayList<String> subjectNameList = new ArrayList<>();
                ArrayList<String> quizTypeList = new ArrayList<>();

                allQuizSearch = quizDAO.getQuizBySubject(subjectName);
                allQuizSearchPaginated = quizDAO.getQuizBySubjectPaginated(subjectName, pageId, total);
                subjectNameList = subjectDAO.getAllSubjectName();
                quizTypeList = quizDAO.getQuizType();

                request.setAttribute("subjectNameList", subjectNameList);
                request.setAttribute("quizzes", allQuizSearchPaginated);
                request.setAttribute("allQuizzes", allQuizSearch);
                request.setAttribute("quizTypeList", quizTypeList);
                if (allQuizSearch.size() > 0) {
                    request.getRequestDispatcher("quizzes-list.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("no-quiz-page.jsp").forward(request, response);
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You have to login to use this feature');");
                 out.println("location='Homepage';");
                out.println("</script>");
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FilterQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FilterQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
