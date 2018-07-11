package msyml.kcsj.service;

import java.sql.SQLException;
import java.util.List;

import msyml.kcsj.domain.Address;
import msyml.kcsj.domain.Commodity;
import msyml.kcsj.domain.Orderfrom;
import msyml.kcsj.domain.ShopCart;
import msyml.kcsj.domain.UserInfor;
import mysml.kcsj.dao.AdminProductDao;

public class AdminProductService {
	// 查询全部商品数据
	public List<Commodity> findAllCommodity() throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findAllCommodity();
	}

	// 模糊查询商品名
	public List<Commodity> findProductByName(String name) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findCommodityByName(name);
	}

	// 添加数据
	public void addProduct(Commodity commodity) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.addCommodity(commodity);
	}

	// 根据id删除商品
	public void delProductByPid(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.delCommodityByPid(pid);
	}

	// 根据id查询商品对象
	public Commodity findProductByPid(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findCommodityByPid(pid);
	}

	// 根据id更新商品
	public void updateProduct(Commodity commodity) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.updateCommodity(commodity);
	}

	// 验证登录
	public UserInfor loginServlet(String account, String password, String time) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.loginServlet(account, password, time);
	}

	// 判断手机号是否存在
	public boolean checkPhone(String phone) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.checkPhone(phone);
	}

	// 判断帐号是否存在
	public boolean checkAccount(String account) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.checkAccount(account);
	}

	// 添加用户
	public boolean addUser(String phone, String account, String password, String time) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.addUser(phone, account, password, time);
	}

	// 修改密码
	public boolean updatePassword(String id, String oldpw, String newpw) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.updatePassword(id, oldpw, newpw);
	}

	// 修改性别
	public boolean updateSex(String id, String oldpw, String newpw) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.updateSex(id, oldpw, newpw);
	}

	// 修改地址
	public void updateAddress(String address, String name, String mobile, String aid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		adminProductDao.updateAddress(address, name, mobile, aid);
	}

	// 修改生日
	public boolean updateBirth(String id, String oldpw, String newpw) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.updateBirth(id, oldpw, newpw);
	}

	// 添加购物车
	public boolean addShopCart(String uid, String cid, String shopid, String shopname, String image, String cname,
			String count, String price) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.addShopCart(uid, cid, shopid, shopname, image, cname, count, price);
	}

	// 根据用户id获取购物车
	public List<ShopCart> QueryShopCart(String uid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		return adminProductDao.QueryShopCart(uid);
	}

	// 根据商品id获取商店id
	public String queryShopid(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.queryShopid(pid);
	}

	// 根据商店id获取商店名
	public String quertShopname(String shopid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.queryShopName(shopid);
	}

	// 根据地址id删除地址
	public void DelAddress(String aid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		adminProductDao.DelAddress(aid);
	}

	// 根据id获取收货地址
	public List<Address> queryAddress(String uid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		return adminProductDao.queryAddress(uid);
	}

	// 添加收货地址
	public void AddAddress(String uid, String name, String address, String mobile, boolean check) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		adminProductDao.AddAddress(uid, name, address, mobile, check);
	}

	// 删除购物车
	public void DeleteShopCart(String uid, String cid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		adminProductDao.DeleteShopCart(uid, cid);
	}

	// 根据id查找订单
	public List<Orderfrom> QueryOrder(String uid) throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		return adminProductDao.QueryOrderFrom(uid);
	}

	// 添加订单
	public void AddOrderFrom(String uid, String cid, String address, String stime, String otime, String count)
			throws SQLException {
		AdminProductDao adminProductDao = new AdminProductDao();
		adminProductDao.AddOrderFrom(uid, cid, address, stime, otime, count);
	}
}
