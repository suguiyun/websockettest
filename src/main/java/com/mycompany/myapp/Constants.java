package com.mycompany.myapp;

import java.text.SimpleDateFormat;

/**
 * web常量
 */
public abstract class Constants {
	/**
	 * 路径分隔符
	 */
	public static final String SPT = "/";
	/**
	 * 索引页
	 */
	public static final String INDEX = "index";
	/**
	 * 默认模板
	 */
	public static final String DEFAULT = "default";
	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	/**
	 * GBK-2312编码
	 */
	public static final String GB2312 = "GB2312";
	/**
	 * 提示信息
	 */
	public static final String MESSAGE = "message";
	/**
	 * cookie中的JSESSIONID名称
	 */
	public static final String JSESSION_COOKIE = "JSESSIONID";
	/**
	 * url中的jsessionid名称
	 */
	public static final String JSESSION_URL = "jsessionid";
	/**
	 * HTTP POST请求
	 */
	public static final String POST = "POST";
	/**
	 * HTTP GET请求
	 */
	public static final String GET = "GET";

	public static final String PROCESS_URL = "processUrl";

	public static final String RETURN_URL = "returnUrl";

	/**
	 * 模板后缀
	 */
	public static final String TPL_SUFFIX = ".html";

	/**
	 * 公用模板
	 */
	public static final String TPLDIR_COMMON = "common";
	/**
	 * 标签模板
	 */
	public static final String TPLDIR_TAG = "tag";

	/**
	 * 上传路径
	 */
	public static final String UPLOAD_PATH = "/";
	/**
	 * 资源路径
	 */
	public static final String RES_PATH = "/r/";
	/**
	 * 模板路径
	 */
	public static final String TPL_BASE = "/pages";
	/**
	 * 全文检索索引路径
	 */
	public static final String LUCENE_PATH = "/lucene";
	/**
	 * 自定义标签body模板存入路径
	 */
	public static final String TAG_BODY_PATH = "/pages/tag/";

	/******************************************************************/

	/**
	 * 自定义标签指定模板参数关键字
	 */
	public static final String PARAM_TAG_BODY_STYLE = "tpl_style";


	/**
	 * 列表样式模板路径
	 */
	public static final String TPL_STYLE_LIST = "/mye_store/sys_defined/style_list/style_";
	/**
	 * 内容分页模板路径
	 */
	public static final String TPL_STYLE_PAGE_CONTENT = "/mye_store/sys_defined/style_page/content_";

	/**
	 * 标签列表样式模板路径
	 */
	public static final String TPLDIR_STYLE_LIST = "style_list";
	/**
	 * 列表样式模板
	 */
	public static final String TPLDIR_STYLE_PAGE = "style_page";

	/**
	 * 列表分页模板路径
	 */
	public static final String TPL_STYLE_PAGE_CHANNEL = "/WEB-INF/mye_store/sys_defined/style_page/channel_";
	/**
	 * 页面禁止访问
	 */
	public static final String ERROR_403 = "error/403";

	/**
	 * 订单表表名前缀
	 */
	public static final String EX_ORDER_PREFIX = "ex_order_";
	/**
	 * 订单流水表表名前缀
	 */
	public static final String EX_TRADE_PREFIX = "ex_trade_";

	/**
	 * 公司钱包地址表前缀
	 */
	public static final String CRYPTO_ADDRESS_PREFIX = "crypto_address_";

	public static final Byte NUM_ONE = 1;

	public static final Byte NUM_TWO = 2;

	public static final Byte NUM_THREE = 3;

	public final static String USER_MD5_KEY = "jys20170921";			//用于用户密码生成的固定串
	public final static SimpleDateFormat ymdhms_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public final static SimpleDateFormat ymd_format = new SimpleDateFormat("yyyy-MM-dd");

	public static final String TIME_START_STR = " 00:00:00";

	public static final String TIME_END_STR = " 23:59:59";

	public static final String CHINAL_CODE = "156+86";//实名认证专用

	public static final String KOREA_CODE = "410+82";//实名认证专用

	public static final String ADMIN_USER = "admin_user";

	public static final String AUTH_STATUS = "1";

	public static final Integer SUCCESS_ONE = 1;

	public static final Integer EXCEPTION_ONE = -1;

	public static final Integer EXCEPTION_TWO = -2;

	public static final Integer EXCEPTION_THREE = -3;

	public static final Integer EXCEPTION_FOUR = -4;

	public static final Integer EXCEPTION_999 = 999;

	public static final Integer CURRENT_PAGE_SIZE = 20;

	public static final Integer CURRENT_PAGE_SIZE_TEN = 10;

	public final static SimpleDateFormat yyyy_format = new SimpleDateFormat("yyyy");

	public final static SimpleDateFormat ym_format = new SimpleDateFormat("yyyy-MM");

	public final static SimpleDateFormat ym_format_x = new SimpleDateFormat("yyyy/MM");

}
