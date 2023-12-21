<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var="path" value="${pageContext.request.requestURI}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>springboot</title>
</head>
<body>
	<h2>☃︎눈사람☃︎</h2>
	<button id="memberall">전체회원조회 •ɞ•</button><br><br>
	<a href="${pathContext.request.contextPath }/board/boardList">✐게시글✎</a>
	<h3>${path }</h3>
	<c:if test="${1!=1 }">
		<h3>if문 성공~!!</h3>
	</c:if>
	<h3>업로드 처리하기</h3>
	<form action="${pageContext.request.contextPath }/fileupload" method="post" enctype="multipart/form-data">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="submit" value="upload">
	</form>
	<h2>٩( *˙0˙*)۶채팅접속</h2>
	<button onclick="openChatting();">채팅하기٩( *˙0˙*)۶</button>
<script>
function openChatting(){
	open("${pathContext.request.contextPath}/chatpage","_blank","width=400, height=500");
}
document.querySelector("#memberall")
.addEventListener("click",e=>{
	fetch("${pageContext.request.contextPath}/members")
	.then(response=>{
		console.log(response);
		if(response.status!=200){
			throw new Error("에러 ¯＼_(ツ)_/¯");
		}
		return response.json();
	}).then(data=>{
		console.log(data);
	}).catch(e=>{
		alert(e);
	});
});
</script>
</body>
</html>