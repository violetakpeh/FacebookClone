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

@WebServlet(name = "CommentEdititngServlet", value = "/CommentEdititngServlet")
public class CommentEdititngServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter();) {
            HttpSession httpSession = request.getSession();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            int postId = Integer.parseInt(request.getParameter("postId"));
            String comment = request.getParameter("editedComment");

            User user = (User) httpSession.getAttribute("user");

            PostDatabase postDatabase = new PostDatabase(ConnectionManager.getConnection());

            if(postDatabase.editComment(user.getId(), postId, comment)){
                response.getWriter().write("Success editing post");
            }else{
                response.getWriter().write("Error editing post or you don't have access to delete this comment");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
