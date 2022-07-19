/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.Subject;
import beans.User;
import daos.CategoryDAO;
import daos.SubjectDAO;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
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
public class AdminSubjectListServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "adminsublist.jsp";

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
            SubjectDAO dao = new SubjectDAO();
            CategoryDAO cdao = new CategoryDAO();
            UserDAO udao = new UserDAO();
            
            ArrayList<Subject> lst = dao.getAllSubject();
            ArrayList<Category> catelst = cdao.getAllCategory();
            ArrayList<User> explst = udao.getAllUserbyRole("EXP");
            ArrayList<Subject> lst_check = dao.getAllSubject();
             
            
            if (request.getAttribute("SUBJECTSLIST_change") != null) {
                lst = (ArrayList<Subject>) request.getAttribute("SUBJECTSLIST_change");
                session.setAttribute("sessionSUBJECTSLIST", lst);
                System.out.println(lst);
                session.removeAttribute("change");
                session.removeAttribute("check_1");
            }
            
            if (session.getAttribute("sessionSUBJECTSLIST") != null) {
                ArrayList<Subject> lst_test = (ArrayList<Subject>) request.getSession().getAttribute("sessionSUBJECTSLIST");
                if (!lst_test.isEmpty()) {
                    if (!lst.equals(lst_test)) {
                        lst = lst_test;
                    }
                }
            }
            
            if(request.getParameter("all")!=null){
                session.setAttribute("check_1", lst);
            }
            
            if (session.getAttribute("check_1") != null) {
                lst = dao.getAllSubject();
            }

            //paginated
            int recordPerPage = 5;
            int maxPage = 0;
            int size = lst.size();
            maxPage = size / recordPerPage;
            int remainder = size % recordPerPage;
            if (remainder != 0) {
                maxPage = maxPage + 1;
            }

            List<Subject>[] smalllist = new ArrayList[maxPage];
            for (int i = 0; i < maxPage; i++) {
                int fromIndex = i * recordPerPage;
                int toIndex = (i * recordPerPage + recordPerPage < size) ? (i * recordPerPage + recordPerPage) : size;
                smalllist[i] = new ArrayList(lst.subList(fromIndex, toIndex));
            }

            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            request.setAttribute("check", 5);
            request.setAttribute("noOfPages", maxPage);
            request.setAttribute("currentPage", page);

            int check = 1;
            for (List<Subject> list : smalllist) {
                if ((page - check) == 0) {
                    request.setAttribute("SUBJECTSLIST", list);   
                    break;
                } else {
                    check++;
                }
            }
            
            //paginated
            request.setAttribute("CATESLIST", catelst);
            request.setAttribute("EXPSLIST", explst);
                     
            url = success;
            
        }
        catch(Exception e){
            e.printStackTrace();
            log("Error at AdminSubjectListServlet: "+e.getMessage());
        }
        finally{
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
