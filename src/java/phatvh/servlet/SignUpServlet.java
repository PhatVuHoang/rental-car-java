/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatvh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatvh.account.accountDAO;
import phatvh.account.accountDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String SIGN_UP_PAGE = "signup.jsp";
    private final String VERIFY_PAGE = "verify.jsp";

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

        String url = SIGN_UP_PAGE;
        // get parameter
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");

        try {
            HttpSession session = request.getSession();
            accountDAO dao = new accountDAO();
            boolean checkExist = dao.checkEmailExist(email);
            if (checkExist) {
                request.setAttribute("EMAILEXIST", "Email is existed");
            } else {
                boolean role = false;
                boolean status = false;
                Random r = new Random();
                String code = "";
                for (int i = 0; i < 6; i++) {
                    char c = (char) ('a' + r.nextInt(26));
                    code += c;
                }
                Properties mailServerProperties;
                Session getMailSession;
                MimeMessage mailMessage;

                mailServerProperties = System.getProperties();
                mailServerProperties.put("mail.smtp.port", "587");
                mailServerProperties.put("mail.smtp.auth", "true");
                mailServerProperties.put("mail.smtp.starttls.enable", "true");

                getMailSession = Session.getDefaultInstance(mailServerProperties, null);
                mailMessage = new MimeMessage(getMailSession);

                mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                mailMessage.setSubject("Verify accout from Rental car");
                mailMessage.setText("Welcome to Rental car\n"
                        + "This is your verify code: " + code + "\n"
                        + "Thank you!");
                Transport transport = getMailSession.getTransport("smtp");
                transport.connect("smtp.gmail.com", "rental.car.lab3@gmail.com", "123456Phat");
                transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                transport.close();

                accountDTO dto = new accountDTO(email, phone, name, address, role, status, code);
                dao.insertAccount(email, name, password, phone, address, status, code);
                session.setAttribute("USERNAME", name);
                session.setAttribute("ROLE", role);
                session.setAttribute("EMAIL", dto.getEmail());
                session.setAttribute("ACCOUNT", dto);
                session.setAttribute("VERIFYCODE", dto.getVerifyCode());
                url = VERIFY_PAGE;
            }
        } catch (SQLException e) {
            log("SQLException_SignUpServlet: " + e.getMessage());
        } catch (NamingException e) {
            log("NamingException_SignUpServlet: " + e.getMessage());
        } catch (MessagingException e) {
            log("MessagingException_SignUpServlet: " + e.getMessage());
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
