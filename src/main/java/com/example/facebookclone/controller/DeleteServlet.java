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

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
           response.setContentType("text/plain");
           response.setCharacterEncoding("UTF-8");
            int postId = Integer.parseInt(request.getParameter("postId"));

            HttpSession session = request.getSession();
          User user =  (User)session.getAttribute("user");

            PostDatabase postDatabase = new PostDatabase(ConnectionManager.getConnection());

            if(postDatabase.deletePost(user.getId(), postId)){

                response.getWriter().write("Post deleted succesfully");
            }else{

                response.getWriter().write("Access denied deleting this post");
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
