<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ko-kr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,600,700" rel="stylesheet" />
    <!--  css 파일 불러오는 부분 -->
    <link href="<c:url value='/css/company.css'/>" rel="stylesheet" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="s009">
      <form>
        <div class="result">
			<table class="table">
			  <tr>
				<th>기업 이름</th>
				<td>${recruit.company_name}</td>
			  </tr>
			  <tr>
				<th>제목</th>
				<td>${recruit.title}</td>
			  </tr>
			  <tr>
				<th>채용 공고 사이트</th>
				<td>${recruit.recruit_url}</td>
			  </tr>
			  <tr>
			  	<th>근무 형태</th>
			  	<td>${recruit.workingType }</td>
			  </tr>
			  <tr>
				<th>마감일</th>
				<td><fmt:formatDate value="${recruit.deadLine}" pattern="yyyy-mm-dd" /></td>
			  </tr>
			</table>
		    	<a href="<c:url value='/recruit/addwish'>
		    	   <c:param name="company_name" value='${recruit.company_name}' />
   					<c:param name="recruit_url" value='${recruit.recruit_url}' />
   					<c:param name="company_form" value='${recruit.company_form }' />
   					<c:param name="title" value='${recruit.title }' />
   					<c:param name="workingType" value='${recruit.workingType }' />
   					<c:param name="regDate" value='${recruit.regDate }' />
   					<c:param name="deadLine" value='${recruit.deadLine }' />
		    	</c:url>">채용공고 위시 추가</a>
		    	<a href="<c:url value='/recruit/list' />">채용 공고 목록</a></div>
        </form>
    </div>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>