<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Study study = (Study)request.getAttribute("study"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 상세 정보</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<style>
	body {
		background-color: #e5f0ff;
	}
	
	#info {
		width: 1300px;
		height: 400px;
	}
	
	#infoHeader {
		width: 1300px;
		height: 50px;
	}
	
	th, td {
		border-top: 1px solid #d3d3d3;
	}
	
	th {
		width: 300px;
	}
	
	#title {
		font-size: 20px;
		margin: 20px 0 20px 0;
	}
	
	#written {
		font-size: 20px;
		margin: 20px 0 20px 0;
		text-align: right;
	}
	
	#content {
		background-color: #FDFDFE;
		box-shadow: 0 0 15px #D8D8D8;
	}
	
	
	#btnRegister {
		background: rgb(68, 93, 119);
		width: 120px;
		height: 40px;
		margin: 0 0 20px 0;
	}
	
	#btnDelete {
		background: #D8D8D8;
		border: none;
		width: 60px;
		height: 40px;
		margin: 0 0 20px 0;
	}
	
	#studyInformation {
		position: absolute;
		left: 50%;
		width: 1500px;
		margin: 70px 0 0 -750px;
		
	}
	
</style>
<script>
function studyDelete() {
	var loginMember = "<%=(String)session.getAttribute("memberId") %>";
	var writer = ${study.writer};
	
	if (loginMember == writer)
		location.href="<c:url value='/study/delete?study_id=${study.studyID}' />";
	else
		alert("다른 사용자의 게시물은 삭제할 수 없습니다.");
}

function studyRegister() {
	var list = new Array();
	<c:forEach var="member" items="${study.memberList}" varStatus="listIds">
		list.push("${member.memberId}");
	</c:forEach>
	var loginMember= "<%=(String)session.getAttribute("memberId") %>";
	var writer = ${study.writer};
	
	if (loginMember == writer) {
		alert("이미 가입된 사용자입니다.");
		return false;
	}
	
	for (i=0; i<list.length; i++) {
		if (loginMember == list[i]) {
			alert("이미 가입된 사용자입니다.");
			return false;
		}
	}
	
	
	alert("신청되었습니다.");
	location.href="<c:url value='/study/register?study_id=${study.studyID}' />";
	
}
</script>
</head>
<body>
	<div id="header" align="center" style="margin: 30px 0 0 0;">
	<h1><b>스터디 상세 정보</b></h1>
	</div>
	
	<div id="studyInformation">
	<div align="right">
		<button type="button" class="btn btn-primary" id="btnDelete" onClick="studyDelete()">삭제</button>
	</div>
	<div id="content" align="center">
	<table id="infoHeader">
		<tr>
			<th id="title">제목:${study.title}</th>
			<th id="written">작성자:${study.writer} &nbsp; &nbsp; &nbsp; &nbsp;
				작성일:${study.reportingDate}
			</th>
		</tr>
	</table>
	<table id="info">
		<tr>
			<th>기업</th>
			<td>${study.companyName}</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>${study.category}</td>
		</tr>
		<tr>
			<th>지역</th>
			<td>${study.location}</td>
		</tr>
		<tr>
			<th>연령대</th>
			<td>${study.age}</td>
		</tr>
		<tr>
			<th>기간</th>
			<td>${study.period}</td>
		</tr>
		<tr>
			<th>인원</th>
			<td><c:if test="${study.currHeadcount ge study.maxHeadcount }"><p style="color:red; "></c:if>${study.currHeadcount}/${study.maxHeadcount}</td>
		</tr>
		<tr>
			<th>회원</th>
			<td>
				<c:forEach var="member" items="${study.memberList}">
					<br>${member.memberId}
				</c:forEach>
			</td>
		</tr>
	
	</table>
	<c:choose>
		<c:when test="${study.currHeadcount lt study.maxHeadcount }">
			<button type="button" class="btn btn-primary" id="btnRegister" onClick="studyRegister()">신청</button>
		</c:when>
		<c:when test="${study.currHeadcount ge study.maxHeadcount }">
			<p style="color:red; ">신청이 마감되었습니다.</p>
		</c:when>
	</c:choose>
	</div>
	</div>
	
	
	
	
</body>
</html>