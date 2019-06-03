<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"></jsp:include>

<table class="table table-striped table-bordered  table-condensed table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Nome</th>
			<th>Usu�rio</th>
			<th>Level</th>
			<th>A��es</th>
			<!--<th>Amount</th>
			<th>Entry</th>
			<th>LicensePlate</th>-->
		</tr>
	</thead>

	<tbody>
		<c:forEach var="u" items="${users }">
			<tr>
				<td>${u.id}</td>
				<td>${u.name}</td>
				<td>${u.userName}</td>
				<td>${u.level}</td>
				<td><a href="${linkTo[UserController].delete(u.id)}" class="btn btn-danger">Deletar</a></td>
			</tr>
		</c:forEach>
	</tbody>

</table>
<jsp:include page="../footer.jsp"></jsp:include>
