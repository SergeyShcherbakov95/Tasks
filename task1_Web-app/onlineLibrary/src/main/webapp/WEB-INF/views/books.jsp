<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Books</title>
  </head>
  <body>
  		<p><a href = "${contextPath}/" >Home</a></p>
  		<sec:authorize access="isAnonymous()">
			<p><a href = "${contextPath}/login" >Sign In</a></p>
    	    <p><a href = "${contextPath}/register" >Sign Up</a></p> 
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN')">
			<p><a href = "${contextPath}/addBookPage" >Add book</a></p> 
		</sec:authorize> 
		<sec:authorize access="hasRole('USER')">
			<p><a href = "${contextPath}/userInfo" >My info</a></p> 
			<p><a href="${contextPath}/<c:url value='j_spring_security_logout' />" >Logout</a></p>
		</sec:authorize> 
		<form action="${contextPath}/" method = "GET" >
			<input type='text' name='text' />
            <select name="genres" >
            	<option value = "all" >All</option>	
                <c:forEach items="${listGenres}" var="genre">
            		<option value = "${genre.getGenre()}" >${genre.getGenre()}</option>	
    			</c:forEach>
			</select>
           	<input type="submit" value="Search" />
		</form>
		<h1>Books</h1>
		<c:if test = "${not empty books}">
    		<c:forEach items="${books}" var="book">
            	<p><a href = "${contextPath}/book/${book.getIdBook()}" >${book.getTitle()} - ${book.getAuthor()}</a></p> 
    			<hr width = "70%" align = "left" />
    		</c:forEach>
    	</c:if>   
  </body>
</html>