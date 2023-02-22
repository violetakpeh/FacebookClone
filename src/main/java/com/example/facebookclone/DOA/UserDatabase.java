package com.example.facebookclone.DOA;

import com.example.facebookclone.model.User;
import com.example.facebookclone.utilities.ConnectionManager;
import com.example.facebookclone.utilities.Encryption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDatabase {
    private Connection con;

    public UserDatabase(Connection con) {
        this.con = con;
    }


    public boolean registerUser(User user){
        boolean success = false;
        String query = "INSERT INTO user(firstname, lastname,password, email, dob, gender) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5,user.getDob());
            preparedStatement.setString(6,user.getGender());
            preparedStatement.executeUpdate();
            success = true;

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return success;

    }


    public User login(String data, String password){
        User user = null;
        try {
           String query = "select * from user where email=?";
           Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, data);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                user = new User();
                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstName"));
                user.setLastname(result.getString("lastName"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setDob(result.getString("dob"));
                user.setGender(result.getString("gender"));


                //decrypting password
                String decryptPass = Encryption.decryptPassword(result.getString("password"));
                System.out.print("Decr: "+decryptPass);
                System.out.print("password "+password);
                if(!decryptPass.equals(password)){
                    return null;
                }
            }
        }catch(Exception e){
        }
        return user;
    }

}

