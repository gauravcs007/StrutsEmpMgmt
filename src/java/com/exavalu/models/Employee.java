/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int status;
    private String gender;
    private String age;
    private String departmentId;
    private String roleId;
    private String basicSalary;
    private String carAllowance;
    private String departmentName;
    private String roleName;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String doSearch() throws SQLException {
        String result = "SUCCESS";
        ArrayList EmpList = EmployeeService.getInstance().searchEmployee(this);
        sessionMap.put("EmpList", EmpList);
        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("RoleList", RoleList);
        return result;
    }

    public String doAdd() throws SQLException {
        String result = "SUCCESS";
        EmployeeService.addEmployee(this);
        ArrayList DeptList = DepartmentService.getAllDepartment();
        sessionMap.put("DeptList", DeptList);
        ArrayList RoleList = RoleService.getAllRole();
        sessionMap.put("RoleList", RoleList);
        
            String createdMsg = "Employee created successfully!!";
            sessionMap.put("CreatedMsg", createdMsg);
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);

            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

       
           
        
        return result;
    }
    
    public String doGetEmp(){
         String result = "FAILURE";
        Employee emp = EmployeeService.getEmployee(this);
        sessionMap.put("Emp", emp);

        ArrayList depList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        sessionMap.put("DeptList", depList);
        sessionMap.put("RoleList", roleList);

        if (true) {

            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }
    
    public String doUpdate() throws Exception {

        String result = "FAILURE";
        boolean success = EmployeeService.updateEmployee(this);

        if (success) {
            String updateMsg = "updated Employee";
            sessionMap.put("UpdateMsg", updateMsg);
            
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            
            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }
    
     public String doDelete() throws Exception {

        String result = "FAILURE";
        boolean success = EmployeeService.deleteEmployee(this);

        if (success) {
            String updateMsg = "deleted successfully!";
            sessionMap.put("UpdateMsg", updateMsg);
            
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            
            
            result = "SUCCESS";

        }
        return result;
    }

    public String ShowEmployee() {
        String result = "FAILURE";
        ArrayList empList = EmployeeService.getInstance().getAllEmployees();
        if (empList != null) {
            result = "SUCCESS";
        }

        return result;
    }

    public static ArrayList getAllEmployees() {
        ArrayList empList = EmployeeService.getInstance().getAllEmployees();
        return empList;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getCarAllowance() {
        return carAllowance;
    }

    public void setCarAllowance(String carAllowance) {
        this.carAllowance = carAllowance;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
