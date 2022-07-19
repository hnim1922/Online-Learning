/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Answer;
import beans.Question;
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
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author acer
 */
public class QuizReviewServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            if (user != null) {
                int quizID = Integer.parseInt(request.getParameter("quizID"));
                QuestionDAO qdao = new QuestionDAO();
                QuizDAO quizdao = new QuizDAO();
                AnswerDAO adao = new AnswerDAO();
                ScoreDAO scoreDAO= new ScoreDAO();
                ArrayList<Question> questionList = qdao.getQuestionbyQuizID(quizID);
                ArrayList<Answer> answerList = adao.getAllAnswer();
                ArrayList<Answer> correctList = adao.getAllCorrectAnswer();
                ArrayList<ArrayList<String>> radioList = new ArrayList<>();
                String answerRadio = null;
                TreeMap<Question, String[]> hMap = new TreeMap<>();
                for (Question question : questionList) {
                    answerRadio = request.getParameter(String.valueOf(question.getQuestionID()));
                    if (answerRadio != null) {
                        String[] radio = answerRadio.split(",");
                        if (Integer.parseInt(radio[1]) == question.getQuestionID()) {
                            hMap.put(question, radio);
                        }
                    } else {
                        hMap.put(question, null);
                    }

                }
                for (Question q : hMap.keySet()) {
                    System.out.println(q.getQuestionID());
                }

//            for (String[] s : hMap.values()) {
//                for(int i=0; i<s.length;i++)
//                {
//                    System.out.println(s[i]);
//                }
//            }
                if (request.getParameter("quizSubmit") != null) {
                    request.setAttribute("quizID", quizID);
                    request.setAttribute("questionList", hMap);
                    request.setAttribute("answerList", answerList);
                    request.setAttribute("correctList", correctList);
                    RequestDispatcher rd = request.getRequestDispatcher("quizreview.jsp");
                    rd.forward(request, response);
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You have to login to use this feature');");
                out.println("location='homepage.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            log("ERROR at QuizReviewServlet:" + e.getMessage());
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

    }
}
