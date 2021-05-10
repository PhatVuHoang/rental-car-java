/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.order.orderDAO;
import phatvh.order.orderDTO2;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchOrderServlet", urlPatterns = {"/SearchOrderServlet"})
public class SearchOrderServlet extends HttpServlet {

    private final String ORDER_PAGE = "orderhistory.jsp";

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
        String select = request.getParameter("rd");
        String searchName = request.getParameter("txtSearch");
        String searchDate = request.getParameter("txtOrderDate");
        try {
            HttpSession session = request.getSession();
            if (select == null) {
                select = (String) session.getAttribute("SELECTORDERSEARCH");
            }
            orderDAO dao = new orderDAO();
            List<orderDTO2> listOrder = (List<orderDTO2>) session.getAttribute("LISTORDERTEMP");
            String email = (String) session.getAttribute("EMAIL");
            List<orderDTO2> listOrderSearch = new ArrayList<>();
            if (Integer.parseInt(select) == 1) {
                if (searchName == null) {
                    searchName = (String) session.getAttribute("SEARCHORDERNAME");
                } else {
                    session.setAttribute("SEARCHORDERNAME", searchName);
                }
                ArrayList<orderDTO2> listOrd = dao.searchNameId(searchName, email);
                ArrayList<orderDTO2> listDetail = new ArrayList<>();
                if (listOrd != null) {
                    for (orderDTO2 dto : listOrd) {
                        ArrayList<orderDTO2> temp = dao.searchOrderDetail(email, dto.getIdOrder());
                        for (orderDTO2 dto2 : temp) {
                            listDetail.add(dto2);
                        }
                    }
                }
                session.setAttribute("LISTORDER", listOrd);
                session.setAttribute("LISTORDERDETAIL", listDetail);
                session.setAttribute("SEARCHNAMEORDER", searchName);
                session.setAttribute("SEARCHDATEORDER", searchDate);

            } else {
                if (searchDate == null) {
                    searchDate = (String) session.getAttribute("SEARCHORDERDATE");
                } else {
                    session.setAttribute("SEARCHORDERDATE", searchDate);
                }
                List<orderDTO2> listSearch = dao.searchDateOrderDetail(email, searchDate);
                if (listSearch != null) {
                    for (orderDTO2 dto : listOrder) {
                        for (orderDTO2 dto2 : listSearch) {
                            if (dto.getIdOrder().equals(dto2.getIdOrder())) {
                                orderDTO2 order = new orderDTO2(dto.getIdOrder(), dto.getDateOrder(), dto.getPercent());
                                listOrderSearch.add(order);
                                break;
                            }
                        }
                    }
                }
                session.setAttribute("LISTORDERDETAIL", listSearch);
                session.setAttribute("SEARCHDATEORDER", searchDate);
                session.setAttribute("SEARCHNAMEORDER", searchName);
                session.setAttribute("LISTORDER", listOrderSearch);
            }
            session.setAttribute("SELECTORDERSEARCH", select);
        } catch (SQLException e) {
            log("SQLException_SearchOrderServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_SearchOrderServlet: " + e.getMessage());

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
