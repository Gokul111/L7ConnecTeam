package com.l7.ratms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.l7.connecteam.controller.UserController;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Cookie[] cookie = request.getCookies();
		String userName = "";
		String password = "";
		if (session != null && request.isRequestedSessionIdValid()) {

			getServletConfig().getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

		} else if (cookie != null && cookie.length != 0) {

			for (Cookie c : cookie) {
				if (c.getName().equals("usercookie")) {
					userName = c.getValue();
				}
				if (c.getName().equals("passwordcookie")) {
					password = c.getValue();
				}
			}

			if (userName != null && password != null && !userName.trim().isEmpty() && !password.trim().isEmpty()) {
				boolean isloginSuccess = true;
				UserController userObj = new UserController();
				UserDto userDataObj = new UserDto();
				userDataObj.setUsername(userName);
				userDataObj.setPassword(password);
				try {
					userDataObj = userObj.userLogin(userDataObj);

				} catch (UIException e) {
					isloginSuccess = false;
					e.printStackTrace();
				} catch (SQLException e) {
					isloginSuccess = false;
					e.printStackTrace();
				}

				session = request.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("user", userDataObj);

				if(!isloginSuccess) {
				getServletConfig().getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

				}
				else {
					getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);

				}
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);

			}
		}

		else {
			getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = "";
		String password = "";
		String rememberMe = "";
		rememberMe = request.getParameter("rememberMe");
		userName = request.getParameter("username");
		password = request.getParameter("password");

		boolean isloginSuccess = true;
		UserController userObj = new UserController();
		UserDto userDataObj = new UserDto();
		userDataObj.setUsername(userName);
		userDataObj.setPassword(password);
		try {
			userDataObj = userObj.userLogin(userDataObj);

		} catch (UIException e) {
			isloginSuccess = false;
			e.printStackTrace();
		} catch (SQLException e) {
			isloginSuccess = false;
			e.printStackTrace();
		}

		if (isloginSuccess && userDataObj.getUsername().equalsIgnoreCase(userName)
				&& userDataObj.getPassword().equals(password)) {
			System.out.println(rememberMe);
			if (rememberMe != null && !rememberMe.trim().equals("")) {
				Cookie cUserName = new Cookie("usercookie", userName.trim());
				Cookie cPassword = new Cookie("passwordcookie", password);
				Cookie cRememberMe = new Cookie("remembermecookie", rememberMe);
				cUserName.setMaxAge(60 * 60 * 24 * 10);
				cPassword.setMaxAge(60 * 60 * 24 * 10);
				cRememberMe.setMaxAge(60 * 24 * 10);
				response.addCookie(cUserName);
				response.addCookie(cPassword);
				response.addCookie(cRememberMe);
			}

			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("user", userDataObj);

			String json = new Gson().toJson("home.jsp");
			response.setContentType("application/json");
			response.getWriter().write(json);

		} else if (!isloginSuccess) {
			response.getWriter().write("error");
		} else {

			getServletConfig().getServletContext().getRequestDispatcher("/homeError.jsp").forward(request, response);
		}

	}

}
