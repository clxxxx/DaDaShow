package com.xzry.takeshow.base;

/**
 * Created by admin on 2017/2/22.
 */
public class BaseValue {
    /*登录状态*/
    public static boolean ISLOGIN = false;
    public static String USER_ID = "0";
    public static String TOKEN = "0";
    /*登录事件标记*/
    public static String LOGIN = "login";
    /*登录成功*/
    public static String LOGIN_SUCCESS = "login_success";

    /*状态栏高度*/
    public static int STATUS_HEIGHT = 50;

    public static int WINDOW_HEIGHT = 0;
    public static int WINDOW_WIDTH = 0;
    //时尚圈最多上传图片
    public static int DYANMIC_MAX_IMAGE_SIZE = 9;

    //加入搭搭最多上传图片
    public static int ADD_MAX_IMAGE_SIZE = 6;

    /**
     * 地图相关
     */
    public static String latitude = "";
    public static String longitude = "";
    public static int update_position_time = 900;
    public static String CURRENT_POSITION = "CURRENT_POSITION";
    public static String CURRENT_POSITION_NOFITY="com.xzry.takeshow.base.CURRENT_POSITION_NOFITY";



    /*获取验证码*/
    public static final String GETCODE_URL = "http://119.23.75.159:8080/shop-password/sr/userInfo/loginVerifyCode";
    /*登录接口*/
    public static final String LOGIN_URL = "http://119.23.75.159:8080/shop-password/sr/userInfo/login";



    /*首页基础地址*/
    public static final String HOME_BASE_URL = "http://119.23.73.250:8080";
    /*首页banner接口*/
    public static final String HOME_BANNER_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate";
    /*首页精选活动接口*/
    public static final String HOME_ACTIVITIES_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate/featured";
    /*首页每日特价接口*/
    public static final String HOME_SPECIALS_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate/special";
    /*首页专题推荐接口*/
    public static final String HOME_RECOMMOND_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate/subject";
    /*首页品牌精选接口*/
    public static final String HOME_HOTBRAND_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate/hotBrand";
    /*首页热门商品接口*/
    public static final String HOME_HOTGOODS_URL = HOME_BASE_URL + "/shop-mms/mms/index/plate/hotGoods";
    /*商品一级分类*/
    public static final String ALL_COMMODITY_CLASSIFY_URL = HOME_BASE_URL + "/shop-mms/mms/category/parent";
    /*商品二级分类*/
    public static final String ALL_COMMODITY_CLASSIFY_URL2 = HOME_BASE_URL + "/shop-mms/mms/category/sub/";
    /*品牌街*/
    public static final String BRAND_STREET_URL = HOME_BASE_URL + "/shop-mms/mms/brand/list/";
    /*品牌首页*/
    public static final String BRAND_URL = HOME_BASE_URL + "/shop-mms/mms/brand/";

    /*商品详情*/
    public static final String COMMODITY_INFO_URL = HOME_BASE_URL + "/shop-mms/mms/goods/details";
    /*商品库存信息*/
    public static final String COMMODITY_INVENTORY_URL = HOME_BASE_URL + "/shop-mms/mms/goods/inventory";

    /*附近门店*/
    public static final String HOME_NEARBY_STORES_URL = HOME_BASE_URL + "/shop-mms/mms/store/near";
    /*获取门店所有商品*/
    public static final String BRAND_SHOP_MAIN_URL = HOME_BASE_URL + "/shop-mms/mms/store/storeInfo";
    /*获取门店分类商品*/
    public static final String BRAND_SHOP_MAIN_TYPE_URL = HOME_BASE_URL + "/shop-mms/mms/store/storeGoods";



    /*活动首页*/
    public static final String HOME_ENENT_TOPIC_MAIN_URL = HOME_BASE_URL +"/shop-mms/mms/activity/category";
    /*活动页面fragment url*/
    public static final String ENENT_TOPIC_INFO_URL = HOME_BASE_URL + "/shop-mms/mms/activity/goods";
    /*积分商城首页*/
    public static final String INTEGRAL_SHOP_URL = HOME_BASE_URL + "/shop-mms/mms/point/category";
    /*积分商城首页*/
    public static final String INTEGRAL_SHOP_INFO_URL = HOME_BASE_URL + "/shop-mms/mms/point/goods";

    /*获取个人中心信息*/
    public static final String GET_MINE_DATA_URL = HOME_BASE_URL + "/shop-mms/mms/me";
    /*获取我的关注*/
    public static final String GET_MY_ATTENTION_URL = HOME_BASE_URL + "/shop-mms/mms/me/follow";
    /*获取我的粉丝*/
    public static final String GET_MY_FANS_URL = HOME_BASE_URL + "/shop-mms/mms/me/fans";
    /*获取我的积分*/
    public static final String GET_MY_INTEGRAL_URL = HOME_BASE_URL + "/shop-mms/mms/me/point";
    /*获取我的积分列表*/
    public static final String GET_MY_INTEGRAL_LIST_URL = HOME_BASE_URL + "/shop-mms/mms/me/pointDetail";
    /*获取我的足迹列表*/
    public static final String GET_MY_FOOT_MARK_LIST_URL = HOME_BASE_URL + "/shop-mms/mms/me/footPrint";
    /*足迹删除*/
    public static final String GET_DELETE_MY_FOOT_MARK_URL = HOME_BASE_URL + "/shop-mms/mms/me/deleteFootPrint";

