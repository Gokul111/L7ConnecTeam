
$(document)
		.ready(
				function() {
					$("#getin").click(action);

					function action() {
						uname = document.getElementById('username').value;
						pword = document.getElementById('password').value;
						rememberBox = document.querySelector('#rememberMe').checked;
						if(rememberBox === true){
							remember = 'rememberMe';
							
						}
						else{
							remember = '';
						}
						if(uname != null && pword != null && uname.trim().length > 0 &&  pword.trim().length > 0){
					
						$.ajax({
									type : 'POST',
									url : 'loginServlet',
									data: {username:uname,password:pword,rememberMe:remember},
									dataType : 'json',
									success : function(json) {
										
										var successUrl = "home.jsp"; // might be a good idea to return this URL in the successful AJAX call
										if(successUrl === json){
										    window.location.href = successUrl;
										}
										else{
											$("#error").text('invalid username or password');

										}
									},
									error : function(e) {
										$("#error").text('invalid username or password');
									}
								})
						}
						else{
							$("#error").text('username and  password cannot be empty');

						}
					}

				});
