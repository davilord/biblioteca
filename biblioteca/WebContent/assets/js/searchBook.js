/**
 * @author fillipquesado
 * 
 * 
 */

$("#isbn").keyup(function() {
	if ($(this).val().length == 13) {
		$.ajax({
			type : 'GET',
			data : {
				isbn : $(this).val()
			},
			url : "service/search",
			dataType : 'text',
			success : function(result) {
				if (result !== null)
					preencheCampos(result);
			},
			error : function() {
				nadaEncontrado();
				
			},
		});
	}
});

function preencheCampos(book) {

	v = $.parseJSON(book);
	for ( var i in v) {

		$("#" + i).attr("value", v[i]);
		if (i !== 'isbn') {
			$("#" + i).prop("disabled", true);
		}
	}

	$("#book_first").addClass("has-success");
	$("#book_last").addClass("has-success");

}

function nadaEncontrado() {

	$("#book_first").removeClass("has-success");
	$("#book_last").removeClass("has-success");

	$("#book_first").addClass("has-error");
	$("#book_last").addClass("has-error");

	var campos = [ "autor", "book_id", "isbn", "editora", "genero" ];
	for (var i = 0; i <= campos.length; i++) {
		
		$("#" + campos[i]).prop("disabled", false);
		$("#" + campos[i]).attr("value", '');

	}
}
