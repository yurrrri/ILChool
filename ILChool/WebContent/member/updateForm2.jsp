<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Member member = (Member)request.getAttribute("member");
%>

<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/register.css">
        <title>회원정보 수정</title>
    <script>
		function memberModify() {
			if (form.password.value == "") {
				alert("비밀번호를 입력하십시오.");
				form.password.focus();
				return false;
			}
			if (form.password.value != form.password2.value) {
				alert("비밀번호가 일치하지 않습니다.");
				form.name.focus();
				return false;
			}
			if (form.name.value == "") {
				alert("이름을 입력하십시오.");
				form.name.focus();
				return false;
			}
			var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			if(emailExp.test(form.email.value)==false) {
				alert("이메일 형식이 올바르지 않습니다.");
				form.email.focus();
				return false;
			}
			var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
			if(phoneExp.test(form.phone.value)==false) {
				alert("전화번호 형식이 올바르지 않습니다.");
				form.phone.focus();
				return false;
			}
			form.submit();
		}
		
	function memberRemove() {
		return confirm("정말 삭제하시겠습니까?");		
		}
    </script>
    </head>
     <style type="text/css">
   			 body{	background-color: #e5f0ff }
    </style>
    <body >
    <form name="form" method="POST" action="<c:url value='/member/update2' />">
        <div class="d-flex align-items-center light-blue-gradient" style="height: 100vh;">
            <div class="container" >
                <div class="d-flex justify-content-center">
                    <div class="col-md-6">
                        <div class="card rounded-0 shadow">
                            <div class="card-body">
                                <form>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">사용자 아이디: </label> 
                                        <input type="text" class="form-control" id="exampleInputId"  placeholder="Enter id"  name="memberId" value="${member.memberId}">        
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">패스워드: </label>
                                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password"  name="password" value="${member.password}">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">패스워드 확인: </label>
                                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" name="password1" value="${member.password}">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">이름: </label>
                                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter membername" name="name" value="${member.name}">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">이메일:</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Email" name="email" value="${member.email}">
                                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>
                                    
                                      <div class="form-group">
                                        <label for="exampleInputEmail1">전화번호:</label>
                                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter phoneNumber" name="phone" value="${member.phone}">
                          
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">연령대: </label>
                                        <br>
                                        <select name=age class="form-control">
												<option value=1 selected>10대</option>
												<option value=2>20대</option>
												<option value=3>30대</option>
												<option value=4>30대 이상</option>
										</select>
                                	</div>
                                    <button type="submit"  style="background: rgb(68, 93, 119)" class="btn btn-primary" align="center" onClick="memberModify()">회원정보 수정</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
</div>
                <!-- Optional JavaScript -->
                <!-- jQuery first, then Popper.js, then Bootstrap JS -->
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
            </div>
    </body>
</html>
