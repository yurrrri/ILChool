<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>TO-DO LIST 등록</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<style>
	#inputForm {
		position:absolute;
		width: 1200px;
		height:400px;
		left: 50%;
		margin: 100px 0 0 -600px;
	}
	
	#title {
		margin: 0 0 20px 0;
	}
	
	#btnCancel {
		background: #D8D8D8;
		border: none;
	}
	
	#btnsubmit {
		background: rgb(68, 93, 119);
	}
	
	#formContents {
		background: #FDFDFE;
		padding: 20px 30px 20px 30px;
		margin: 0 0 20px 0;
		box-shadow: 0 0 15px #D8D8D8;
	}
	
	button {
		width: 120px;
		height: 40px;
	}
	
	body {
		background-color: #e5f0ff;
	}
</style>
<script>
window.history.forward();
function noBack(){window.history.forward();
}
function todoCreate() {
	var loginMember = "<%=(String)session.getAttribute("memberId") %>";
	if(loginMember != null){
		location.href="<c:url value='/todo/create?memberid=${todo.memberid}' />";
	}
	if (form.todo.value == "") {
		alert("TODO를 입력하십시오.");
		form.todo.focus();
		return false;
	}
	
	form.submit();
}

</script>
</head>
<body>
	<div id="header" align="center" style="margin: 30px 0 0 0;">
	<h1><b>TO-DO LIST 등록</b></h1>
	</div>
	
	
	<div id="inputForm">
	
	<form name="form" method="POST" action="<c:url value='/todo/create'/>">
	<div id="title" align="center">
	<input type="text" id="todo" style="width: 1120px; height: 45px;" name="TO_DO" value="${todo.TO_DO}">
	<button type="submit" class="btn btn-primary" id="btnsubmit">등록</button>
  	<a href= "<c:url value='/main'/>"><button type="button" class="btn btn-primary" id="btnCancel">취소</button></a>
	</div>
	</form>
	<table class="table">
  	<thead>
  	<tr>
  		<th scope="col">TODO</th>
  		<th><%= request.getParameter("memberId") %></th>
  	</tr>
    	<tr class="list">
      		<!--  <a href="<c:url value='/todo/create'>
      			<c:param name="TO_DO" value='${todo.TO_DO}'></c:param>
      			</c:url>">${todo.TO_DO}
      			</a> -->
      		<c:forEach var="todo" items="${todolist}">
      			<tr class="list">
      				<td>${todo.TO_DO}</td>
      			</tr>
      		</c:forEach>
    	</tr>
    	<!-- studyList 출력 -->
    	
  	</thead>
  	</table>
  	<div align="right">
  	
  	</div>
	</div>
</body>
</html>