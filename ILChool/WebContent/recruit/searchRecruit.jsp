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
    <link href="<c:url value='/css/select_recruit.css'/>" rel="stylesheet" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<script>
	function search() {	
		form.submit();
	}

	function memberCreate(targetUri) {
		form.action = targetUri;
		form.submit();
	}
	</script>
 </head>
  <body>
    <div class="s009">
      <form name="form" method="POST" action="<c:url value='/recruit/search' />">
        <div class="inner-form">
          <div class="basic-search">
            <div class="input-field">
              <input id="search" type="text" name="company_name" onclick="search()" placeholder="기업명을 입력하세요" />
            </div>
          </div>
          <div class="advance-search">
            <div class="row third">
              <div class="input-field">
                <div class="group-btn">
                  <button class="btn-search">검색</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="result">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">기업</th>
			      <th scope="col">제목</th>
			      <th scope="col">기업형태</th>
			      <th scope="col">근무형태</th>
			      <th scope="col">마감일</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:choose>
			  	<c:when test="${is_search_result eq false }">
					<c:forEach var="rec" items="${recList}">
						<tr>
						  <td><a href="<c:url value='/company/view'>
						   		<c:param name='comName' value='${rec.company_name }'/>
				 		 	 </c:url>">${rec.company_name }</a></td>
						  <td><a href="<c:url value='/recruit/view'>
						   		<c:param name='title' value='${rec.title }'/>
				 		 	 </c:url>">${rec.title }</a></td>
						  <td>${rec.company_form }</td>
						  <td>${rec.workingType }</td>
						  <td><fmt:formatDate value="${rec.deadLine}" pattern="yyyy-mm-dd" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${is_search_result eq true }">
					<c:forEach var="rec" items="${finded_recList}">
						<tr>
						  <td><a href="<c:url value='/company/view'>
						   		<c:param name='comName' value='${rec.company_name }'/>
				 		 	 </c:url>">${rec.company_name }</a></td>
						  <td><a href="<c:url value='/recruit/view'>
						   		<c:param name='title' value='${rec.title }'/>
				 		 	 </c:url>">${rec.title }</a></td>
						  <td>${rec.company_form }</td>
						  <td>${rec.workingType }</td>
						  <td><fmt:formatDate value="${rec.deadLine}" pattern="yyyy-mm-dd" /></td>
						</tr>
					</c:forEach>
					</c:when>
					</c:choose>
			  </tbody>
			</table>
    	</div>
        </form>
    </div>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
