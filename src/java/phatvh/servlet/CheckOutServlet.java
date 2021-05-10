/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.cart.CartObject;
import phatvh.cart.cartDTO;
import phatvh.order.orderDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
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
        String url = SEARCH_PAGE;
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            String idDiscount = (String) session.getAttribute("IDDISCOUNT");
            String percent = (String) session.getAttribute("PERCENT");
            orderDAO dao = new orderDAO();
            dao.checkOut(email, idDiscount, percent);
            String idOrder = dao.getIdOrder();
            List<cartDTO> list = (List<cartDTO>) session.getAttribute("LIST");
            for (cartDTO item : list) {
                dao.insertOrderDetail(item.getRentDate(), item.getReturnDate(), item.getIdCar(), idOrder, item.getNameCar(), item.getColor(), item.getYear(), item.getPrice() * item.getQuantity(), item.getQuantity());
            }
            CartObject cart = null;
            session.setAttribute("CUSTCART", cart);
            session.setAttribute("PERCENT", "");
            session.setAttribute("CODE", "");
            session.removeAttribute("IDDISCOUNT");
//            session.setAttribute("IDDISCOUNT", "");
        } catch (SQLException e) {
            log("SQLException_CheckOutServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_CheckOutServlet: " + e.getMessage());
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
