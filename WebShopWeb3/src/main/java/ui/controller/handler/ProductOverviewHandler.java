package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.ShopService;

public class ProductOverviewHandler extends RequestHandler {

	private ShopService service;
	
	public ProductOverviewHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ShopService getService() {
		return service;
	}


	public void setService(ShopService service) {
		this.service = service;
	}

	@Override
	public void HandleRequest(HttpSession session, Cookie cookie, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(session.getAttribute("role") != null && (session.getAttribute("role").equals("Admin") || session.getAttribute("role").equals("Customer"))){
			request.setAttribute("service", service.getProducts());
			String destination = "products.jsp";
			
			request.setAttribute("actionn", request.getParameter("action"));
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher(destination).forward(request, response);
		}else{
			request.setAttribute("error", "You're not authorized to do this");
			response.addCookie(cookie);
			request.setAttribute("kleur", cookie.getValue());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
