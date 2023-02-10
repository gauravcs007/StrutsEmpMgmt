/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.Employee;
import com.exavalu.models.Province;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gaura
 */
public class UserService {

    public static UserService userService = null;

    public static ArrayList getAllCountries(){

        ArrayList countryList = new ArrayList();

        String sql = "SELECT * FROM employeedb.countries;";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));
                country.setCountryName(rs.getString("countryName"));
                 
                countryList.add(country);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return countryList;

    }

    public static ArrayList getAllStates(String countryCode) {
        
        ArrayList stateList = new ArrayList();

        String sql = "SELECT * FROM employeedb.states where countryCode = ?;";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, countryCode);
            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                province.setProvinceCode(rs.getString("stateCode"));
                province.setProvinceName(rs.getString("stateName"));
                stateList.add(province);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return stateList;
       
    }

    public static ArrayList getAllDistricts(String stateCode) {
        
         ArrayList distList = new ArrayList();

        String sql = "SELECT * FROM employeedb.districts where stateCode = ?;";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, stateCode);
            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                District district = new District();
                district.setDistCode(rs.getString("districtCode"));
                district.setDistName(rs.getString("districtName"));
                distList.add(district);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return distList;
        
    }

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            return new UserService();
        } else {
            return userService;
        }
    }

    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "SELECT * FROM employeedb.users where emailAddress = ? and password = ?;";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }

    public boolean doSignUp(User user) {

        String sql = "INSERT INTO employeedb.users(emailAddress,password,firstName,lastName,status,country,state,city)\n"
                + "VALUES(? ,? ,? ,? ,1,?,?,?);";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
             ps.setString(5, user.getCountryCode());
              ps.setString(6, user.getStateCode());
               ps.setString(7, user.getDistCode());

            System.out.println("LoginService :: " + ps);

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