    /*获取收藏的商品*/
    public static final String GET_MY_GOODS_LIST_URL = HOME_BASE_URL + "/shop-mms/mms/me/goodsFollow";
    /*获取收藏的商家*/
    public static final String GET_MY_MERCHANT_LIST_URL = HOME_BASE_URL + "/shop-mms/mms/me/storeFollow";



    /*获取个人资料*/
    public static final String GET_PERSONAL_CENTER_URL = HOME_BASE_URL + "/shop-mms/mms/me/profile";
    /*修改头像*/
    public static final String MODIFY_THE_HEAD_URL = HOME_BASE_URL + "/shop-mms/mms/me/updateFace";
    /*修改昵称*/
    public static final String MODIFY_NICKNAME_URL = HOME_BASE_URL +"/shop-mms/mms/me/updateNick";





    /*订单基础地址*/
    public static final String ORDER_BASE_URL = "http://119.23.222.219:8080";
    /*订单接口*/
    public static final String GET_ORDER_URL = ORDER_BASE_URL + "/shop-order/oms/api/getOrder";
    /*添加收藏商品*/
    public static final String COLLECT_URL = ORDER_BASE_URL + "/shop-mms/mms/me/addGoodsFollow";
    /*添加购物车*/
    public static final String ADD_SHOPPINGCAR_URL = ORDER_BASE_URL + "/shop-order/oms/api/addShoppingCart";
    /*提交订单*/
    public static final String SUBMIT_ORDER_URL = ORDER_BASE_URL + "/shop-order/oms/api/placeOrder";
    /*查询购物车*/
    public static final String GET_SHOPPINGCAR_URL = ORDER_BASE_URL + "/shop-order/oms/api/getShoppingCart";
    /*查询购物车*/
    public static final String DELETE_SHOPPINGCAR_URL = ORDER_BASE_URL + "/shop-order/oms/api/delShoppingCart";
    /*收件人地址显示*/
    public static final String GET_ADDRESS_SHOW = ORDER_BASE_URL + "/shop-order/oms/api/findConsignee";
    /*添加收件人*/
    public static final String ADD_ADDRESS_URL = ORDER_BASE_URL + "/shop-order/oms/api/modifyConsignee";
    /*删除收件人*/
    public static final String DEL_ADDRESS_URL = ORDER_BASE_URL + "/shop-order/oms/api/delConsignee";

    public static final String HOME_SEARCH_BASE_URL = "http://39.108.131.116:8080";
    /*搜索关键字*/
    public static final String SEARCH_DATA_URL = HOME_SEARCH_BASE_URL + "/shop-solr/sr/account/searchPage";
    /*搜索历史记录*/
    public static final String SEARCH_HISTORY_RECORD_URL = HOME_SEARCH_BASE_URL + "/shop-solr/sr/account/history";
    /*删除历史记录*/
    public static final String REMOVE_HISTORY_RECORD_URL = HOME_SEARCH_BASE_URL + "/shop-solr/sr/account/historyDelete/{userId}";


    /**
     * 用户信息
     */
    public interface User {
        String ISFIRST = "isfirst";         //是否打开过
        String ISLOGIN = "loginstate";      //登录状态
        String USERID = "userId";           //用户id
        String TOKEN = "token"; 		        //Md5值，用户生成唯一标识
        String MOBILE = "mobile"; 		    // 手机
        String NICKNAME = "nickname" ; 		//昵称
        String SEX = "sex"; 			    // 性别，0，保密；1，男；2，女
        String PERSONALITY = "personality"; // 个性描述
        String HEADIMG = "headerUrl";	    // 上传用户图像
        String MARTURL = "martUrl"; 		    // 用户二维码
        String SHIPPING_ADDRESS = "orderConsignee"; // 收货地址
        String VISITCOUNT = "visitCount"; 	//登录次数
        String LASTLOGIN = "lastLogin"; 	// 最后一次登录时间
        String CITY = "city";               //定位城市
    }
//    /**
//     *  地址
//     */
//    public interface Address {
//        String ADS_Name = "ads_consignee";
//        String ADS_Mobile = "ads_mobile";
//        String ADS_Province = "ads_province";
//        String ADS_City = "ads_city";
//        String ADS_District = "ads_district";
//        String ADS_Address = "ads_address";
//        String ADS_Isdefault = "ads_isdefault";
//    }
}
