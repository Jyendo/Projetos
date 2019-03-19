<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" href="<c:url value='/resources/css/index.css' />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="testCss">
	<label>${sucesso}</label>
	<table border="1">
		<tr>
			<td>Titulo</td>
			<td>Valores</td>
			<td>Imagem</td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="/casadocodigo/produtos/show/${product.id}" >${product.title} </a></td>
				<td>
				<c:forEach items="${product.prices}" var="price">
					[${price.value} - ${price.bookType}]
				</c:forEach>
				</td>
				<td>
				<img width="100px" height="120px" src="${product.summaryPath}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>