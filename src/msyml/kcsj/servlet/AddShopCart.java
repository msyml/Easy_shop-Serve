package msyml.kcsj.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msyml.kcsj.domain.Commodity;
import msyml.kcsj.service.AdminProductService;

public class AddShopCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String cid = request.getParameter("cid");
		String count = request.getParameter("count");
		String shopid;
		String shopname;
		String image;
		String cname;
		String price;
		Commodity commodity = new Commodity();
		boolean type=false;
		AdminProductService service = new AdminProductService();
		try {
			commodity=service.findProductByPid(cid);
			shopid=String.valueOf(commodity.getShop_id());
			image=commodity.getPimage();
			cname=commodity.getPname();
			price=String.valueOf(commodity.getPrice());
			shopname=service.quertShopname(shopid);
			type = service.addShopCart(uid, cid, shopid, shopname, image, cname, count, price);
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
