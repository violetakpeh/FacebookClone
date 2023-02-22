package com.example.facebookclone.controller;

import com.example.facebookclone.DOA.PostDatabase;
import com.example.facebookclone.model.User;
import com.example.facebookclone.utilities.ConnectionManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LikeServlet", value = "/LikeServlet")
public class LikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter();){
            out.println("<html><body>");
            out.println("<h1>" + "Servlet Registration example" + "</h1>");
            out.println("</body></html>");

            HttpSession httpSession = request.getSession();

            int action = Integer.parseInt(request.getParameter("action"));
            int postId = Integer.parseInt(request.getParameter("postId"));

            User user = (User) httpSession.getAttribute("user");

            response.getWriter().write(action+postId+user.getId());

            PostDatabase postDatabase = new PostDatabase(ConnectionManager.getConnection());

            if(postDatabase.likePost(user.getId(), postId, action)){
                response.getWriter().write("Success liking/disliking post");
            }else{
                out.print("500 error");
                response.getWriter().write("Failed do liking post");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
