package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import msyml.kcsj.domain.UserInfor;
import msyml.kcsj.service.AdminProductService;
import net.sf.json.JSONObject;

public class LoginServlet extends HttpServlet {

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
		Date d = new Date();
		Gson gson=new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(d);
		UserInfor userInfor = new UserInfor();
		AdminProductService service = new AdminProductService();
		try {
			userInfor = service.loginServlet(account, password, time);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		String map=gson.toJson(userInfor);
		json.put("status", 200);
		json.put("msg", "null");
		json.put("data", map);
		response.getWriter().write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}