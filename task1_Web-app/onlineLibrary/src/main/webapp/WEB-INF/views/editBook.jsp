<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page session="false" %>
<html>
  <head>
    <title>Edit Book</title>
  </head>
  <body>
  		<p><a href = "${contextPath}/" >Home</a></p>
		<form action="${contextPath}/editBook/${book.getIdBook()}" method = "POST" enctype="multipart/form-data">
			<table>
            	<tr>
                	<td>Title :</td>
                	<td><input type='text' name='title' value = '${book.getTitle()}' /></td>
            	</tr>
            	<tr>
                	<td>Author :</td>
                	<td><input type='text' name='author' value = '${book.getAuthor()}' /></td>
            	</tr>
            	<tr>
                	<td>Description :</td>
                	<td><input type='text' name='description' value = '${book.getDescription()}' /></td>
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
                	<td colspan='2'><input name="submit" type="submit" value="Edit book" /></td>
            	</tr>
        	</table>
		</form>
  </body>
</html>