<%@include file="../header.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-7 col-md-offset-2 well">
		<form class="form-horizontal" role="form"
			action="${linkTo[UserController].add }" method="post">
			<div class="form-group">
				<div class="col-md-6">
					<label for="inputuserName" class="col-sm-2 control-label">Usu�rio</label>
					<input type="text" class="form-control" id="inputuserName"
						name="user.userName" placeholder="Usu�rio" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="inputName" class="col-sm-2 control-label">Nome</label>
					<input type="text" class="form-control" id="inputName"
						name="user.name" placeholder="Nome" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="inputPassword3" class="col-sm-2 control-label">Senha</label>
					<input type="password" class="form-control" id="inputPassword1"
						name="user.password" placeholder="Senha" required>
				</div>
				<div class="col-md-6">
					<label for="inputPassword3" class="col-sm-6 control-label">Confirmar Senha</label> <input type="password" class="form-control"
						id="inputPassword2" placeholder="Senha" required>
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-6">
					<c:choose>
						<c:when test="${user.level == 'MANAGER'}">
							<select class="form-control" name="user.level">
								<option>Select type</option>
								<c:forEach items="${levels}" var="l">
									<option value="${l}">${l.level}</option>
								</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="user.level" value="EMPLOYEE">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-2 col-sm-offset-10">
					<button type="submit" id="submit"
						class="btn btn-default btn-lg btn-block">Salvar</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#inputPassword2").prop("disabled", true);

		$("#inputPassword1").keyup(function() {

			$("#inputPassword2").prop("disabled", false);

		});

		$("#inputPassword2").blur(function() {
			if ($(this).val().length != 0) {
				if ($(this).val() !== $("#inputPassword1").val()) {
					alert("Senhas inconpativeis.");

				}
			}
		});
	});
</script>


<%@include file="../footer.jsp"%>