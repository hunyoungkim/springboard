<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트 페이지</title>
</head>
<body>
<div id="infoArea">
<section class="search"> 
<form name="search" action="./search" method="post"> 
<fieldset> 
<legend> 검색 </legend> 
<label for="keyword"></label> 
<select name="keyfield" class="b_search">
<%--해당 항목을 기본 선택으로 지정하여 검색한다.--%>
<option value="all" selected="selected">전체 검색</option> 
<option value="subject"<c:if test="${'subject' == keyfield}">selected="selected"</c:if>> 제목 </option> 
<option value="m_id"<c:if test="${'m_id' == keyfield}">selected="selected"</c:if>> 글쓴이 </option> 
<option value="content"<c:if test="${'content' == keyfield}">selected="selected"</c:if>> 내용 </option> 
</select> 
<input type="search" id="keyword" name="keyword" required="required" placeholder= "검색어 입력"> 
<button type="submit"> 검색 </button> 
</fieldset> 
</form> 
</section> 
</div>

	<!-- <a href="http://localhost:8088/b/Insert">게시글 작성</a> -->
<p class="allPost">
<%--전체 글의 개수를 호출한다.--%>
전체 글: &nbsp; <strong><c:out value="${listcount}"/></strong>개 </p>
	<table >
		<caption>게시판 리스트 화면</caption>
		<%--게시글이 존재할 조건을 지정한다.--%>

		<tr>
			<th scope="col" class="bbsNumber" width="150">글번호</th>
			<th scope="col" class="bbsTitle" width="150">작성자</th>
			<th scope="col" class="bbsAuthor" width="500">제목</th>
			<th scope="col" class="bbsAuthor" width="150">조회수</th>
			<th scope="col" class="bbsAuthor" width="150">첨부파일</th>
			<th scope="col" class="bbsAuthor" width="150">작성일</th>
		</tr>

		<%--해당 페이지에 저장된 글을 호출한다. --%>
		<c:forEach var="board" items="${list}">
			<tbody>
				<tr>
					<%--글 번호를 표시한다.--%>
					<td style="text-align:center"><c:out value="${board.num}" /></td>
					


					<td style="text-align:center"><c:out value="${board.m_id}" /></td>
					<td>
					<%--답변 글을 표시한다.--%>
					<c:if test="${!empty board.answer_lev}">
					<c:forEach var="j" begin="0" end="${board.answer_lev*2}" step="1">&nbsp;
					</c:forEach> </c:if>
					<a href="./select?num=<c:out value="${board.num}" />">
							<c:out value="${board.subject}" />
					</a></td>
					<td style="text-align:center"><c:out value="${board.read_count}" /></td>
					<c:choose>
					<c:when test="${!empty board.attached_file}"><td style="text-align:center">O</td></c:when>
					<c:otherwise><td> </td></c:otherwise>
					</c:choose>
					<td style="text-align:center"><c:out value="${board.write_date}" /></td>

				</tr>

			</tbody>
		</c:forEach>

	</table>
	<c:choose> 
	<c:when test="${page <= 1}"> [이전]&nbsp; </c:when> 
	<c:otherwise> <a href="./listAll?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;</c:otherwise> 
	</c:choose>
	 
	<c:forEach var="start" begin="${startpage}" end="${endpage}" step="1"> 
	<c:choose> 
	<c:when test="${start eq page}"> [<c:out value="${start}"/>] </c:when> 
	<c:otherwise> <a href="./listAll?page=<c:out value="${start}" />">[<c:out value= "${start}" />]</a>&nbsp;</c:otherwise> 
	</c:choose> 
	</c:forEach>
	 
	<c:choose> <c:when test="${page >= maxpage}">[다음] </c:when> 
	<c:otherwise> <a href="./listAll?page=<c:out value="${page+1}" />">[다음]</a> </c:otherwise> 
	</c:choose>
	<br/>
	<input type="button" onclick="location.href='http://localhost:8088/b/Insert'" value="글 쓰기" />


</body>
</html>