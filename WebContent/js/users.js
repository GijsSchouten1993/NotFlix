$(document).ready(function() {
	if (checkIfLoggedIn(true)) {
		getUsers();
	}
});

// Haal alle gebruikers op en toon deze in een grid
function getUsers() {
	var token = localStorage.getItem('token')
	$
			.ajax({
				type : 'GET',
				url : "/Notflix/api/users",
				dataType : 'json',
				headers : {
					'token' : token
				},
				encode : true
			})

			.done(
					function(data) {
						console.log(data);
						$
								.each(
										data,
										function(i, item) {
											console.log(item);
											$("#pnlUsers")
													.append(
															"<div class='col-sm-2 col-md-2'><a href='/Notflix/userpage.html?userid="
																	+ item.userId
																	+ "'><div class='thumbnail'>"
																	+ "<img id='"
																	+ item.userId
																	+ "' alt='100%x200'"
																	+ "style='height: 300px; width: 100%; display: block;'"
																	+ "src='http://www.vacul.org/extension/site/design/site//images/anonymous-user.png'"
																	+ "data-holder-rendered='true''> "
																	+ "<div class='caption'>"
																	+ "<h3>"
																	+ item.nickname
																	+ "</h3>"
																	// + "<p>"
																	// + "<a
																	// href='#'
																	// class='btn
																	// btn-primary'
																	// role='button'>Bekijk</a>"
																	+ "</div></div></a></div>");

										});
					});
}