/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;


import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }
    
    public ArrayList getAllEmployees()
    {
        ArrayList empList = new ArrayList();
          String sql = "SELECT employees.employesId, employees.firstName, employees.lastName,employees.phone,employees.address,employees.gender,employees.age,departments.departmentName,roles.rolesName,employees.basicSalary,employees.carAllowance,employees.specialAllowance\n"
                    + "FROM employees\n"
                    + "INNER JOIN roles ON employees.roleId = roles.rolesId\n"
                    + "INNER JOIN departments ON employees.departmentId = departments.departmentId WHERE status=0;";
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println(sql);
            while(rs.next())
            {
               Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employesId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));

                empList.add(emp);
                
            }
            
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("Total rows:"+empList.size());
        return empList;
    }
    
     public static boolean addEmployee(Employee emp) {
        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO employeedb.employees\n"
                    + "(firstName,lastName,phone,address,gender,age,basicSalary,carAllowance,departmentId,roleId)\n"
                    + "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? );";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setString(7, emp.getBasicSalary());
            preparedStatement.setString(8,emp.getCarAllowance());
            preparedStatement.setString(9, emp.getDepartmentId());
            preparedStatement.setString(10, emp.getRoleId());
//            preparedStatement.setString(11, emp.getEmployesId());

         System.out.println(sql);
            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;

    }
 
 public ArrayList searchEmployee(Employee employee) throws SQLException{
     ArrayList empList = new ArrayList();
       
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, roles r where e.departmentId=d.departmentId and e.roleId=r.rolesId "
                    + "having firstName like ?"
                    + "and lastName like ?"
                    + "and gender like ?"
                    + "and departmentName like ?"
                    + "and rolesName like ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,employee.getFirstName()+"%");
            preparedStatement.setString(2,employee.getLastName()+"%");
            preparedStatement.setString(3,employee.getGender()+"%");
            preparedStatement.setString(4,employee.getDepartmentName()+"%");
             preparedStatement.setString(5,employee.getRoleName()+"%");
//            preparedStatement.setString(5,request.getParameter("rolesName")+"%");
            System.out.println("sql = "+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employesId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setDepartmentId(rs.getString("departmentId"));
                emp.setRoleName(rs.getString("rolesName"));
                emp.setRoleId(rs.getString("rolesId"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));

                empList.add(emp);

            }
         return empList;
 
 }
 
     public static Employee getEmployee(Employee emp1) {
        Employee emp = new Employee();

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, roles r where e.departmentId=d.departmentId and e.roleId=r.rolesId and  e.employesId =?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,emp1.getEmployeeId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employesId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("rolesName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;
    }
     
     
      public static boolean updateEmployee(Employee emp) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees\n"
                    + "SET firstName = ? , lastName = ? , phone = ? , address = ? ,gender = ? ,age = ? ,basicSalary = ? ,carAllowance = ?,departmentId=?,roleId=? WHERE employesId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setString(7, emp.getBasicSalary());
            preparedStatement.setString(8, emp.getCarAllowance());
            preparedStatement.setString(9, emp.getDepartmentId());
            preparedStatement.setString(10, emp.getRoleId());
            preparedStatement.setString(11, emp.getEmployeeId());

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
                System.out.println(sql);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
      
     public static boolean deleteEmployee(Employee emp) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees SET status = 1 WHERE employesId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getEmployeeId());
           

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
