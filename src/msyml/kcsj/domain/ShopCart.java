package msyml.kcsj.domain;

public class ShopCart {
    private String cid;
    private String uid;
    private String pid;
    private String shopid;
    private String shopname;
    private String image;
    private String cname;
    private String count;
    private String price;

    @Override
    public String toString() {
        return "ShopCart{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", pid='" + pid + '\'' +
                ", shopid='" + shopid + '\'' +
                ", shopname='" + shopname + '\'' +
                ", image='" + image + '\'' +
                ", cname='" + cname + '\'' +
                ", count='" + count + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ShopCart() {
    }

    public ShopCart(String cid, String uid, String pid, String shopid, String shopname, String image, String cname, String count, String price) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.shopid = shopid;
        this.shopname = shopname;
        this.image = image;
        this.cname = cname;
        this.count = count;
        this.price = price;
    }
}
