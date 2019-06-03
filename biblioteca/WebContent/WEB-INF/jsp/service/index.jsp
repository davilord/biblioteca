<!-- Button trigger modal -->
<button type="button" class="btn btn-warning btn-lg " data-toggle="modal"
	data-target="#myModal">Emprestar Livro</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Registrar aqui</h4>
			</div>
			<form action="${linkTo[ServiceController].newService}" method="post"
				role="form" class="form-horizontal">
				<div class="modal-body">
					<!-- form vehicle -->
					<div class="form-group" id="book_first">
						<div class="col-lg-6 col-md-6 ">
							<label for="inputuserName" class="col-sm-0 control-label">Inserir ISBN</label> <input type="text"
								name="book.isbn" placeholder="ISBN" required
								class="form-control" id="isbn" maxlength="13" autofocus="autofocus"/>
						</div>
					</div>
					<div class="form-group" id="book_last">
						<div class="col-md-4">
							<label for="inputuserName" class="col-sm-2 control-label">Autor</label>
							<input type="text" name="book.autor" placeholder="Autor"
								id="autor" required class="form-control" />
						</div>
						<div class="col-md-4">
							<label for="inputuserName" class="col-sm-2 control-label">Editora</label>
							<input type="text" name="book.editora" id="editora"
								placeholder="Editora" required class="form-control" />
						</div>
						<div class="col-md-4">
							<label for="inputuserName" class="col-sm-2 control-label">Genero</label>
							<input type="text" name="book.genero" id="genero"
								placeholder="Genero" required class="form-control" />
						</div>
						<input type="hidden" id="book_id" value="NULL">
					</div>
					<!-- end vehicle -->

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					<input type="submit" value="cadastrar" class="btn btn-success pull-right">
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/searchBook.js"></script>