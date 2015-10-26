function loginProcess() {
	var token = localStorage.getItem('token');
	if ((typeof (token) !== 'undefined') && (token !== null)) {
		$("#loginForm").addClass("hide");
		$("#btnLogOut").removeClass("hide");

	} else {
		$("#btnLogOut").addClass("hide");
		$("#loginForm").removeClass("hide");
	}
}

function logout() {
	localStorage.removeItem("token");
	loginProcess();
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
