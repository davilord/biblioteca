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

	$("#vehicle_first").addClass("has-success");
	$("#vehicle_last").addClass("has-success");

}

function nadaEncontrado() {

	$("#vehicle_first").removeClass("has-success");
	$("#vehicle_last").removeClass("has-success");

	$("#vehicle_first").addClass("has-error");
	$("#vehicle_last").addClass("has-error");

	var campos = [ "model", "book_id", "isbn", "mark", "color" ];
	for (var i = 0; i <= campos.length; i++) {
		
		$("#" + campos[i]).prop("disabled", false);
		$("#" + campos[i]).attr("value", '');

	}
}
