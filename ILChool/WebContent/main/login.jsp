<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V5</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="css/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="css/daterangepicker.css">
<!--===============================================================================================-->
<link rel=stylesheet href="<c:url value='css/util.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='css/main.css' />" type="text/css">
<!--===============================================================================================-->

<script>
function login() {
	if (form.memberId.value == "") {
		alert("????????? ID??? ??????????????????.");
		form.memberId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("??????????????? ??????????????????.");
		form.password.focus();
		return false;
	}		
	form.submit();
}

function memberCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-color:#e5f0ff">
			<div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
				<form class="login100-form validate-form flex-sb flex-w" name="form" method="POST" action="<c:url value='/member/login' />" >
					<span class="login100-form-title p-b-53">
						??????
					</span>
					<div class="p-t-31 p-b-9">
						<span class="txt1">
							Username
						</span>
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Username is required">
						<input class="input100" type="text" name="memberId" >
						<span class="focus-input100"></span>
					</div>

					<div class="p-t-13 p-b-9">
						<span class="txt1">
							Password
						</span>

						<!-- <a href="#" class="txt2 bo1 m-l-5">
							Forgot?
						</a> -->
					</div>
					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="password" >
						<span class="focus-input100"></span>
					</div>
					<c:if test="${loginFailed}">
	  	  				<br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	    			</c:if>
					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn" onClick="login()"> &nbsp;
							?????????
						</button>
						<button class="login100-form-btn" onClick="memberCreate('<c:url value='/member/register/form' />')"
>
							????????????
						</button>
					</div>
				
					<!--  <div class="w-full text-center p-t-55">
						<span class="txt2">
							Not a member?
						</span>

						<a href="#" class="txt2 bo1">
							Sign up now
						</a>
					</div>-->
				</form>
				????????? ????????? (admin / admin)
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="js/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="jsselect2.min.js"></script>
<!--===============================================================================================-->
	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="js/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>


</body>
</html>