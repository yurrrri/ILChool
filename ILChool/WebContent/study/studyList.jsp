<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@	page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% @SuppressWarnings("unchecked") 
List<Study> studyList = (List<Study>)request.getAttribute("studyList"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 게시판</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<style>
		#header {
			height:100px;
		}
	
		#add {
			position: absolute;
			right: 10px;
			width: 100px;
			height: 40px;
			background: rgb(68, 93, 119);
		}
		
		#search {
			width: 60px;
			height: 40px;
		}
		
		#menu {
			height: 40px;
			margin: 0 0 50px 0;
		}
		
		body {
			background-color: #e5f0ff;
		}
		
		.list {
			background-color: #ffffff;
		}
		
		table {
			border-spacing: 0 10px;
			border-collapse: separate;
		}
		
	</style>
</head>
<body>
	<div id="header" align="center" style="margin: 30px 0 0 0;">
	<h1><b>스터디 구인 게시판</b></h1>
	</div>
	
	<a href="<c:url value='/main/MainUI.jsp' />">메인화면</a>
	
	<div id="menu">
	<form action="<c:url value='/study/list' />" method="GET">
	<select style="height: 40px; width:90px;" name="option">
		<option value="company">기업</option>
		<option value="category">종류</option>
		<option value="location">지역</option>
	</select>
	<input type="search" name="search" style="height: 40px;">
	<button type="submit" id="search" class="btn btn-secondary">검색</button>
	</form>
	
	<a href="<c:url value='/study/create/form' />"><button type="button" name="등록" id="add" class="btn btn-primary">등록</button></a><a href="<c:url value='/study/list' />">검색 결과 초기화</a>
	</div>
	
	<table class="table">
  	<thead>
    	<tr>
      		<th scope="col">제목</th>
      		<th scope="col">작성자</th>
      		<th scope="col">작성일</th>
      		<th scope="col">상태</th>
    	</tr>
    	<!-- studyList 출력 -->
    	<c:forEach var="study" items="${studyList}">
    		<tr class="list">
    			<td><a href="<c:url value='/study/view?study_id=${study.studyID}' />">${study.title}</a></td>
    			<td>${study.writer}</td>
    			<td>${study.reportingDate}</td>
    			<!-- if (currHeaedcount = maxHeadcount) 빨간 글씨 표시 -->
    			<td>${study.currHeadcount}/${study.maxHeadcount}</td>
    		</tr>
    	</c:forEach>
  	</thead>
  	<tbody>
    	
  	</tbody>
	</table>
	
</body>
</html>