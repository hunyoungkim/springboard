<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시 글 상세보기</title>
	<script type="text/javascript">
function del(num) {
if (confirm("정말 삭제하시겠습니까??") == true){    //확인
	location.href="./delete?num="+num
}else{   //취소
    return false;
}
}
</script>

</head>
<body>

 <p>
글 번호<br/>
<c:out value="${view.num}"/>
</p>
<p>
작성자<br/>
<c:out value="${view.m_id}"/>
</p>
<p>
글 제목<br/>
<c:out value="${view.subject}"/>
</p>
<p>
글 내용<br/>
<textarea rows="15" cols="74" disabled><c:out value="${view.content}"/></textarea>
</p>

<c:choose>          
<c:when test="${!empty view.attached_file}">      
<p>        <label for="attached_file">파일 첨부</label><br/> 
<c:out value="${view.attached_file}"/>&nbsp;&nbsp;&nbsp; 
<%-- <a href="./down/<c:out value='${view.attached_file}'/>"> 파일 다운 </a> --%>
<a href="./down?attached_file=${view.attached_file}"> 파일 다운 </a>
<%--hidden 값으로 기존 파일의 이름인 attached_file를 old_file로 설정한다.--%> 
<%-- <input type="hidden" name="old_file" value="<c:out value='${view.attached_file}'/>"/> &nbsp;&nbsp;&nbsp;   --%>    
</p>          
</c:when>          
<c:otherwise>
 <p>        
 <label for="old_file">파일 첨부</label>        <br/> 첨부 파일이 없습니다.      </p>          
 </c:otherwise>        
 </c:choose>     
 
<div class="input_group"> 
<br> 
<input type="button" onclick="location.href='http://localhost:8088/b/listAll'"  value = "리스트"/> 
<a href="./update?num=${view.num}"><button type="button" > 수정 </button> </a>
   <a ><button type="button" onclick="del(${view.num})"> 삭제 </button> </a>
    <a href="./reply?num=<c:out value="${view.num}"/>">          
 <button type="button" class="btnOk"> 답변 </button>        </a></div> 
</body>
</html>