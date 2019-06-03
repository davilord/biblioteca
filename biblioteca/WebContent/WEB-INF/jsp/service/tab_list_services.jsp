<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table
	class="table table-striped table-bordered  table-condensed table-hover">
	<thead>
		<tr>
			<th>Livro</th>
			<th>ISBN</th>
			<th>Entrada</th>
			<th>Saída</th>
			<!--  <th>Ficou</th>-->
			<th>Preço</th>
			<th>Ações</th>
		</tr>
	</thead>

	<tbody>

		<c:forEach items="${services}" var="s">
			<c:set var="checkout" value=""></c:set>
			<c:set var="name_button" value="devolver"></c:set>
			<c:set var="checkout_class" value="success"></c:set>
			<c:if test="${s.stay ne null }">
				<c:set var="checkout" value="DISABLED"></c:set>
				<c:set var="checkout_class" value="danger"></c:set>
				<c:set var="name_button" value="entregue"></c:set>
			</c:if>
			<tr>
				<td>${s.book.model}</td>
				<td>${s.book.isbn}</td>
				<td><fmt:formatDate type="both" value="${s.dateTimeEntry.time}" /></td>
				<td><fmt:formatDate type="both" value="${s.dateTimeOut.time}" /></td>
				<!--  <td>${s.stay}</td>-->
				<td><c:choose>
						<c:when test="${s.amount eq 0 }">
									free
								</c:when>
						<c:otherwise>
							<fmt:formatNumber type="number" minFractionDigits="2"
								value="${s.amount}" />
						</c:otherwise>
					</c:choose></td>
				<td><a href="${linkTo[ServiceController].checkout(s.id)}"
					class="btn btn-${checkout_class}" ${checkout}>${name_button}</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>