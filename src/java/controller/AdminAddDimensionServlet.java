/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorDimension;
import beans.SubjectDimension;
import daos.SubjectDimensionDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AdminAddDimensionServlet extends HttpServlet {

    private static final String fail = "error.jsp";
    private static final String success = "AdminViewSubjectServlet";
    private static final String invalid = "AdminViewSubjectServlet";

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
        try{
            String name = request.getParameter("dimenname");
            String type = request.getParameter("type");
            String desc = request.getParameter("desc");
            
            SubjectDimensionDAO dao = new SubjectDimensionDAO();
            
            ErrorDimension error = new ErrorDimension();
            boolean valid = true;
            
            if(name.isEmpty()){
                error.setNameError("Name should not be Empty!");
                valid = false;
            }                        
            if(type.isEmpty()){
                error.setTypeError("Type should not be Empty!");
                valid = false;
            }
            if(desc.isEmpty()){
                error.setDescriptionError("Description should not be Empty!");
                valid = false;
            }
            
            if(valid){
                SubjectDimension dimen = new SubjectDimension(0, type, name, desc);
                boolean result = dao.AddANewDimension(dimen);
                if(result){
                    url = success;
                }
                else{
                    request.setAttribute("ERROR", "Insert fail!");
                }
            }
            else{
                url = invalid;
                request.setAttribute("INVALIDDIMEN", error);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            log("ERROR at AdminAddDimensionServlet: "+e.getMessage());
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
