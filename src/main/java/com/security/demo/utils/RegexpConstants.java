package com.security.demo.utils;

/**
 * @author Relic
 * @date 2019/6/27 12:00
 */
public class RegexpConstants {
    /**
     * 品牌速记码
     */
    public static final String BRAND_SHORTHAND_CODE = "^[A-B]{2-15}";
    /**
     * 品牌代码
     */
    public static final String BRAND_CODE = "^[0-9]{2-8}";
    /**
     * 用户名
     */
    public static final String USERNAME = "^[-_a-zA-Z0-9]{4,16}$";
    /**
     * 密码
     */
    public static final String PASSWORD = "^[a-z0-9_-]{6,18}$";
    /**
     * 姓名 中文1-20位
     */
    public static final String BRAND_NAME = "^[\\u4e00-\\u9fa5]{1,20}$";
    /**
     * 姓名 中文1-20位
     */
    public static final String NAME = "^[\\u4e00-\\u9fa5]{1,20}$";
    /**
     * 包括固话和手机号
     */
    public static final String PHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$|^0\\d{2,3}-?\\d{7,8}$";
    /**
     * 邮箱
     */
    public static final String EMAIL = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
    /**
     * 身份证
     */
    public static final String ID_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
    /**
     * 手机号码
     */
    public static final String MOBILE_PHONE = "^(13[0-9]|14[5|7]|15[0-9]|18[0-9])\\d{8}$";
    /**
     * 角色名称
     */
    public static final String ROLE_NAME = "^[-_a-zA-Z0-9]{4,30}$";
    /**
     * 公司名称缩写
     */
    public static final String COMPANY_ABB = "^[a-z]{3,15}$";
    /**
     * 名称首字母
     */
    public static final String FIRST_NAME = "^[a-z]{1,16}$";
    /**
     * 证件类型
     */
    public static final String CARD_TYPE = "^\\d{1,10}$";
    /**
     * 地址
     */
    public static final String ADDRESS = "^[\\u4e00-\\u9fa5]{5,20}[0-9]{1,4}[\\u4e00-\\u9fa5]{1,5}$";
    /**
     * 注册资金
     */
    public static final String REG_FUND = "^[0-9]{3,20}$";
    /**
     * 机构代码
     */
    public static final String REG_NUMBER = "^[0-9A-Z]{9}$";
    /**
     * 统一社会信用代码
     */
    public static final String UNISCID = "^[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}";
}
