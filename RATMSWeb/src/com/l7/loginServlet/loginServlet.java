package com.l7.loginServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.l7.connecteam.controller.UserController;
import com.l7.connecteam.daoImpl.UserDaoImpl;
import com.l7.connecteam.dto.RoleDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("sdasdsad");
		Cookie[] cookie = request.getCookies();
		String userName = "";
		String password = "";
		if (cookie != null) {
			for (Cookie c : cookie) {
				if (c.getName().equals("usercookie")) {
					userName = c.getValue();
				}
				if (c.getName().equals("passwordcookie")) {
					password = c.getValue();
				}
			}
			if (userName.equals("admin") && password.equals("admin")) {
				getServletConfig().getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = "";
		String password = "";
		String rememberMe = request.getParameter("rememberMe");
		userName = request.getParameter("username");
		password = request.getParameter("password");
		
		boolean isloginSuccess=true;
		UserController userObj = new UserController();
		UserDto userDataObj = new UserDto();
		userDataObj.setUsername(userName);
		userDataObj.setPassword(password);
		try {
			userDataObj = userObj.userLogin(userDataObj);
			
			
		} catch (UIException e) {
			// TODO Auto-generated catch block
			isloginSuccess=false;
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isloginSuccess=false;
			e.printStackTrace();
		}

		if (isloginSuccess && userDataObj.getUsername().equalsIgnoreCase(userName) && userDataObj.getPassword().equals(password)) {
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
			
			
		//	userDaoImpl.getRoleByUser(userDataObj);
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("user", userDataObj);
			System.out.println(userDataObj);
			
			getServletConfig().getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else {
			getServletConfig().getServletContext().getRequestDispatcher("/homeError.jsp").forward(request, response);
		}
//		if (request.getParameter("Logout") != null) {
//			System.out.println("here");
//			Cookie cUserName = new Cookie("usercookie", userName.trim());
//			Cookie cPassword = new Cookie("passwordcookie", password);
//			Cookie cRememberMe = new Cookie("remembermecookie", rememberMe);
//			cUserName.setMaxAge(0);
//			cPassword.setMaxAge(0);
//			cRememberMe.setMaxAge(0);
//			doGet(request, response);
//			// getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp").forward(request,
//			// response);
//		}

	}

}
