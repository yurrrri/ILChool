<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 등록 화면</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
function studyCreate() {
	if (form.title.value == "") {
		alert("제목을 입력하십시오.");
		form.title.focus();
		return false;
	}
	if (form.companyName.value == "") {
		alert("기업을 입력하십시오.");
		form.companyname.focus();
		return false;
	}
	if (form.category.value == "") {
		alert("카테고리를 입력하십시오.");
		form.category.focus();
		return false;
	}
	if (form.location.value == "") {
		alert("지역을 선택하십시오.");
		form.location.focus();
		return false;
	}
	if (form.maxHeadcount.value == "") {
		alert("인원을 입력하십시오.");
		form.headcount.focus();
		return false;
	}
	form.submit();
	
	if (form.period.value == "") {
		form.period.value = 999;
	}
}

</script>
</head>
<body>
	<div id="header" align="center" style="margin: 30px 0 0 0;">
	<h1><b>스터디 구인 게시판</b></h1>
	</div>
	
	
	<div id="inputForm">
	
	<form name="form" method="POST" action="<c:url value='/study/create' />">
	<div id="title" align="center">
	<img src="<c:url value='/images/right.png' />" height="30px" width="30px" />제목<input type="text" id="title" style="width: 1120px; height: 45px;" name="title" required>
	</div>
	
	<div id="formContents">
  	<div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCompany">기업 *</label>
      <input type="text" class="form-control" id="inputCompany" name="companyName" placeholder="기업 명" required>
    </div>
    <div class="form-group col-md-6">
      <label for="inputCategory">카테고리 *</label>
      <input type="text" class="form-control" id="inputCategory" name="category" placeholder="ex) 스터디, 면접, ...." required>
    </div>
  	</div>
 	<div class="form-group">
    	<label for="inputAge">지역 *</label>
      	<select id="inputAge" class="form-control" name="location" required>
        	<option selected value="">지역 명</option>
        	<option>서울</option>
        	<option>인천</option>
        	<option>부산</option>
      	</select>
  	</div>
  	<div class="form-group">
    	<label for="inputMember">인원 *</label>
    	<input type="number" class="form-control" id="inputMember" name="maxHeadcount" placeholder="인원 수" required>
  	</div>
  	<div class="form-row">
    <div class="form-group col-md-6">
      	<label for="inputCompany">기간</label>
      	<input type="number" class="form-control" id="inputPeriod" name="period" placeholder="개월 단위로 입력하세요.">
    </div>
    <div class="form-group col-md-6">
      	<label for="inputAge">연령대</label>
      	<select id="inputAge" class="form-control" name="age">
        	<option selected>Choose...</option>
        	<option>~20</option>
        	<option>20~30</option>
        	<option>30~40</option>
      	</select>
    </div>
    </div>
    
    </div>
    * 표시는 필수 입력
  	<div align="right">
  	<button type="submit" class="btn btn-primary" id="btnsubmit" onClick="studyCreate()">등록</button>
  	<a href="<c:url value='/study/list' />"><button type="button" class="btn btn-primary" id="btnCancel">취소</button></a>
  	</div>
	</form>
	</div>
</body>
</html>