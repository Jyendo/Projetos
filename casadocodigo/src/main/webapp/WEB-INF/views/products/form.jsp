<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
</head>
<body>
	<f:form action="/casadocodigo/produtos" method="post" 
	modelAttribute="product" enctype="multipart/form-data">
		<div>
			<label for="title">Titulo</label> 
			<f:input path="title" type="text" name="title"	id="title" />
			<f:errors path="title"/>
		</div>
		<div>
			<label for="description">Descrição</label>
			<f:textarea path="description" rows="10" cols="20" name="description" id="description"></f:textarea>
			<f:errors path="description"/>
		</div>
		<div>
			<label for="pages">Número de paginas</label> 
			<f:input path="pages" type="text" name="pages" id="pages" />
			<f:errors path="pages"/>
		</div>

		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> 
				<f:input path="prices[${status.index}].value" type="text" name="prices[${status.index}].value"
					id="price_${bookType}" /> <input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		<div>
		<label for="releaseDate">Data de lançamento</label>
		<f:input name="releaseDate" id="releaseDate" path="releaseDate" type="date"/>
		<f:errors path="releaseDate"/>
		</div>
		
		<input type="file" name="summary"/>
		
		<div>
			<input type="submit" value="Enviar">
		</div>
	</f:form>
</body>
</html>