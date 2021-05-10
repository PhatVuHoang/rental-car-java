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
import phatvh.car.carDAO;
import phatvh.car.carDTO;
import phatvh.order.orderDAO;
import phatvh.order.orderDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchNameServlet", urlPatterns = {"/SearchNameServlet"})
public class SearchNameServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String SHOW_PAGE = "show.jsp";

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
        String searchValue = request.getParameter("txtSearch");
        String searchCate = request.getParameter("cbCategory");
        String rentDate = request.getParameter("txtRentDate");
        String returnDate = request.getParameter("txtReturnDate");
        String quantity = request.getParameter("txtQuantitySearch");
        String index = request.getParameter("index");
        String select = request.getParameter("rad");
        HttpSession session = request.getSession();
        if (index == null) {
            index = "1";
        }
        if (rentDate == null) {
            rentDate = (String) session.getAttribute("RENTDATE");
        }
        if (returnDate == null) {
            returnDate = (String) session.getAttribute("RETURNDATE");
        }
        if (quantity == null) {
            quantity = (String) session.getAttribute("QUANTITY");
        }
        int numRow = (Integer.parseInt(index) - 1) * 20;
        try {
            if (searchValue == null) {
                searchValue = "";
            }
            if (searchCate == null) {
                searchCate = "";
            }
            if (select == null) {
                select = (String) session.getAttribute("SELECT");
            }
            if (quantity == null) {
                quantity = (String) session.getAttribute("QUANITY");
            }
            LocalDate dRent = LocalDate.parse(rentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dReturn = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (dRent.compareTo(dReturn) >= 0) {
                request.setAttribute("ERRORDATE", "rental date must lower than return date");
            } else if (dRent.compareTo(LocalDate.now()) < 0) {
                request.setAttribute("ERRORDATE", "rental date must greater or equal today");
            } else {
                url = SHOW_PAGE;
                if (select.equals("1")) {
                    if (searchValue.equals("")) {
                        searchValue = (String) session.getAttribute("SEARCHNAME");
                    }
                } else if (select.equals("2")) {
                    if (searchCate.equals("")) {
                        searchCate = (String) session.getAttribute("SEARCHCATE");
                    }

                }
                orderDAO orderDao = new orderDAO();
                carDAO dao = new carDAO();
                int row = 0;
                if (searchCate.equals("")) {
                    row = dao.numberOfCar(searchValue);
                } else {
                    row = dao.numberOfCarCate(searchCate);
                }
                int page = row / 20;
                if (row % 20 != 0) {
                    page++;
                }
                ArrayList<orderDTO> listOrder = orderDao.getAllOrder(searchValue);
                ArrayList<carDTO> listCar = dao.searchNameCar(searchValue, searchCate, numRow);
                if (listCar != null) {
                    if (listOrder != null) {
                        for (orderDTO order : listOrder) {
                            LocalDate dRentOrder = LocalDate.parse(order.getRentDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            LocalDate dReturnOrder = LocalDate.parse(order.getReturnDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (dRent.compareTo(dRentOrder) >= 0 && dRent.compareTo(dReturnOrder) <= 0) {
                                for (carDTO car : listCar) {
                                    if (car.getId().equals(order.getIdCar())) {
                                        car.setQuantity(car.getQuantity() - order.getQuantity());
                                    }
                                }
                            } else if (dReturn.compareTo(dReturnOrder) <= 0 && dReturn.compareTo(dRentOrder) >= 0) {
                                for (carDTO car : listCar) {
                                    if (car.getId().equals(order.getIdCar())) {
                                        car.setQuantity(car.getQuantity() - order.getQuantity());
                                    }
                                }
                            } else if (dRent.compareTo(dRentOrder) <= 0 && dReturn.compareTo(dReturnOrder) >= 0) {
                                for (carDTO car : listCar) {
                                    if (car.getId().equals(order.getIdCar())) {
                                        car.setQuantity(car.getQuantity() - order.getQuantity());
                                    }
                                }
                            }
                        }
                    }
                }
                
                ArrayList<carDTO> showCar = new ArrayList<>();
                if (listCar != null) {
                    for (carDTO car : listCar) {
                        if (car.getQuantity() >= Integer.parseInt(quantity)) {
                            showCar.add(car);
                        }
                    }
                }
                
                
                session.setAttribute("NUMBEROFPAGE", page);
                session.setAttribute("INDEX", index);
                session.setAttribute("LISTCAR", showCar);
                session.setAttribute("QUANTITY", quantity);
            }
            session.setAttribute("RENTDATE", rentDate);
            session.setAttribute("RETURNDATE", returnDate);
            if (select == null) {
                select = "1";
            }
            session.setAttribute("SELECT", select);
            if (searchValue == null) {
                searchValue = "";
            }
            if (searchCate == null) {
                searchCate = "";
            }
            session.setAttribute("SEARCHNAME", searchValue);
            session.setAttribute("SEARCHCATE", searchCate);

        } catch (SQLException e) {
            log("SQLException_SearchNameServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_SearchNameServlet: " + e.getMessage());
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
