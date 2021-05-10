/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.discount.discountDAO;
import phatvh.discount.discountDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DiscountServlet", urlPatterns = {"/DiscountServlet"})
public class DiscountServlet extends HttpServlet {

    private final String VIEW_CART = "viewcart.jsp";

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
        
        String discountCode = request.getParameter("txtDiscount");
        String url = VIEW_CART;
        try {
            HttpSession session = request.getSession();
            discountDAO dao = new discountDAO();
            ArrayList<discountDTO> list = dao.getDiscount();
            int mark = 0;
            for (discountDTO discount : list) {
                LocalDate expirationDate = LocalDate.parse(discount.getExpirationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (discount.getCode().equals(discountCode)) {
                    if (expirationDate.compareTo(LocalDate.now()) >= 0) {
                        request.setAttribute("EXPIRED", "");
                        request.setAttribute("DISCOUNTERROR", "");
                        session.setAttribute("PERCENT", String.valueOf(discount.getPercentDiscount()));
                        session.setAttribute("CODE", discountCode);
                        session.setAttribute("IDDISCOUNT", discount.getId());
                    } else {
                        request.setAttribute("EXPIRED", "discount code has expired");
                    }
                    mark = 1;
                }
            }
            if (mark == 0) {
                request.setAttribute("DISCOUNTERROR", "discount code is not exist");
            }
        } catch (SQLException e) {
            log("SQLException_DiscountServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_DiscountServlet: " + e.getMessage());
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
