package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.service.AdminProductService;

public class AddUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(d);
		boolean type=false;
		AdminProductService service = new AdminProductService();
		try {
			type = service.addUser(phone, account, password, time);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(String.valueOf(type));;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
