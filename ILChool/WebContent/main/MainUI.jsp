<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <% request.setCharacterEncoding("UTF-8"); %>
   <%@ page import="java.util.*" %>
	<%@page import="model.*" %>
<% @SuppressWarnings("unchecked") 
List<TO_DO_LIST> todolist = (List<TO_DO_LIST>)request.getAttribute("todolist"); %>
 <% session.setAttribute("memberId", request.getParameter("memberId")); %>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인UI</title>


<style type = "text/css">
table.a{
	border-collapse :collapse;
}
table.a,td.a,th.a{
	border:solid 1px #000000;
	width : 700px;
	height : 0px;
	text-align : center;
}
caption.a{
	 padding: 0px;
}
table.b{
	border-collapse :collapse;
}
table.b,td.b,th.b{
	border:solid 0px #FFFFFF;
	width : 700px;
	height : 0px;
	text-align : center;
}
caption.b{
	 padding: 0px;
}
table,td,th{
	border : 1px solid #FFFFFF;
	border-collapse : collapse;
	width : 1450px;
	align : center;
	border-right:none;
	border-left:none;
	border-top:none;
	border-bottom:none;

}
caption{
	 padding: 0px;
}

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Helvetica Nueue",Arial,Verdana,sans-serif;
		background-color: #e5f0ff;
		}
		
		#wrap {
		width: 300px;
		margin: 0 10px;
		}
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		text-align: left;
		}

	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
		}

	.external-event { /* try to mimick the look of a real event */
		margin: 10px 0;
		padding: 2px 4px;
		background: #3366CC;
		color: #fff;
		font-size: .85em;
		cursor: pointer;
		}

	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
		}

	#external-events p input {
		margin: 0;
		vertical-align: middle;
		}

	#calendar {
/* 		float: right; */
        margin: 0 auto;
		width: 900px;
		background-color: #FFFFFF;
		  border-radius: 6px;
        box-shadow: 0 1px 2px #C3C3C3;
		}
body {
  margin: 0;
  min-width: 150px;
}

/* Include the padding and border in an element's total width and height */
* {
  box-sizing: border-box;
}

/* Remove margins and padding from the list */
ul {
  margin: 0;
  padding: 0;
}

