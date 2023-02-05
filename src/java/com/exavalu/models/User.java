/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.exavalu.services.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gaura
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String email;
    private String password;
   
    private String firstName;
    private String lastName;
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
        sessionMap.put("Loggedin", NONE);
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = UserService.getInstance().doLogin(this);

        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("Loggedin", this);
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";

        } else {
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }

    public String doLogout() {
        String result = "SUCCESS";
        sessionMap.clear();
        return result;
    }
     public String doSignUp(){
         String result = "FAILURE";
         boolean success = UserService.getInstance().doSignUp(this);
         if (success) {
             String createmsg = "User created !";
             sessionMap.put("Createmsg", createmsg);
           
            System.out.println("returning Success from doLogin method");
            result = "SUCCESS";
            
        } else {
            System.out.println("returning Failure from doLogin method");
        }
         return result; 
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
