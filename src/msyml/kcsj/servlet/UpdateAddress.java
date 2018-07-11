package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.service.AdminProductService;

public class UpdateAddress extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String aid = request.getParameter("aid");
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String address=request.getParameter("address");
		AdminProductService service = new AdminProductService();
		try {
				service.updateAddress(address, name, mobile, aid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
