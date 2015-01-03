package nagaseiori.commons.constants;

/**
 * @describe: <pre>
 * 常量
 * </pre>
 */
public interface DefaultConstants {
	
	// 存放于request session 里面的值名字
	public static final String CURRENT_USER_ID = "currentUserId";
	// 存放于request session 里面的值名字
	public static final String CURRENT_USER = "currentUser";
	// 存放于cookie里面的加密串名字
	public static final String COOKIE_NAME_VALUE = "remember";
	// 业务加密用户名的key AES 128
	public static final String AES_CRYPTO_SECRET_BUSINESS = "^1*afdsadfm;;fsd";
	// app加密用户名的key DES
	public static final String DES_CRYPTO_SECRET_APP = "JKffdsaprwq4^&Lmfa23<>&H";
	// cookie 昵称
	public static final String COOKIE_NICKNAME = "_niiwoo_nickname";
	
	// 后台接口 取得钱小二信息
	public static final String GET_CUSTOMER_URL_DEVELOP = "http://192.168.18.39:8080/niiwoo-call-center/chat/getFreeService.do";
	public static final String GET_CUSTOMER_URL_TEST = "http://192.168.18.38:8080/niiwoo-call-center/chat/getFreeService.do";
	
	// 业务接口 取得用户基础信息
	public final static String GET_USER_INFO_URL = "http://121.13.249.210:8006/NiwoService/PostIMData";
	
	// 业务接口所需token
	public final static String GET_USER_INFO_TOKEN = "token=tuandainiwo";
	
}
