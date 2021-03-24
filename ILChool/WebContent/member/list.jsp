<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*, model.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	@SuppressWarnings("unchecked") 
	List<Member> memberList = (List<Member>)request.getAttribute("memberList");
	String curmemberId = (String)request.getAttribute("curmemberId");
--%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/member.css' />" type="text/css">


</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
  <tr>
  	<td width="20"></td>
    <td><a href="<c:url value='/member/logout' />">로그아웃(&nbsp;${curmemberId}&nbsp;)</a></td>
  </tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 리스트</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <br>		  
	  <table style="background-color: YellowGreen">
		<tr>
		  <td width="190" align="center" bgcolor="E6ECDE" height="22">사용자 ID</td>
		  <td width="200" align="center" bgcolor="E6ECDE">이름</td>
		  <td width="200" align="center" bgcolor="E6ECDE">이메일</td>
		  <td width="100" align="center" bgcolor="E6ECDE">나이</td>
		</tr>
<%-- 
	if (memberList != null) {	
	  Iterator<Member> memberIter = memberList.iterator();
	
	  //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
	  while ( memberIter.hasNext() ) {
		Member member = (Member)memberIter.next();
--%>	  	
	  <c:forEach var="member" items="${memberList}">  			  	
  		<tr>
		  <td width="190" align="center" bgcolor="ffffff" height="20">
		  	${member.memberId}       <%-- <%=member.getmemberId()%> --%>
		  </td>
		  <td width="200" bgcolor="ffffff" style="padding-left: 10">
			<a href="<c:url value='/member/view'>
					   <c:param name='memberId' value='${member.memberId}'/>
			 		 </c:url>">
			  ${member.name}</a>	 <%-- <%=member.getName()%></a> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${member.email}        <%-- <%=member.getEmail()%> --%>
		  </td>
		  <td width="200" align="center" bgcolor="ffffff" height="20">
		    ${member.age}        <%-- <%=member.getage()%> --%>
		  </td>
		 
		</tr>
	  </c:forEach> 
<%--
	  }
	}
--%>	 
	  </table>	  	 
	  

	</td>
  </tr>
</table>  
</body>
</html>