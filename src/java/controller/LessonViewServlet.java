/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Lesson;
import beans.Quiz;
import beans.User;
import daos.LessonDAO;
import daos.QuizDAO;
import daos.ScoreDAO;
import daos.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ihtng
 */
public class LessonViewServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                int subjectID = Integer.parseInt(request.getParameter("subjectID"));
                int courseID = Integer.parseInt(request.getParameter("courseID"));
                int lessonID = Integer.parseInt(request.getParameter("lessonID"));
                SubjectDAO subjectDAO = new SubjectDAO();
                LessonDAO lessonDAO = new LessonDAO();
                QuizDAO quizDAO = new QuizDAO();
                ScoreDAO scoreDAO = new ScoreDAO();
                Lesson lesson = lessonDAO.getLessonByLessonIDAndSubjectID(lessonID, courseID);
                ArrayList<Lesson> lessons = lessonDAO.getLessonByCourseID(courseID);
                ArrayList<Quiz> quizzes = quizDAO.getAllQuizByLessonID(lessonID);
                if (lessons.size() == 0) {
                    request.getRequestDispatcher("no-lesson-page.jsp").forward(request, response);
                } else {
                    request.setAttribute("lessonStatus", lessonDAO.getStatus(lessonID));
                    request.setAttribute("courseID", courseID);
                    request.setAttribute("lessons", lessons);
                    request.setAttribute("lessonID", lessonID);
                    request.setAttribute("quizzes", quizzes);
                    request.setAttribute("lessonContent", lesson);
                    request.setAttribute("subjectID", subjectID);
                    request.setAttribute("subjectName", subjectDAO.getSubjectNameByID(subjectID));
                    request.getRequestDispatcher("lesson-view.jsp").forward(request, response);
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
        } catch (Exception ex) {
            Logger.getLogger(LessonViewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(LessonViewServlet.class.getName()).log(Level.SEVERE, null, ex);
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
