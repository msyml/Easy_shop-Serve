package msyml.kcsj.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import msyml.kcsj.domain.Commodity;
import msyml.kcsj.service.AdminProductService;



public class UpdateCommodity extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		//1、获取数据
		Map<String, String[]> properties = request.getParameterMap();
		//2、封装数据
		Commodity commodity = new Commodity();
		try {
			BeanUtils.populate(commodity, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		commodity.setPname(request.getParameter("name"));
		commodity.setPrice(Double.valueOf(request.getParameter("price")));
		commodity.setPimage(request.getParameter("image"));
		commodity.setPdescripel(request.getParameter("descripel"));;
		commodity.setSale_number(Integer.valueOf(request.getParameter("sale_number")));
		commodity.setShop_id(Integer.valueOf(request.getParameter("shop_id")));
		commodity.setCategory_id(Integer.valueOf(request.getParameter("category_id")));
		commodity.setDate(request.getParameter("date"));
		commodity.setInventory(Double.valueOf(request.getParameter("inventory")));
		commodity.setPid(Integer.valueOf(request.getParameter("id")));

		//3、传递数据给service层
		AdminProductService service = new AdminProductService();
		try {
			service.updateProduct(commodity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
