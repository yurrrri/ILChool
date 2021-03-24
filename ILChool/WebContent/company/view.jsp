<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<th>이름</th>
				<td>${com.name}</td>
			  </tr>
			  <tr>
				<th>기업 홈페이지</th>
				<td>${com.url}</td>
			  </tr>
			  <tr>
				<th>기업 형태</th>
				<td>${com.form}</td>
			  </tr>
			  <tr>
				<th>기업 소개</th>
				<td>${com.summary}</td>
			  </tr>
			</table>
		    	<a href="<c:url value='/recruit/list' />">채용 공고 목록</a>
    	</div>
        </form>
    </div>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
