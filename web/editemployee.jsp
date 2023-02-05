<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.exavalu.models.Role"%>
<%@page import="com.exavalu.services.RoleService"%>
<%@page import="com.exavalu.models.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.models.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <title>Employee Management</title>      
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/signin.css" rel="stylesheet">  
        <!-- Custom styles for this template -->
        <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.3/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.3/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.3/assets/img/favicons/safari-pinned-tab.svg" color="#712cf9">
<link rel="icon" href="/docs/5.3/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#712cf9">




    
    <!-- Custom styles for this template -->
    <link href="css/sign-in.css" rel="stylesheet">
    </head>
    <body class="text-center">

        <main class="form-signin w-100 m-auto">
            
            
            <c:set var="emp" value="${Emp}"/>
            
            <form action="SaveEmployee" method="Post">
                
                 <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                <h1 class="h3 mb-3 fw-normal">Please edit employee data</h1>
                  <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Employee ID" name="employeeId" value=${emp.getEmployeeId()} readonly >
                    <label for="floatingInput">Employee ID</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="first name" name="firstName" value=${emp.getFirstName()}>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="last name" name="lastName" value=${emp.getLastName()}>
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="address" name="address" value=${emp.getAddress()}>
                    <label for="floatingInput">Address</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="phone" name="phone" value=${emp.getPhone()}>
                    <label for="floatingInput">Phone</label>
                </div>
               <div class="form-floating">
                    <select name="gender" class="form-select" id="Gender" required >
                         <option value="0" disabled>Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="age" name="age" value=${emp.getAge()}>
                    <label for="floatingInput">Age</label>
                </div>
              <div class="form-floating">
                        <c:set var="deptList" value="${DeptList}"/> 
                    <select name="departmentId" class="form-select" id="departmentId" required>
                         <option value="0" disabled>select a department</option>
                        <c:forEach items="${deptList}" var="dep">

                            <option value="${dep.departmentId}" <c:if test="${emp.departmentId == dep.departmentId}">selected</c:if>>
                                ${dep.departmentName}
                            </option>

                        </c:forEach>

                    </select>
                </div>
                </div>
                 <div class="form-floating">
        
                        <c:set var="roleList" value="${RoleList}"/>
                       
                    <select name="roleId" class="form-select" id="roleId" required>
                        <option value="0" disabled>select a Role</option>

                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.roleId}" <c:if test="${emp.roleId == role.roleId}">selected</c:if>>
                                ${role.roleName}
                            </option>
                        </c:forEach>
                    </select>
                       
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" value=${emp.getBasicSalary()}>
                    <label for="floatingInput">Basic Salary</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" value=${emp.getCarAllowance()}>
                    <label for="floatingInput">Car Allowance</label>
                </div>
                
                <button class="w-100 btn btn-lg btn-primary" type="submit">Save</button>
                
            </form>
        </main>
    </body>
</html>