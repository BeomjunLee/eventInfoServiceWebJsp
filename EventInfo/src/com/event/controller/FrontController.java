package com.event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====FrontController====");
		System.out.println("actionDo() 호출");
		
		request.setCharacterEncoding("UTF-8");
			
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String viewPage = null;  // 어떠한 페이지에 보여줄지 (View)
		Command command = null;   // 어떠한 로직을 수행할지
		
		// 테스트 출력
		System.out.println("uri: " + uri);
		System.out.println("conPath: " + conPath);
		System.out.println("com: " + com);
		System.out.println("=======================");
		
		switch(com) {
		case "/main.do":
			viewPage = "main.jsp";
			break;
		case "/login.do":
			viewPage = "login.jsp";
			break;
		case "/loginOk.do":	
			command = new LoginCommand();
			command.excute(request, response);
			viewPage = "loginOk.jsp";
			break;
		case "/logout.do":
			command = new LogoutCommand();
			command.excute(request, response);
			viewPage = "logout.jsp";
			break;
		case "/signUp.do":
			viewPage = "signUp.jsp";
			break;
		case "/signUpOk.do":
			command = new SignUpCommand();
			command.excute(request, response);
			viewPage = "signUpOk.jsp";
			break;
		case "/idDuplicated.do":
			command = new IdDuplicatedCommand();
			command.excute(request, response);
			viewPage = "idDuplicated.jsp";
			break;
		case "/review.do":
			viewPage = "review.jsp";
			break;
		case "/writeReview.do":
			viewPage = "writeReview.jsp";
			break;
			
		}
		
		// response 를 위해서 
		// 위에서 결정된 viewPage 에 forward 해줌
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
//		RequestDispatcher dispatcher = context.getRequestDispatcher("/hello");
	}
}
