<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>

<form action="./Insert" method="post" enctype="multipart/form-data">
<div>
<input type="text" id="m_id" name="m_id" required placeholder="아이디를 입력하세요">
</div>
<div>
<input type="text" id="subject" name="subject" required placeholder="제목">
</div>
<div>
<textarea id="content" name="content" cols="74" rows="15" required placeholder="내용">
</textarea>
</div>
<div>
</div>
<input type="file" name="filename" id="filename">
<br/>
<input type="submit" value="글등록"> 
<input type="button" onclick="location.href='http://localhost:8088/b/listAll'"  value = "리스트"/> 
</form>

</body>
</html>