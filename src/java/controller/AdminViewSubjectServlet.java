/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Category;
import beans.PricePackage;
import beans.Subject;
import beans.SubjectDimension;
import daos.CategoryDAO;
import daos.PricePackageDAO;
import daos.SubjectDAO;
import daos.SubjectDimensionDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdminViewSubjectServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "adminsubview.jsp";

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
            String id = request.getParameter("id");
            SubjectDAO dao = new SubjectDAO();
            PricePackageDAO pdao = new PricePackageDAO();
            CategoryDAO cdao = new CategoryDAO();
            SubjectDimensionDAO ddao = new SubjectDimensionDAO();
            
            ArrayList<Category> catelst = cdao.getAllCategory();
            
            Subject sub = dao.getOneSubjectsById(Integer.parseInt(id));
            ArrayList<PricePackage> price_lst = pdao.getPricePackageBySubjectId(sub.getSubjectID());
            
            ArrayList<SubjectDimension> dimen_list = ddao.getAllSubjectDimension();
            
            request.setAttribute("SUBJECT", sub);
            request.setAttribute("PRICELIST", price_lst);
            request.setAttribute("CATESLIST", catelst);
            request.setAttribute("DIMENLIST", dimen_list);
            url = success;
        }
        catch(Exception e){
            e.printStackTrace();
            log("Error at AdminViewSubjectServlet: "+e.getMessage());
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
