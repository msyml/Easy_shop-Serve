package mysml.kcsj.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import msyml.kcsj.domain.Address;
import msyml.kcsj.domain.Commodity;
import msyml.kcsj.domain.Orderfrom;
import msyml.kcsj.domain.Shop;
import msyml.kcsj.domain.ShopCart;
import msyml.kcsj.domain.UserInfor;
import msyml.kcsj.utils.DataSourceUtils;

public class AdminProductDao {
	// 查询全部的商品信息
	public List<Commodity> findAllCommodity() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from commodity";
		List<Commodity> commodityList = runner.query(sql, new BeanListHandler<Commodity>(Commodity.class));
		return commodityList;
	}

	// 模糊查询商品名
	public List<Commodity> findCommodityByName(String name) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from commodity where pname like \"%\"?\"%\"";
		List<Commodity> commodityList = runner.query(sql, new BeanListHandler<Commodity>(Commodity.class), name);
		return commodityList;
	}

	// 查询特定id的商品
	public Commodity findCommodityByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from commodity where pid=?";
		Commodity commodity = runner.query(sql, new BeanHandler<Commodity>(Commodity.class), pid);
		return commodity;
	}

	// 添加商品
	public void addCommodity(Commodity commodity) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into commodity values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, null, commodity.getPname(), commodity.getPrice(), commodity.getPimage(),
				commodity.getPdescripe(), commodity.getSale_number(), commodity.getShop_id(),
				commodity.getCategory_id(), commodity.getDate(), commodity.getInventory());
	}

	// 修改指定id的商品信息
	public void updateCommodity(Commodity commodity) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update commodity set pname=?,price=?,pimage=?,pdescripe=?,sale_number=?,shop_id=?,category_id=?,date=?,inventory=? where pid=?";
		runner.update(sql, commodity.getPname(), commodity.getPrice(), commodity.getPimage(), commodity.getPdescripe(),
				commodity.getSale_number(), commodity.getShop_id(), commodity.getCategory_id(), commodity.getDate(),
				commodity.getInventory(), commodity.getPid());
	}

	// 删除指定id的商品信息
	public void delCommodityByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from commodity where pid=?";
		runner.update(sql, pid);
	}

	// 验证登录
	public UserInfor loginServlet(String account, String password, String time) throws SQLException {
		UserInfor userInfor = new UserInfor();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_infor where user_id=? and user_password=?";
		String sql1 = "select * from user_infor where user_account=? and user_password=?";
		String sql2 = "select * from user_infor where user_phone=? and user_password=?";
		if (runner.query(sql, new BeanHandler<UserInfor>(UserInfor.class), account, password) != null) {
			userInfor = runner.query(sql, new BeanHandler<UserInfor>(UserInfor.class), account, password);
			String sql3 = "update user_infor set user_last_time=? where user_id=? and user_password=?";
			runner.update(sql3, time, account, password);
		} else if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), account, password) != null) {
			userInfor = runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), account, password);
			String sql3 = "update user_infor set user_last_time=? where user_account=? and user_password=?";
			runner.update(sql3, time, account, password);
		} else if (runner.query(sql2, new BeanHandler<UserInfor>(UserInfor.class), account, password) != null) {
			userInfor = runner.query(sql2, new BeanHandler<UserInfor>(UserInfor.class), account, password);
			String sql3 = "update user_infor set user_last_time=? where user_phone=? and user_password=?";
			runner.update(sql3, time, account, password);
		}
		return userInfor;
	}

	// 判断手机号是否存在
	public boolean checkPhone(String phone) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_infor where user_phone=?";
		if (runner.query(sql, new BeanHandler<UserInfor>(UserInfor.class), phone) != null) {
			b = true;
		}
		return b;
	}

	// 判断帐号是否存在
	public boolean checkAccount(String account) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user_infor where user_account=?";
		if (runner.query(sql, new BeanHandler<UserInfor>(UserInfor.class), account) != null) {
			b = true;
		}
		return b;
	}

	// 添加用户
	public boolean addUser(String phone, String account, String password, String time) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user_infor values(?,?,?,?,?,?,?,?,?)";
		runner.update(sql, null, account, password, phone, "未知", time, time,"2000年1月1日", 0);
		String sql1 = "select * from user_infor where user_account=?";
		if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), account) != null) {
			b = true;
		}
		return b;
	}

	// 修改密码
	public boolean updatePassword(String id, String oldpassword, String newpassword) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_infor set user_password=replace(user_password,?,?) where user_id=?";
		runner.update(sql, oldpassword, newpassword, id);
		String sql1 = "select * from user_infor where user_id=? and user_password=?";
		if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), id, newpassword) != null) {
			b = true;
		}
		return b;
	}

	// 修改性别
	public boolean updateSex(String id, String oldSex, String newSex) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_infor set user_sex=replace(user_sex,?,?) where user_id=?";
		runner.update(sql, oldSex, newSex, id);
		String sql1 = "select * from user_infor where user_id=? and user_sex=?";
		if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), id, newSex) != null) {
			b = true;
		}
		return b;
	}
	
	//修改地址
	public void updateAddress(String address,String name,String mobile,String aid) throws SQLException{
		String sql="update address set address=?,name=?,mobile=? where aid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,address,name,mobile,aid);
	}

	// 修改生日
	public boolean updateBirth(String id, String oldbirth, String newbirth) throws SQLException {
		boolean b = false;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user_infor set user_birth=replace(user_birth,?,?) where user_id=?";
		runner.update(sql, oldbirth, newbirth, id);
		String sql1 = "select * from user_infor where user_id=? and user_birth=?";
		if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), id, newbirth) != null) {
			b = true;
		}
		return b;
	}

	// 添加购物车
	public boolean addShopCart(String uid, String cid, String shopid, String shopname, String image, String cname,
			String count, String price) throws SQLException {
		boolean b = false;
		String sql1 = "select * from shopcart where uid=? and cid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (runner.query(sql1, new BeanHandler<UserInfor>(UserInfor.class), uid, cid) == null) {
			String sql = "insert into shopcart values(?,?,?,?,?,?,?,?,?)";
			runner.update(sql, null, uid, cid, shopid, shopname, image, cname, count, price);
			b = true;
		}
		return b;
	}

	// 根据用户id获取购物车
	public List<ShopCart> QueryShopCart(String uid) throws SQLException {
		List<ShopCart> shopCartList = new ArrayList<>();
		String sql = "select * from shopcart where uid=? order by shopid asc";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		shopCartList = queryRunner.query(sql, new BeanListHandler<ShopCart>(ShopCart.class), uid);
		return shopCartList;
	}

	// 根据商品id获取商店id
	public String queryShopid(String cid) throws SQLException {
		String shopid = null;
		String sql = "select * from commodity where pid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		ShopCart shopcart = runner.query(sql, new BeanHandler<ShopCart>(ShopCart.class), cid);
		shopid = shopcart.getShopid();
		return shopid;
	}

	// 根据商店id获取商店名
	public String queryShopName(String shopid) throws SQLException {
		String shopname = null;
		String sql = "select * from shop where shop_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		Shop shop = runner.query(sql, new BeanHandler<Shop>(Shop.class), shopid);
		shopname = shop.getShop_name();
		return shopname;
	}

	// 根据用户id获取收货地址
	public List<Address> queryAddress(String uid) throws SQLException {
		String sql = "select * from address where uid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Address> address = runner.query(sql, new BeanListHandler<Address>(Address.class), uid);
		return address;
	}

	//根据用户id查找订单
	public List<Orderfrom> QueryOrderFrom(String uid) throws SQLException {
		List<Orderfrom> orderList=new ArrayList<>();
		String sql="select * from orderfrom where uid=?";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		orderList=queryRunner.query(sql,new BeanListHandler<Orderfrom>(Orderfrom.class), uid);
		return orderList;
	}

	// 根据地址的id删除地址
	public void DelAddress(String aid) throws SQLException {
		String sql = "delete from address where aid=?";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		queryRunner.update(sql, aid);
	}

	// 添加收货地址
	public void AddAddress(String uid, String name, String address, String mobile, boolean check) throws SQLException {
		String sql = "insert into address values(?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, null, address, name, mobile, 0, uid);
	}

	// 删除购物车
	public void DeleteShopCart(String uid, String cid) throws SQLException {
		String sql = "delete from shopcart where uid=? and cid=?";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		queryRunner.update(sql, uid, cid);
	}

	// 添加订单
	public void AddOrderFrom(String uid, String cid, String address, String stime, String otime, String count)
			throws SQLException {
		String sql = "insert into orderfrom values(?,?,?,?,?,?,?,?)";
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		queryRunner.update(sql, uid, cid, null, address, stime, "已付款", otime, count);
	}

}
