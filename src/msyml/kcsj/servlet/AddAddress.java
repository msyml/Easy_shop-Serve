package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.service.AdminProductService;

public class AddAddress extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		boolean check = false;
		AdminProductService service = new AdminProductService();
		try {
			service.AddAddress(uid, name, address, mobile, check);
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write("true");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
