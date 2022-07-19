/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.User;
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
public class AdminUserListServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "adminuserlist.jsp";

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
        boolean flag = true;
        try {
            UserDAO dao = new UserDAO();
            ArrayList<User> lst = dao.getAllUsers();
            ArrayList<User> lst_check = dao.getAllUsers();

            HttpSession session = request.getSession();
//            if (session.getAttribute("session_USERSLIST_change")!= null) {
//                lst = (ArrayList<User>)session.getAttribute("USERSLIST_change");
//            }

            if (request.getAttribute("USERSLIST_change") != null) {
                lst = (ArrayList<User>) request.getAttribute("USERSLIST_change");
                session.setAttribute("sessionUSERSLIST", lst);
                System.out.println(lst);
                session.removeAttribute("change");
                session.removeAttribute("check_1");
            }

            if (session.getAttribute("sessionUSERSLIST") != null) {
                ArrayList<User> lst_test = (ArrayList<User>) request.getSession().getAttribute("sessionUSERSLIST");
                if (!lst_test.isEmpty()) {
                    if (!lst.equals(lst_test)) {
                        lst = lst_test;
                    }
                }
            }

            if (session.getAttribute("check_1") != null) {
                lst = dao.getAllUsers();
            }

//            if(request.getParameter("check_2")!=null){
//                lst = dao.getAllUsers();
//                session.removeAttribute("check_2");
//            }
            System.out.println(lst);

//            if(session.getAttribute("check_2")!=null){
//                
//                System.out.println("B");
//                
//            }
//            if (session.getAttribute("change") != null) {
//                if(session.getAttribute("check") != null){
//                    lst = dao.getAllUsers();
//                    session.removeAttribute("check");
//                }
//            }
//            session.removeAttribute("check");
//            if (session.getAttribute("check") != null) {
//                System.out.println("B");
//            }
            //get the total number of page
            int recordPerPage = 4;
            int maxPage = 0;
            int size = lst.size();
            maxPage = size / recordPerPage;
            int remainder = size % recordPerPage;
            if (remainder != 0) {
                maxPage = maxPage + 1;
            }

//
            List<User>[] smalllist = new ArrayList[maxPage];
            for (int i = 0; i < maxPage; i++) {
                int fromIndex = i * recordPerPage;
                int toIndex = (i * recordPerPage + recordPerPage < size) ? (i * recordPerPage + recordPerPage) : size;

                smalllist[i] = new ArrayList(lst.subList(fromIndex, toIndex));
            }

//            
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
//            lst = dao.getAllUsersLimit((page - 1) * recordPerPage, recordPerPage);

            request.setAttribute("check", 4);
            request.setAttribute("noOfPages", maxPage);
            request.setAttribute("currentPage", page);

            int check = 1;
            for (List<User> list : smalllist) {
                if ((page - check) == 0) {
                    request.setAttribute("USERSLIST", list);
                    break;
                } else {
                    check++;
                }
            }

            url = success;

        } catch (Exception e) {
            log("ERROR at UserListServlet: " + e.getMessage());
            e.printStackTrace();
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
