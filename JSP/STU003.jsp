<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ page import="DAO.CourseDAO"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <spring:url value="/resources/theme/css/test.css" var="testCss" />
<link href="${testCss}" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Course Registration</title>
</head>

<body>
  <div id="testheader">
    <div class="container">
        <div class=row>        
            <div class="col-md-5 ">
        <a href="MNU001.jsp"><h3>Student Registration</h3></a>
    </div>  
    <div class="col-md-6">
					<p>User: ${sessionScope.loginName}</p>
					<p>Current Date : ${sessionScope.date}</p>
				</div> 
    <div class="col-md-1" >
       <input type="button" class="btn-basic" id="lgnout-button"
						value="Log Out" onclick="location.href='/MvcProjectWithSpring/logOut'">
    </div>        
</div>
</div>

</div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
           <div class="dropdown-container">
           <a href="courseAddPage">Course Registration </a>
          <a href="stuAddPage">Student Registration </a>
          <a href="stuSearchPage">Student Search </a>
        </div>
        <a href="searchUserPage">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
    
    
      <form:form class="row g-3 mt-3 ms-2" action="StudentSearchPage" method="post" modelAttribute="studentbean">
        <div class="col-auto">
          <form:label for="staticEmail2" class="visually-hidden" path="searchid">studentID</form:label>
          <form:input type="text"  class="form-control" id="staticEmail2" placeholder="Student ID" path="searchid"/>
        </div>
        <div class="col-auto">
          <form:label for="form:inputPassword2" class="visually-hidden" path="searchname">studentName</form:label>
          <form:input type="text" class="form-control" id="inputPassword2" placeholder="Student Name" path="searchname"/>
        </div>
        <div class="col-auto">
            <form:label for="form:inputPassword2" class="visually-hidden" path="searchcourse">Course</form:label>
            <form:input type="text" class="form-control" id="inputPassword2" placeholder="Course" path ="searchcourse"/>
          </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-success mb-3">Search</button>
        </div>
        <div class="col-auto">
          <button type="reset" class="btn btn-secondary mb-3">Reset</button>
        </div>
      </form:form>
<div>
<div>${errorFill}</div>
      <table class="table table-striped" id="stduentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <tbody>
         <c:set var="count" value="0" scope="page"/>
        <c:forEach items="${studentList}" var = "data">
        <c:set var="count" value="${count+1}" scope="page"/>
          <tr>
            <th scope="row">${count}</th>
            <td>${data.studentid}</td>
            <td>${data.studentname}</td>
            <td>
           <c:forEach items="${data.course}" var="a">
            ${a},
            </c:forEach>
            </td>
            <td>
              <a href="studentupdatedpage/${data.studentid}"><button type="button" class="btn btn-secondary mb-2">See More</button></a> 
            </td>
          </tr>
         </c:forEach>
        </tbody>
      </table>
    </div>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>

</html>
