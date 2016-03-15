<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>${book.getTitle() }</title>
  </head>
  	<body>
  		<p><a href = "${contextPath}/" >Home</a></p>
  		<sec:authorize access="hasRole('ADMIN')">
			<p><a href = "${contextPath}/addBookPage" >Add book</a></p> 
		</sec:authorize> 
  		<sec:authorize access="hasRole('USER')">
			<p><a href = "${contextPath}/userInfo" >My info</a></p> 
			<p><a href="${contextPath}/<c:url value='j_spring_security_logout' />" >Logout</a></p>
		</sec:authorize> 
		<hr width = "70%" align = "left" />
  		<p>Title: ${book.getTitle()}</p>
  		<p>Author: ${book.getAuthor()}</p>
  		<p>Description: ${book.getDescription()}</p>
  		<p>Genre: ${book.getBookGenre().getGenre() }</p>
  		<hr width = "70%" align = "left" />
  		<sec:authorize access="hasRole('USER')">
  			<p><a href = "${contextPath}/${book.getPathToBook()}" >Download</a></p>
  			<p><a href = "${contextPath}/addToFavourite/${book.getIdBook()}" >Add to favourite</a></p>
  		</sec:authorize> 
  		<sec:authorize access="hasRole('ADMIN')">
  			<p><a href = "${contextPath}/delete/${book.getIdBook()}" >Delete</a></p>
  			<p><a href = "${contextPath}/editBook/${book.getIdBook()}" >Edit</a></p>
  		</sec:authorize> 
	</body>
</html>