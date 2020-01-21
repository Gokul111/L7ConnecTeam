function clearCookies() {
	 document.cookie = "usercookie=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ReporGenerator_Dynamic";
	 document.cookie = "password=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ReporGenerator_Dynamic";
	 document.cookie ="rememberMe4=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/ReporGenerator_Dynamic";
     location.replace("Login.jsp");
 
  }