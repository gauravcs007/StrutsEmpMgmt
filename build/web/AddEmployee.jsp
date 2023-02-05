<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.services.RoleService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.104.2">
        <title>Sign in - Employee Management</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <meta name="theme-color" content="#712cf9">


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">

    </head>

    <body class="text-center">


        <main class="form-signin w-100 m-auto">
            <form action="AddEmployee" method="Post">
                <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                <h1 class="h3 mb-3 fw-normal">Please provide below information</h1>

<!--                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                    <label for="floatingPassword">Password</label>
                </div>-->
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="first name" name="firstName" required>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="last name" name="lastName" required>
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="address" name="address" required>
                    <label for="floatingInput">Address</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="phone" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" name='phone' required/>
                    <label for="floatingInput">Phone</label>
                </div>
                <div class="form-floating">
                    <select name="gender" class="form-select" id="Gender" required>
                        <option value="" hidden>Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>


                    </select>

                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="age" name="age">
                    <label for="floatingInput">Age</label>
                </div>
                 <div class="form-floating">
                        <c:set var="deptList" value="${DepartmentService.getAllDepartment()}"/> 
                    <select name="departmentId" class="form-select" id="departmentId" required>
                        <option value="0">Select a Department</option>
                       <c:forEach items="${deptList}" var="dept">
                        <option value=${dept.getDepartmentId()}> ${dept.getDepartmentName()}  </option>
                       </c:forEach>
                    </select>
                </div>
              
                <div class="form-floating">
        
                        <c:set var="roleList" value="${RoleService.getAllRole()}"/>
                       
                    <select name="roleId" class="form-select" id="roleId" required>
                        <option value="0">Select a Role</option>
                        <c:forEach items="${roleList}" var="role">
                        <option value=${role.getRoleId()}>${role.getRoleName()}</option>
                     </c:forEach>
                    </select>
                       
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" required>
                    <label for="floatingInput">Basic Salary</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" >
                    <label for="floatingInput">Car Allowance</label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Create</button>
<!--                <a href="landingPage.jsp">
                    <button type="button" class="w-100 btn btn-lg btn-warning">Cancel</button>
                </a>-->
                <p class="mt-5 mb-3 text-muted">&copy; 2017?2022</p>
            </form>
        </main>



    </body>
</html>
