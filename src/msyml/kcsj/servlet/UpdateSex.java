package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.service.AdminProductService;
import net.sf.json.JSONObject;

public class UpdateSex extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int cookie=Integer.valueOf(request.getHeader("cookie"));
		String id = request.getParameter("id");
		String oldsex = request.getParameter("oldsex");
		String newsex = request.getParameter("newsex");
		JSONObject json = new JSONObject();
		boolean type=false;
		AdminProductService service = new AdminProductService();
		try {
			if(cookie==200){
				type = service.updateSex(id, oldsex, newsex);
				json.put("msg", type);
			}else{
				json.put("msg", "登录状态失效");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(json.toString());;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
