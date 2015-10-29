$(document).ready(function() {

	// Als er geen token is redirect
	if (checkIfLoggedIn(true)) {
		var userId = getParameterByName('userid');
		getUserById(userId);
	}

});

// Haal een gebruiker op obv ID
function getUserById(id) {

	var token = localStorage.getItem('token');
	$.ajax({
		type : 'GET',
		url : "/Notflix/api/users/" + id,
		headers : {
			'token' : token
		},
		encode : true,
		dataType : "json"
	}).done(
			function(data) {
				console.log(data);
				console.log(data.nickname);
				$("#nicknameUser").html(data.nickname);
				$("#userInfo").html(
						data.firstName + " " + data.preposition + " "
								+ data.lastName);
			});

}