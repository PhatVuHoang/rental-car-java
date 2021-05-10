/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.cart.CartObject;
import phatvh.cart.cartDTO;
import phatvh.category.categoryDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "SearchNameServlet";

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

        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
//        String url = SEARCH_PAGE;
        String url = "SearchNameServlet?index=" + index;

        String id = request.getParameter("txtId");
        String nameCar = request.getParameter("txtName");
        String color = request.getParameter("txtColor");
        int year = Integer.parseInt(request.getParameter("txtYear"));
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        float price = Float.parseFloat(request.getParameter("txtPrice"));
        int amount = Integer.parseInt(request.getParameter("txtAmount"));
        String idCate = request.getParameter("txtCate");
        try {
            HttpSession session = request.getSession();
            String rentDate = (String) session.getAttribute("RENTDATE");
            String returnDate = (String) session.getAttribute("RETURNDATE");
            LocalDate dRentDate = LocalDate.parse(rentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dReturnDate = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Duration diff = Duration.between(dRentDate.atStartOfDay(), dReturnDate.atStartOfDay());
            long diffDate = diff.toDays();
            ArrayList<categoryDTO> listCate = (ArrayList<categoryDTO>) session.getAttribute("CATEGORY");
            String nameCate = "";
            for (categoryDTO category : listCate) {
                if (category.getIdCategory().equals(idCate)) {
                    nameCate = category.getNameCategory();
                }
            }
            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            if (cart == null) {
                cart = new CartObject();
            }
            List<cartDTO> itemInCart = cart.getItems();
            if (itemInCart != null) {
                for (cartDTO item : itemInCart) {
                    if (item.getIdCar().equals(id) && item.getRentDate().equals(rentDate) && item.getReturnDate().equals(returnDate)) {
                        int total = item.getQuantity() + amount;
                        if (total > quantity) {
                            request.setAttribute("ERRORQUANTITY", "Quantity must be lower than " + quantity);
                            break;
                        } else {
                            cart.addItem(id, nameCar, color, year, amount, price * diffDate, nameCate, rentDate, returnDate);
                            break;
                        }
                    } else {
                        cart.addItem(id, nameCar, color, year, amount, price * diffDate, nameCate, rentDate, returnDate);
                        break;
                    }
                }
            } else {
                cart.addItem(id, nameCar, color, year, amount, price, nameCate, rentDate, returnDate);
            }

            List<cartDTO> listItem = cart.getItems();
            session.setAttribute("LIST", listItem);
            session.setAttribute("CUSTCART", cart);
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
