/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Quiz;
import beans.User;
import daos.LessonDAO;
import daos.QuizDAO;
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
public class QuizzesListServlet extends HttpServlet {

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
                int pageId = Integer.parseInt(request.getParameter("page"));
                int total = 3;
                if (pageId == 1) {
                } else {
                    pageId = pageId - 1;
                    pageId = pageId * total + 1;
                }

                SubjectDAO subjectDAO = new SubjectDAO();
                QuizDAO quizDAO = new QuizDAO();
                LessonDAO lessonDAO = new LessonDAO();

                ArrayList<String> quizTypeList = new ArrayList<>();
                ArrayList<String> subjectNameList = new ArrayList<>();
                ArrayList<Quiz> quizzes = new ArrayList<>();
                ArrayList<Integer> lessonIDList = new ArrayList<>();

                quizTypeList = quizDAO.getQuizType();
                quizzes = quizDAO.getQuizPaginated(pageId, total);
                subjectNameList = subjectDAO.getAllSubjectName();
                lessonIDList = lessonDAO.getLessonID();

                request.setAttribute("subjectNameList", subjectNameList);
                request.setAttribute("quizzes", quizzes);
                request.setAttribute("allQuizzes", quizDAO.getAllQuiz());
                request.setAttribute("quizTypeList", quizTypeList);
                request.setAttribute("lessonIDList", lessonIDList);
                if (quizzes.size() > 0) {
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
        } catch (Exception ex) {
            Logger.getLogger(QuizzesListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(QuizzesListServlet.class.getName()).log(Level.SEVERE, null, ex);
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
