$(document).ready(function() {

});

$('#registerForm')
		.submit(
				function(event) {
					// stop the form from submitting the normal way and
					// refreshing the page
					event.preventDefault();

					var formData = {
						'nickname' : $('#inputNickname').val(),
						'password' : $('#inputPassword').val(),
						'lastName' : $('#inputLastname').val(),
						'firstName' : $('#inputFirstname').val(),
					};

					$
							.ajax(
									{
										type : 'POST',
										url : "/Notflix/api/users",
										data : JSON.stringify(formData),
										dataType : 'json',
										contentType : "application/json; charset=utf-8",
										encode : true,
										error : function(jqXHR, textStatus,
												errorThrown) {
											// get the status code
											if (jqXHR.status == 400) {
												console.log(jqXHR);
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
									}).done(function(data) {
										$("#messageDiv")
										.html(
												"<div class='alert alert-dismissible alert-success'>"
														+ "<button type='button' class='close' data-dismiss='alert'>"
														+ "x"
														+ "</button>"
														+ "Account aan gemaakt!"
														+ "</div>");
							});
				});