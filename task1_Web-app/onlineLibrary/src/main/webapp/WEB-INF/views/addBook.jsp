<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Add Book</title>
  </head>
  <body>
  		<p><a href = "${contextPath}/" >Home</a></p>
		<form action="${contextPath}/addBook" method = "POST" enctype="multipart/form-data">
			<table>
            	<tr>
                	<td>Title :</td>
                	<td><input type='text' name='title' /></td>
            	</tr>
            	<tr>
                	<td>Author :</td>
                	<td><input type='text' name='author' /></td>
            	</tr>
            	<tr>
                	<td>Description :</td>
                	<td><input type='text' name='description' /></td>
            	</tr>
            	<tr>
                	<td>Genre :</td>
                	<td>
                		<select name="genres" >		
                			<c:forEach items="${listGenres}" var="genre">
            					<option value = "${genre.getGenre()}" >${genre.getGenre()}</option>	
    						</c:forEach>
						</select>
					</td>
            	</tr>
            	<tr>
                	<td>File :</td>
                	<td><input type='file' name='file' /></td>
            	</tr>
            	<tr>
                	<td colspan='2'><input name="submit" type="submit" value="Add book" /></td>
            	</tr>
        	</table>
		</form>
  </body>
</html>