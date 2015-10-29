$(document).ready(function() {

	// Als er geen token is redirect
	if (checkIfLoggedIn(true)) {
		var movieId = getParameterByName('movieid');
		getMovieById(movieId);
	}

});

// Voeg een nieuwe rating toe
$('#addRatingForm')
		.submit(
				function(event) {

					// stop the form from submitting the normal way and
					// refreshing the page
					event.preventDefault();
					var token = localStorage.getItem('token');
					var userid = localStorage.getItem('userid');
					var token = localStorage.getItem('token');
					var formData = {
						'stars' : $('#txtRating').val(),
						'movieId' : getParameterByName('movieid'),
						'userId' : userid,

					};

					$
							.ajax(
									{
										type : 'POST',
										url : "/Notflix/api/users/" + userid
												+ "/ratings",
										data : JSON.stringify(formData),
										dataType : 'json',
										headers : {
											'token' : token
										},
										contentType : "application/json; charset=utf-8",
										encode : true,
										error : function(jqXHR, textStatus,
												errorThrown) {
											// get the status code
											if (jqXHR.status == 400) {
												$("#messageDiv")
														.html(
																"<div class='alert alert-dismissible alert-danger'>"
																		+ "<button type='button' class='close' data-dismiss='alert'>"
																		+ "x"
																		+ "</button>"
																		+ jqXHR.responseText
																		+ "</div>");
											}

										},
									})
							.done(
									function(data) {
										$("#addRatingForm").addClass("hide");
										$("#messageDiv")
												.html(
														"<div class='alert alert-dismissible alert-success'>"
																+ "<button type='button' class='close' data-dismiss='alert'>"
																+ "x"
																+ "</button>"
																+ "Rating toegevoegd!"
																+ "</div>");
									});

				});

// Haal een film op obv ID
function getMovieById(id) {

	var token = localStorage.getItem('token');
	var userid = localStorage.getItem('userid');

	$.ajax({
		type : 'GET',
		url : "/Notflix/api/movies/" + id,
		dataType : 'json',
		headers : {
			'token' : token
		},
		encode : true
	}).done(
			function(data) {

				$.getJSON("http://www.omdbapi.com/?i=" + data.numberIMDB
						+ "&plot=short&r=json", function(data) {

					$("#imgMovie").attr("src", data.Poster);
				});

				$("#titleMovie").html(data.titel);
				$("#moviedesc").html(data.description);
				$("#movieDatePublished").html(data.datePublished);
				$("#numberIMDB").html(data.numberIMDB);
				$("#lengthFilm").html(data.lengthFilm);
				$("#director").html(data.director);

			});

	$.ajax({
		type : 'GET',
		url : "/Notflix/api/users/" + userid + "/ratings",
		headers : {
			'token' : token
		},
		dataType : 'json',
		encode : true
	}).done(function(data) {

		// Als de film al voorkomt haal form toevoegen rating weg
		$.each(data, function(i, item) {
			if (item.movieId == id) {
				$("#addRatingForm").addClass("hide");
				$("#tdRating").html(item.stars);
			}
		});

	});

}