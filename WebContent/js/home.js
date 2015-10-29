$(document).ready(function() {
			checkIfLoggedIn(false);
			getMovies();
		});

		//Haal alle films op en toon deze in een grid
		function getMovies() {

			$
					.ajax({
						type : 'GET',
						url : "/Notflix/api/movies",
						dataType : 'json',
						encode : true
					})
					.done(
							function(data) {
								$("#pnlMovies").html("");
								$
										.each(
												data,
												function(i, item) {
													console.log(item.numberIMDB)
													$("#pnlMovies")
															.append(
																	"<div class='col-sm-2 col-md-2'><a href='/Notflix/showmovie.html?movieid="
																			+ item.numberInternal
																			+ "'><div class='thumbnail'>"
																			+ "<img id='"
																			+ item.numberInternal
																			+ "' alt='100%x200'"
																			+ "style='height: 300px; width: 100%; display: block;'"
																			+ "src='data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjQyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDI0MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUwNjZhZGRkNDYgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMnB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTA2NmFkZGQ0NiI+PHJlY3Qgd2lkdGg9IjI0MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI4OS44NTkzNzUiIHk9IjEwNS4xIj4yNDJ4MjAwPC90ZXh0PjwvZz48L2c+PC9zdmc+'"
																			+ "data-holder-rendered='true''> "
																			+ "<div class='caption'>"
																			+ "<h3>"
																			+ item.titel
																			+ "</h3>"
																			+ "</div></div></a></div>");

													$
															.getJSON(
																	"http://www.omdbapi.com/?i="
																			+ item.numberIMDB
																			+ "&plot=short&r=json",
																	function(
																			data) {
																		console
																				.log(data.Poster);
																		$(
																				"#"
																						+ item.numberInternal)
																				.attr(
																						"src",
																						data.Poster);
																	});
												});
							});
		}

		$("#btnSearch")
				.click(
						function(e) {
							e.preventDefault();
							var text = $('#txtTitle').val();
							
							$
									.ajax(
											{
												type : 'GET',
												url : "/Notflix/api/movies/query?title="
														+ text,
												dataType : 'json',
												encode : true
											})
									.done(
											function(data) {
												$("#pnlMovies").html("");
												$
														.each(
																data,
																function(i,
																		item) {
																	$(
																			"#pnlMovies")
																			.append(
																					"<div class='col-sm-2 col-md-2'><a href='/Notflix/showmovie.html?movieid="
																							+ item.numberInternal
																							+ "'><div class='thumbnail'>"
																							+ "<img id='"
																							+ item.numberInternal
																							+ "' alt='100%x200'"
																							+ "style='height: 300px; width: 100%; display: block;'"
																							+ "src='data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjQyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDI0MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUwNjZhZGRkNDYgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMnB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTA2NmFkZGQ0NiI+PHJlY3Qgd2lkdGg9IjI0MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI4OS44NTkzNzUiIHk9IjEwNS4xIj4yNDJ4MjAwPC90ZXh0PjwvZz48L2c+PC9zdmc+'"
																							+ "data-holder-rendered='true''> "
																							+ "<div class='caption'>"
																							+ "<h3>"
																							+ item.titel
																							+ "</h3>"
																							+ "</div></div></a></div>");

																	$
																			.getJSON(
																					"http://www.omdbapi.com/?i="
																							+ item.numberIMDB
																							+ "&plot=short&r=json",
																					function(
																							data) {
																						$(
																								"#"
																										+ item.numberInternal)
																								.attr(
																										"src",
																										data.Poster);
																					});
																});
											});

						});