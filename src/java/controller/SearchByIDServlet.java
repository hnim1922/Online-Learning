/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Blog;
import beans.Category;
import daos.BlogDAO;
import daos.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Minh
 */
public class SearchByIDServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            CategoryDAO catDAO = new CategoryDAO();
            BlogDAO blogDAO = new BlogDAO();
            int catID = Integer.parseInt(request.getParameter("catID"));
            List<Blog> blogList = blogDAO.searchByCat(catID);
            int index = Integer.parseInt(request.getParameter("index"));
            int count = blogList.size();
            int size = 9;
            
            int endpage = count / 9;
            
            if (count % size != 0) {
                endpage++;
            }
            
            List<Category> categoryList = catDAO.getAllCategory();
            List<Blog> firstFive = blogDAO.getFirstFive();
            List<Blog> blogPaging = blogDAO.searchByCatPaging(catID, index);

            session.setAttribute("catID", catID);
            session.setAttribute("endPage", endpage);
            session.setAttribute("firstFive", firstFive);
            session.setAttribute("catList", categoryList);
            session.setAttribute("blogList", blogPaging);
            request.getRequestDispatcher("searchByID.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("SearchByID :: " + ex);
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