/* Style the list items */
ul li {
  cursor: pointer;
  position: relative;
  padding: 12px 8px 12px 40px;
  list-style-type: none;
  background: #eee;
  font-size: 18px;
  transition: 0.2s;
  
  /* make the list items unselectable */
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Set all odd list items to a different color (zebra-stripes) */
ul li:nth-child(odd) {
  background: #f9f9f9;
}

/* Darker background-color on hover */
ul li:hover {
  background: #ddd;
}

/* When clicked on, add a background color and strike out text */
ul li.checked {
  background: #888;
  color: #fff;
  text-decoration: line-through;
}

/* Add a "checked" mark when clicked on */
ul li.checked::before {
  content: '';
  position: absolute;
  border-color: #fff;
  border-style: solid;
  border-width: 0 2px 2px 0;
  top: 10px;
  left: 16px;
  transform: rotate(45deg);
  height: 15px;
  width: 7px;
}

/* Style the close button */
.close {
  position: absolute;
  right: 0;
  top: 0;
  padding: 12px 16px 12px 16px;
}

.close:hover {
  background-color: #f44336;
  color: white;
}

/* Style the header */
.header {
  background-color: rgb(2, 60, 92);
  padding: 30px 40px;
  color: white;
  text-align: center;
}

/* Clear floats after the header */
.header:after {
  content: "";
  display: table;
  clear: both;
}

/* Style the input */
input {
  margin: 0;
  border: none;
  border-radius: 0;
  width: 75%;
  padding: 10px;
  float: left;
  font-size: 16px;
}

/* Style the "Add" button */
.addBtn {
  padding: 10.5px;
  width: 25%;
  background: rgb(68, 93, 119);
  color: #FFFFFF;
  float: left;
  text-align: center;
  font-size: 16px;
  cursor: pointer;
  transition: 0.3s;
  border-radius: 0;
}

.addBtn:hover {
  background-color: #bbb;
}

</style>
</head>
<body>


	<table>
		<tr> <!-- 1번줄 -->
			<td colspan="7"><img src="<c:url value="/images/KakaoTalk_20201202_052425961.png"/>" witdh="300px" height="150px" style="float:left"></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>		
			<td align = "right"><%= session.getAttribute("memberId") %>님<br><a href="<c:url value='/member/view2?memberId=${memberId}' />">수정&nbsp;</a><a href="<c:url value="/member/logout" />">로그아웃</a></td>
		</tr>
	
		<tr> <!-- 2번줄 -->
			<td rowspan="5" align = "center" colspan="3"></td>
			<td rowspan="5">
			<div id='wrap'>
			<div id='calendar'></div>
			<div style='clear:both'></div>
			</div>
			<link href="<c:url value='/css/fullcalendar.css'/>" rel='stylesheet' />
			<link href="<c:url value='/css/fullcalendar.print.css' />"rel='stylesheet' media='print' />
			<script src="<c:url value='/js/jquery-1.10.2.js'/>" type="text/javascript"></script>
			<script src="<c:url value='/js/jquery-ui.custom.min.js' />"type="text/javascript"></script>
			<script src="<c:url value='/js/fullcalendar.js' />"type="text/javascript"></script></td>
			<td rowspan="5" colspan="2">&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td align = "center"><input type = "button" value="채용공고검색" style="background-color : rgb(68, 93, 119); 
				color : #FFFFFF;" Onclick="location.href='<c:url value="/recruit/list" />'"></td>
			<td align = "center" colspan="2"><input type = "button" value="스터디게시판" style="background-color : rgb(68, 93, 119); 
				color : #FFFFFF;" Onclick="location.href='<c:url value="/study/list" />'"></td>
		</tr>
	
		<tr> <!-- 3번줄 -->
         <td>&nbsp;</td>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
         
         <td colspan="3">
            <select name="company">
            	<option value="">채용 위시 회사 목록</option>
			    <c:forEach var="rec" items="${recruit_wish_list}">
					<option value="">${rec.recruit.company_name}</option>
				</c:forEach>
            </select>
         </td>
      </tr>
      
   
      <tr> <!-- 4번줄 -->
         <td>&nbsp;</td>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
         <%
            int ran = 0;
            String[] list = new String[5];
            list[0] = "이 또한 지나가리라";
            list[1] = "구름 저편은 언제나 푸른 하늘이 있다";
            list[2] = "모든 일은 쉽기 전까지 다 어렵다.";
            list[3] = "꿈꿀 수 있다면, 실현할 수 있다.";
            list[4] = "나는 이겨낼 것이다.";
         
            Random random = new Random();
            for(int x = 0; x < list.length; x++){
               ran = (int)(Math.random() * list.length);
            }
            String result = list[ran];
         %>
         <td colspan="4" ><input type="text" style="text-align :center" value="<명언>"><input type = "text" value="<% out.print(result); %>"></td>
         <!-- <a href = "<c:url value='/company/view.jsp'/>"><input type = "button" value="회사추가" style="background-color : rgb(68, 93, 119); color : #FFFFFF;"></a>< -->
      </tr>
	
		<tr> <!-- 5번줄 -->
			<td>&nbsp;</td>
			<td colspan="4" rowspan="4"><div id="myDIV" class="header" style= "margin : 0px auto auto 430px;">
				  <h2>My To Do List</h2>
				  <input type="text" id="myInput" placeholder="Add버튼을 눌러 입력해주세요.">
				  <span  class="addBtn" onClick="location.href='<c:url value="/todo/list" />'">Add</span>
				</div>
				<form>
				<ul id="myUL" style="margin : auto auto auto 430px;">
			    	<c:forEach var="todo" items="${todolist}">
						<li>${todo.TO_DO}</li>
					</c:forEach>
				</ul>
				</form>
				
			</td>
		</tr>
		
		<tr> <!-- 6번줄 -->
			<td>&nbsp;</td>
			
		</tr>
		
	</table>
	<!-- 달력부분 코드 -->
	<script>

	$(document).ready(function() {
	    var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		/*  className colors

		className: default(transparent), important(red), chill(pink), success(green), info(blue)

		*/


		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events div.external-event').each(function() {

			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};

			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});

		});


		/* initialize the calendar
		-----------------------------------------------------------------*/

		var calendar =  $('#calendar').fullCalendar({
			header: {
				left: 'title',
				center: 'agendaDay,agendaWeek,month',
				right: 'prev,next today'
			},
			editable: true,
			firstDay: 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
			selectable: true,
			defaultView: 'month',

			axisFormat: 'h:mm',
			columnFormat: {
                month: 'ddd',    // Mon
                week: 'ddd d', // Mon 7
                day: 'dddd M/d',  // Monday 9/7
                agendaDay: 'dddd d'
            },
            titleFormat: {
                month: 'MMMM yyyy', // September 2009
                week: "MMMM yyyy", // September 2009
                day: 'MMMM yyyy'                  // Tuesday, Sep 8, 2009
            },
			allDaySlot: false,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
			},
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');

				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);

				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;

				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}

			},

			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false,
					className: 'info'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false,
					className: 'info'
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false,
					className: 'important'
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false,
					className: 'important'
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false,
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/',
					className: 'success'
				}
			],
		});


	});

</script>
<script>
// Create a "close" button and append it to each list item
var myNodelist = document.getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++) {
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  myNodelist[i].appendChild(span);
}

// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
  close[i].onclick = function() {
    var div = this.parentElement;
    div.style.display = "none";
  }
}

// Add a "checked" symbol when clicking on a list item
var list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
  if (ev.target.tagName === 'LI') {
    ev.target.classList.toggle('checked');
  }
}, false);

// Create a new list item when clicking on the "Add" button
function newElement() {
  var li = document.createElement("li");
  var inputValue = document.getElementById("myInput").value;
  var t = document.createTextNode(inputValue);
  li.appendChild(t);
  if (inputValue === '') {
    alert("You must write something!");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  document.getElementById("myInput").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
}
</script>

</body>
</html>