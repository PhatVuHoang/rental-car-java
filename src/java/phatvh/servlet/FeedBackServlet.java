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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jni.Local;
import phatvh.order.orderDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "FeedBackServlet", urlPatterns = {"/FeedBackServlet"})
public class FeedBackServlet extends HttpServlet {

    private final String ORDER_PAGE = "SearchOrderServlet";

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
        String url = ORDER_PAGE;
        String returnDate = request.getParameter("txtReturnDate");
        String rentDate = request.getParameter("txtRentalDate");
        String idCar = request.getParameter("txtIdCar");
        String idorder = request.getParameter("txtIdOrder");
        String rate = request.getParameter("txtRate");
        try {
            LocalDate dReturn = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (dReturn.compareTo(LocalDate.now()) >= 0) {
                request.setAttribute("ERRORORDER", "You cannot feed back yet, you can only feed back after " + returnDate);
            } else {
                orderDAO dao = new orderDAO();
                dao.feedBack(idorder, idCar, Float.parseFloat(rate), returnDate, rentDate);
                float avg = dao.getAvgFeedBack(idCar);
                dao.updateFeedBack(idCar, avg);
            }
        } catch (SQLException e) {
            log("SQLException_FeedBackServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_FeedBackServlet: " + e.getMessage());
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
