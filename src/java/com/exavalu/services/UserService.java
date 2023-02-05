/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gaura
 */
public class UserService {
      public static UserService userService = null;
    
    private UserService(){}
    
    public static UserService getInstance()
    {
        if(userService==null)
        {
            return new UserService();
        }
        else
        {
            return userService;
        }
    }
    
    public boolean doLogin(User user)
    {
        boolean success = false;
        
        String sql = "SELECT * FROM employeedb.users where emailAddress = ? and password = ?;";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            
            System.out.println("LoginService :: "+ps);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return success;
    }
    
        public boolean doSignUp(User user)
    {
       
        
        String sql = "INSERT INTO employeedb.users(emailAddress,password,firstName,lastName,status)\n" +
"VALUES(? ,? ,? ,? ,1 );";
           
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
             ps.setString(3, user.getFirstName());
              ps.setString(4, user.getLastName());
            
            
            System.out.println("LoginService :: "+ps);
            
            ps.executeUpdate();
            return true;
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return false;
    }
    
}
