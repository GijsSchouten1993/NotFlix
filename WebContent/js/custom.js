function loginProcess() {
	var token = localStorage.getItem('token');
	if ((typeof (token) !== 'undefined') && (token !== null)) {
		$("#loginForm").addClass("hide");
		$("#btnRatings").removeClass("hide");
		$("#btnLogOut").removeClass("hide");
		$("#btnUsers").removeClass("hide");
		$("#btnRegister").addClass("hide");
		$("#btnMovieWithRatings").removeClass("hide");
		

	} else {
		$("#btnLogOut").addClass("hide");
		$("#loginForm").removeClass("hide");
		$("#btnRatings").addClass("hide");
		$("#btnUsers").addClass("hide");
		$("#btnRegister").removeClass("hide");
		$("#btnMovieWithRatings").addClass("hide");
	}
}

function checkIfLoggedIn()
{
	var token = localStorage.getItem('token');
	if ((typeof (token) === 'undefined') || (token === null)) {
		window.location.replace("/Notflix");
	}
}

function logout() {
	localStorage.removeItem("token");
	loginProcess();
}

function register()
{
	window.location.replace("/Notflix/register.html");
}

// Inloggen en token ophalen en opslaan in Localstorage
$('#loginForm').submit(function(event) {
	// stop the form from submitting the normal way and refreshing the page
	event.preventDefault();

	var formData = {
		'nickname' : $('#nickname').val(),
		'password' : $('#password').val(),
	};

	$.ajax({
		type : 'POST',
		url : "http://localhost:8080/Notflix/api/authenticate",
		data : JSON.stringify(formData),
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		encode : true
	})
	.done(function(data) {
		console.log(data);
		console.log(data.token);
		// Sla data op in localstorage
		localStorage.setItem("token", data.token);
		loginProcess();
	});

});

function removeRating(userId, ratingId) {
//	$.ajax({
//		type : 'DELETE',
//		url : "http://localhost:8080/Notflix/api/users/" + userId + "/ratings/" + ratingId,
//		succes : function(result) {
//			
//		}
//	});
	
	console.log(userId + " " + ratingId);

}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

