

$(document).ready(function(){
	$("#logout").click(function(){
		window.location="InvalidateSession";
		document.cookie = "usercookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ratms;";
		document.cookie = "passwordcookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ratms;";
		document.cookie = "remembermecookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ratms;";	
	});
});

