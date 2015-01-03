package nagaseiori.commons.constants;

/**
 * @describe: <pre>
 * 返回码
 * </pre>
 */
public interface RtnConstants {
	
	// 返回状态码
    public static final String RTN = "code";
    // 返回提醒信息
    public static final String RTN_MSG = "msg";
    // 返回数据
    public static final String DATA = "data";

    // 成功
    public static final int OK = 1;
    
    // 失败
    public static final int FAIL = 0;

    // 系统错误
    public static final int SYS_ERROR = 700;

    // 参数错误
    public static final int PARAM_ILLEGALITY = 600;

    // 手机号码格式不正确
    public static final int MOBILENO_ERROR = 1001;

    // 获取验证码太过于频繁
    public static final int VERIFY_TOO_OFTEN = 1002;

    // 验证码错误
    public static final int VERIFY_ERROR = 1003;

    // 验证码过期
    public static final int VERIFY_EXPIRE = 1004;

    // 照片数量已达到限制
    public static final int PHOTO_TOO_MANY = 1005;

    // 手机号码已绑定过
    public static final int MOBILENO_YET_BIND = 1006;

    // 用户不是有效的
    public static final int USER_ILLEGALITY = 1100;
}
