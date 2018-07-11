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

import msyml.kcsj.domain.MyOrderBean;
import msyml.kcsj.service.AdminProductService;

public class AddOrderFrom extends HttpServlet {

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
		String address = request.getParameter("address");
		Gson gson = new Gson();
		MyOrderBean myOrder=gson.fromJson(cid, MyOrderBean.class);
		String otime = "1";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stime = sdf.format(d);
		AdminProductService service = new AdminProductService();
		try {
			for(int i=0;i<myOrder.getData().size();i++){
				service.AddOrderFrom(uid, myOrder.getData().get(i).getId(),
						address, stime, otime,String.valueOf((myOrder.getData().get(i).getCount())));
			}
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
