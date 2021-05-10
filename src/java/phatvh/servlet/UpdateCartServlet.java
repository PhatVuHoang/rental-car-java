/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.car.carDTO;
import phatvh.cart.cartDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

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

        String url = VIEW_CART;

        String id = request.getParameter("txtID");
        String name = request.getParameter("txtName");
        String rentDate = request.getParameter("txtRental");
        String returnDate = request.getParameter("txtReturn");
        int quantity = Integer.parseInt(request.getParameter("txtAmount"));

        try {
            HttpSession session = request.getSession();
            List<cartDTO> cart = (List<cartDTO>) session.getAttribute("LIST");
            List<carDTO> listCar = (List<carDTO>) session.getAttribute("LISTCAR");
            int mark = 0;
            for (carDTO car : listCar) {
                if (car.getId().equals(id)) {
                    if (car.getQuantity() < quantity) {
                        request.setAttribute("ERRORUPDATEQUANTITY", "The quantity of " + name + " is not enough  ");
                        mark = 1;
                    }
                }
            }
            if (mark == 0) {
                for (cartDTO item : cart) {
                    if (item.getIdCar().equals(id) && rentDate.equals(item.getRentDate()) && returnDate.equals(item.getReturnDate())) {
                        item.setQuantity(quantity);
                    }
                }
            }
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
