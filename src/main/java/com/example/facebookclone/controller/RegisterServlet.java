package com.example.facebookclone.controller;

import com.example.facebookclone.DOA.UserDatabase;
import com.example.facebookclone.model.User;
import com.example.facebookclone.utilities.ConnectionManager;
import com.example.facebookclone.utilities.Encryption;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");

            HttpSession httpSession = request.getSession();
            //fetch data from the registration page
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");

            String encryptedPass = Encryption.encryptPassword(password);

            User user = new User(fname,lname,encryptedPass,email,dob,gender);
            UserDatabase userDatabase = new UserDatabase(ConnectionManager.getConnection());


            if (!userDatabase.registerUser(user)) {
                String errorMessage = "User already exist!";
                httpSession.setAttribute("Registration Error", errorMessage);
            }else{
                httpSession.setAttribute("Registration Error", "Successfully registered!");
            }

            response.sendRedirect("index.jsp");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
