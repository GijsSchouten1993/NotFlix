
//Kijk of de gebruiker is ingelogd
function checkIfLoggedIn(redirect) {
	var token = localStorage.getItem('token');
	if ((typeof (token) === 'undefined') || (token === null)) {
		if (redirect) {
			window.location.replace("/Notflix");
		}
		return false;
	}

	if ((typeof (token) !== 'undefined') && (token !== null)) {
		$("#loginForm").addClass("hide");
		$("#btnRatings").removeClass("hide");
		$("#btnLogOut").removeClass("hide");
		$("#btnUsers").removeClass("hide");
		$("#btnRegister").addClass("hide");
		return true;
	} else {
		$("#btnLogOut").addClass("hide");
		$("#loginForm").removeClass("hide");
		$("#btnRatings").addClass("hide");
		$("#btnUsers").addClass("hide");
		$("#btnRegister").removeClass("hide");
		return false;
	}
}

//Log de gebuiker uit
function logout() {
	localStorage.removeItem("token");
	localStorage.removeItem("userid");
	checkIfLoggedIn(true);
	window.location.replace("/Notflix");
}

function register() {
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
		url : "/Notflix/api/authenticate",
		data : JSON.stringify(formData),
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		encode : true
	}).done(function(data) {
		
		// Sla data op in localstorage
		localStorage.setItem("token", data.token);
		localStorage.setItem("userid", data.userId);
		checkIfLoggedIn(false);
	});

});


function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}
