package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.service.AdminProductService;

public class CheckAccount  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String account = request.getParameter("account");
		boolean type=false;
		AdminProductService service = new AdminProductService();
		try {
			type=service.checkAccount(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(String.valueOf(type));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
