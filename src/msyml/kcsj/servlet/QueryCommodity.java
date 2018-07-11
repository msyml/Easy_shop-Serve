package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import msyml.kcsj.domain.Commodity;
import msyml.kcsj.service.AdminProductService;
import net.sf.json.JSONObject;

public class QueryCommodity extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		Gson gson = new Gson();
		AdminProductService service = new AdminProductService();
		List<Commodity> commodityList = null;
		try {
			commodityList = service.findProductByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		String commo = gson.toJson(commodityList);
		json.put("date", commo);
		response.getWriter().write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}