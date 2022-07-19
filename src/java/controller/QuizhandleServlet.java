/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Answer;
import beans.Question;
import beans.Quiz;
import beans.User;
import daos.AnswerDAO;
import daos.QuestionDAO;
import daos.QuizDAO;
import daos.ScoreDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author acer
 */
public class QuizhandleServlet extends HttpServlet {

    public QuizhandleServlet() {
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                int quizID = Integer.parseInt(request.getParameter("quizID"));
          
//            int questionID=Integer.parseInt(request.getParameter("questionID"));
                QuestionDAO qdao = new QuestionDAO();
                AnswerDAO adao = new AnswerDAO();
                ArrayList<Question> questionList = qdao.getQuestionbyQuizID(quizID);
                ArrayList<Answer> answerList = adao.getAllAnswer();
                ArrayList<Answer> correctList = adao.getAllCorrectAnswer();
//                String score = scoreDAO.getHighScore(user.getUserID(), quizID);
//            String correctAns = adao.getCorrectAnswer(questionID);
                request.setAttribute("quizID", quizID);
                request.setAttribute("questionList", questionList);
                request.setAttribute("answerList", answerList);
                request.setAttribute("correctList", correctList);
//                request.setAttribute("score", score);
//            request.setAttribute("correctAns", correctAns);
                RequestDispatcher rd = request.getRequestDispatcher("quizhandle.jsp");
                rd.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You have to login to use this feature');");
                out.println("location='homepage.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            log("ERROR at QuizhandelServlet:" + e.getMessage());
            e.printStackTrace();
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
