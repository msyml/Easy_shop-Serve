package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import msyml.kcsj.service.AdminProductService;
import msyml.kcsj.utils.RegexUtils;

public class CheckPhone  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String phone = request.getParameter("phone");
		String type="null";
		if(RegexUtils.checkMobile(phone)){
			AdminProductService service = new AdminProductService();
			try {
				type=String.valueOf(service.checkPhone(phone));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			type="null";
		}
		
		response.getWriter().write(type);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
