<jsp:include page="../header.jsp"></jsp:include>
<div id="form">
	<div class="row">
		<div class="col-md-5 col-md-offset-3">
			<h2 class="">DIGITE SEU LOGIN E SENHA</h2>
		</div>
	</div>
	<div class="row">
		<div style="background-color: #d6f5d6" class="col-md-5 col-md-offset-3 well">
			<form class="form-signin" role="form"
				action="${linkTo[LoginController].loginUser}" method="post">
				<div class="form-group">
					<label for="inputuserName" class="col-sm-2 control-label">USUÁRIO</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputuserName"
							name="user.userName" placeholder="Usuário">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">SENHA</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3"
							name="user.password" placeholder="Senha">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button style="background-color:#33cc33; color:white"type="submit" class="btn btn-default btn-lg btn-block">ENTRAR</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>

