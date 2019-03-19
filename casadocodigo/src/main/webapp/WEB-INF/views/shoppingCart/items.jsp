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
	tbody>
	<c:forEach items="${shoppingCart.list}" var="item">
		<tr>
			<td class="cart-img-col"><img src="" alt="${item.product.title}" />
			</td>
			<td class="item-title">${item.product.title} - ${item.bookType}
			</td>
			<td class="numeric-cell">${item.price}</td>
			<td class="quantity-input-cell"><input type="number" min="0"
				readonly="readonly" value="${shoppingCart.getQuantity(item)}">
			</td>
			<td class="numeric-cell">${shoppingCart.getTotal(item)}</td>
		</tr>
	</c:forEach>
	</tbody>
	<tfoot>
		tr>
		<td colspan="2">
			<form action="/casadocodigo/payment/checkout" method="post">
				<input type="submit" class="checkout" name="checkout"
					value="Finalizar compra " id="checkout" />
			</form>
		</td>
		<td class="numeric-cell">${shoppingCart.total}</td>
		<td></td>
		</tr>
	</tfoot>
</body>
</html>