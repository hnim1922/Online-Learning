/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.Lesson;
import beans.Subject;
import beans.User;
import daos.LessonDAO;
import daos.SubjectDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AdminLessonListServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "adminlessonlist.jsp";

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
        String url = fail;
        try {
            HttpSession session = request.getSession();
            SubjectDAO sdao = new SubjectDAO();
            LessonDAO dao = new LessonDAO();

            ArrayList<Subject> sub_lst = sdao.getAllSubjectNameandID();

            ArrayList<Lesson> lst = dao.getAllLessons();

            if (request.getAttribute("LESSONSLIST_change") != null) {
                lst = (ArrayList<Lesson>) request.getAttribute("LESSONSLIST_change");
                session.setAttribute("sessionLESSONSLIST", lst);
                System.out.println(lst);
                session.removeAttribute("change");
                session.removeAttribute("check_1");
            }

            if (session.getAttribute("sessionLESSONSLIST") != null) {
                ArrayList<Lesson> lst_test = (ArrayList<Lesson>) request.getSession().getAttribute("sessionLESSONSLIST");
                if (!lst_test.isEmpty()) {
                    if (!lst.equals(lst_test)) {
                        lst = lst_test;
                    }
                }
            }

            if (request.getParameter("all") != null) {
                session.setAttribute("check_1", lst);
            }

            if (session.getAttribute("check_1") != null) {
                lst = dao.getAllLessons();
            }

            for (int i = 0; i < lst.size(); i++) {
                Lesson lscheck = lst.get(i);
                for (int j = 0; j < lst.size(); j++) {
                    Lesson lscheck2 = lst.get(j);
                    if (lscheck.getLessonID() != lscheck2.getLessonID()) {
                        if (lscheck.getContent().equals(lscheck2.getContent())) {
                            lscheck.setContent("remove this lesson");
                        }
                    }
                }
            }

            ArrayList<Lesson> finished_lst = new ArrayList<>();

            for (Lesson lesson : lst) {
                if(!lesson.getContent().equals("remove this lesson")){
                    finished_lst.add(lesson);
                }
            }
            //paginated
            int recordPerPage = 4;
            int maxPage = 0;
            int size = finished_lst.size();
            maxPage = size / recordPerPage;
            int remainder = size % recordPerPage;
            if (remainder != 0) {
                maxPage = maxPage + 1;
            }

            List<Subject>[] smalllist = new ArrayList[maxPage];
            for (int i = 0; i < maxPage; i++) {
                int fromIndex = i * recordPerPage;
                int toIndex = (i * recordPerPage + recordPerPage < size) ? (i * recordPerPage + recordPerPage) : size;
                smalllist[i] = new ArrayList(finished_lst.subList(fromIndex, toIndex));
            }

            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            request.setAttribute("check", 4);
            request.setAttribute("noOfPages", maxPage);
            request.setAttribute("currentPage", page);

            int check = 1;
            for (List<Subject> list : smalllist) {
                if ((page - check) == 0) {
                    request.setAttribute("LESSONSLIST", list);
                    break;
                } else {
                    check++;
                }
            }

            //paginated
            request.setAttribute("LISTofSUBJECTS", sub_lst);
            url = success;

        } catch (Exception e) {
            e.printStackTrace();
            log("Error at AdminLessonListServlet: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
