/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ErrorPrice;
import beans.PricePackage;
import daos.PricePackageDAO;
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
public class AdminChangePriceServlet extends HttpServlet {

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
        try {
            PricePackageDAO dao = new PricePackageDAO();
            String id = request.getParameter("priceid");
            PricePackage price = dao.getPricePackageByPricePackageId(Integer.parseInt(id));

            String name = request.getParameter("updatepricename");
            String str_duration = request.getParameter("updateduration");
            String status = request.getParameter("updatestatus");
            String str_listprice = request.getParameter("updatelistprice");
            String str_saleprice = request.getParameter("updatesaleprice");
            String desc = request.getParameter("updatedesc");

            ErrorPrice error = new ErrorPrice();
            boolean valid = true;

            if (name.isEmpty()) {
                error.setNameError("Name should not be Empty!");
                valid = false;
            }
            if (desc.isEmpty()) {
                error.setInformationError("Description should not be Empty!");
                valid = false;
            } else if (desc.length() > 100) {
                error.setInformationError("Too long! Please keep it simple!");
                valid = false;
            }
            if (str_listprice.isEmpty()) {
                error.setListpriceError("Price should not be Empty!");
                valid = false;
            }
            if (str_saleprice.isEmpty()) {
                error.setSalepriceError("Price should not be Empty!");
                valid = false;
            }
            if (!str_saleprice.isEmpty() && !str_listprice.isEmpty()) {
                float sprice = Float.parseFloat(str_saleprice);
                float lprice = Float.parseFloat(str_listprice);
                if (0 < lprice || lprice > 1000) {
                    error.setListpriceError("List Price should not be in range from 1-1000");
                    valid = false;
                }
                else if (0 < sprice || sprice > 1000) {
                    error.setSalepriceError("Sale Price should not be in range from 1-1000");
                    valid = false;
                }
                else if (lprice <= sprice) {
                    error.setSalepriceError("Sale Price should not be larger than List Price!");
                    valid = false;
                }
            }

            if (valid) {
                int duration = Integer.parseInt(str_duration);
                float listprice = Float.parseFloat(str_listprice);
                float saleprice = Float.parseFloat(str_saleprice);

                price.setName(name);
                price.setAccessDuration(duration);
                price.setListPrice(listprice);
                price.setSalePrice(saleprice);
                price.setDescription(desc);
                price.setStatus(status);

                boolean result = dao.UpdatePricePackage(price);

                if (result) {
                    url = success;
                } else {
                    request.setAttribute("ERROR", "Insert Failed!");
                }
            } else {
                url = invalid;
                request.setAttribute("requestPRICE", price);
                request.setAttribute("INVALIDUPDATEPRICE", error);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AdminChangePriceServlet: " + e.getMessage());
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
