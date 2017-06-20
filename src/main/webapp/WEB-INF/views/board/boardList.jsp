<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".go").click(function() {
			
			document.frm.curPage.value=$(this).attr("id");
			document.frm.search.value='${listinfo.search}';
			document.frm.find.value='${listinfo.find}';
			document.frm.submit();
			/* var cur=$(this).attr("id") */
			/* location.href="${board}List?curPage="+cur+"&search=${listinfo.search}&find=${listinfo.find}"; */
		})
	});

	
	
	
	
	/* var m ="${message}";
	if(m != ''){
		alert(m);
	}  */
</script>
</head>
<body>
	<!-- List 제목  -->
	<h1>${board}</h1>
<div>
<form action="${board}List" name="frm">
	<input type="hidden" name="curPage">

	<select name="search">
		<option value="title">TITLE</option>
		<option value="writer">WRITER</option>
		<option value="contents">CONTENTS</option>
	</select>
	
	<input type="text" name="find">
	<input type="submit" value="SEARCH">
	
</form>
</div>
	<table>
		<tr>
			<td>num</td>
			<td>title</td>
			<td>writer</td>
			<td>contents</td>
			<td>reg_date</td>
			<td>hit</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
				<td>${dto.num}</td>
				<td>
				<c:catch>
				<c:forEach begin="1" end="${dto.depth}">--></c:forEach>
				</c:catch></td>
				<td><a href="NoticeView?num=${dto.num}">${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td>${dto.contents}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			
		</tr>
		
		</c:forEach>
	</table>
	<c:if test="${listInfo.curBlock > 1 }">
	<span class="go" id="${listinfo.startNum-1}">[이전]</span>
<!-- <a href="${board}List?curPage=${listInfo.startNum -1}&search=${listInfo.search}&find=${listInfo.find}">[이전]</a>	
	 -->
 </c:if>
	<c:forEach begin="${listInfo.startNum}" end="${listInfo.lastNum}" var="i">
	<span class="go" id="${i}">${i}</span>
<!-- <a href="${board}List?curPage=${i}&search=${listInfo.search}&find=${listInfo.find}">${i}</a> 	
	 -->
	</c:forEach>
	
	<c:if test="${listInfo.curBlock < listInfo.totalBlock }">
	<span class="go" id="${listinfo.lastNum+1}">[다음]</span>
<!-- <a href="${board}List?curPage=${listInfo.lastNum +1}&search=${listInfo.search}&find=${listInfo.find}">[다음]</a>
	 -->
	</c:if>
	
	<br><br>
	
	<a href="${board}NoticeWrite">write</a>
	
	<div>
		<p>curPage: ${curPage}</p>
		<p>SEARCH: ${search}</p>
		<p>Find: ${find}</p>
	</div>
</body>
</html>