package com.imooc.result;


import com.imooc.exception.BizException;
import com.imooc.log.LogWarnUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * API调用结果状态码定义
 *
 * @author rong yang
 */
@Slf4j
public enum ResultCode {
    /**
     * 响应[消息中心]专用
     */

    SUCCESS("0000", "成功"),
    FAILURE("0001", "失败"),


    BIZ_CODE_MALL_GOODS("2101", "商品已失效，请刷新后再来吧"),

    //40x  : 鉴权及支付密码
    UNAUTHORIZED("401", "未登录、请先登录"),

    LOCK("4099","用户被锁定"),
    INVALID_USERNAME_OR_PASSWORD("4098","用户名密码不正确"),
    USER_NOT_SAVED("4097","用户不存在或者密码错误"),
    INVALID_PERSONNEL_TYPE("4096","当前用户不允许登录"),
    //REFRESH_TOKEN_EXPIRE("4011", "Refresh-Token过期，请重新登录系统"),
    //FORBIDDEN("4030", "没有访问权限，禁止访问"),
    //INVALID_USERNAME_OR_PASSWORD("4031", "用户名密码不正确"),
    //INVALID_VERIFICATION_CODE("4032", "验证码不正确"),
    //ACCESS_TOKEN_EXPIRE("4033", "Access-Token过期，请重新获取Access-Token"),
    INVALID_PAY_PWD("4034", "支付密码错误"),

    //40x: 参数数据异常
    PARAM_ERROR("4000", "参数错误"),
    PHONE_ERROR("4001", "手机号格式不正确"),
    EMAIL_ERROR("4002", "邮箱格式不正确"),
    SECRET_FREE_EROR("4102", "流程7,不能选免密登录"),

    //DATA_CONVERT_ERROR("4035","数据转换错误"),


    /**
     * 系统异常
     */
    SERVICE_ERROR("5000", "服务器异常"),
    CUSTOM_ERROR_MSG("5001", "自定义异常信息"),


    /**
     * 业务异常
     */
    //DOWNLOAD_ERROR("6000", "有人正在下载,请稍后再试"),

    //重复提交  操作频繁
    //REPEAT_SUBMIT_ERROR("6010", "重复提交"),
    FREQUENCY_OPERATION("6011", "操作频繁，请稍后重试"),
    TRIGGER_CURRENT_LIMITING("6012", "商品太火爆了,本次您没有拿到机会哈"),

    //数据库操作
    DB_OPERATE_ERROR("6020", "数据库操作失败"),

    //京东接口
    JD_ACCESS_TOKEN_ERROR("6030", "获取京东ACCESS_TOKEN错误"),

    // 商品校验
    SKU_NOT_EXIST("6040", "商品不存在"),
    SKU_LOSE_EFFECT("6041", "商品已失效"),


    //活动商品或地址 校验
    ADDR_NOT_EXIST("6042", "未获取到有效的地址信息，请完善地址信息"),
    QUERY_NO_RESULT("6043", "未查询到结果"),
    COUPON_LOSE_EFFICACY("6044", "优惠券不匹配或已失效"),
    OVER_LIMIT_COUNT("6045", "超出商品可购买数量"),
    TOTAL_FEE_DIFF("6046", "哎呀,商品已恢复原价，需要重新下单哦！"),

    //提交订单
    ORDER_NOT_EXIST("6047", "未查询到订单"),
    FREIGHT_FEE_DIFF("6048", "提交订单运费金额与后端计算金额不一致"),
    ORDER_HAS_SPLIT("6049","订单已拆单"),

    //用户不存在
    USER_NOT_EXIST("6050", "用户不存在或未登录"),

    //购物车
    //SHOPCAR_DELETE_ERROR("6050","购物车商品移除失败"),

    //商品
    SUBMIT_SKU_NOT_EXIST("6051", "您购买的商品已下架"),
    SUBMIT_SKU_NOT_STOCK("6052", "您购买的商品库存不足"),
    SUBMIT_SKU_LIMIT("6052", "您购买的商品超过限购数量"),
    REDUCE_SKU_STOCK_ERROR("6053", "库存扣减失败"),


    //CONFIRM_DELIVERY_EROR("6053", "不符合确认收货条件，稍后重试"),
    ACTIVITY_SKU_NOT_EXIST("6054", "未查询到该活动商品"),
    MISMATCH_CONDITION("6055", "邀请人数小于3人，不符合购买条件"),
    MISMATCH_CONDITION_COUNT("6056", "秒杀下单商品超过数量限制"),
    //AREA_LIMIT("6056", "商品在该配送区域内受限"),
    //ACTIVITY_SKU_NOT_VALID("6057", "Sku关联的活动无效"),
    ACTIVITY_NOT_VALID("6058", "活动无效"),
    MISMATCH_CONDITION_COUNT_HUNDRED("6059", "拼团下单商品超过数量限制"),
    MISMATCH_CONDITION_GOODS_HUNDRED("6060", "您已经参加团购，不可以重复参加拼团活动~"),

    // 商品
    //SKU_COST_FEE_DIFF("7001","商品金额校验不一致"),
    SKU_COUNT_ERROR("7002", "商品数量错误"),
    SKU_AMOUNT_ERROR("7003", "商品金额错误"),


    //优惠券校验
    //COUPON_NO_MATCH_SKU("7004","没有与优惠券匹配的商品"),
    COUPON_NOT_EXIST("7005", "优惠券不存在"),
    COUPON_OUTNUMBER_TOTAL("7006", "优惠券抢完了,下次早点来~"),
    COUPON_OUTNUMBER_TOTAL_WILL("7007", "即将领取的数量将达总发行量"),
    COUPON_OUTNUMBER_USER("7008", "您已经领过了"),
    COUPON_OUTNUMBER_USER_WILL("7009", "用户即将领取的数量将超限额"),
    COUPON_NOT_NEW_USER("7010", "您已经是老用户了，不符合新人礼包的领取条件哦"),
    COUPON_TYPE_NOT_MATCH("7011", "该优惠券属自动发放，不可领取"),


    //订单快照
    //CREATE_ORDER_SNAPSHOOT_ERROR("7012","创建订单快照失败"),
    //UPLOAD_ORDER_SNAPSHOOT_ERROR("7013","创建订单快照上传失败"),

    //订单： 下单
    CREATE_ORDER_FAIL("7014", "下单失败"),
    ZERO_BUY_CREATE_ORDER_FAIL("7015", "0元购下单失败"),

    SKU_USERD_MORETHAN_TWO_ACTIVITY("7016", "商品不能参与多个活动"),

    CREATE_ORDER_SKU_ISREPEAT("7017", "商品信息有误，存在重复的SkuNo商品!"),
    CREATE_ORDER_SKU_COUNT_LIMIT("7018", "购物车最多支持50个商品同时结算哦~"),
    QUERY_ORDER_PAY_MSG_FAIL("7019", "查询订单支付详情信息失败"),
    QUERY_ORDER_LIST_PAY_MSG_FAIL("7019", "查询订单列表支付信息失败"),
    VM_ORDER_SUBMIT_FAIL("7020", "虚拟商品提交订单失败"),
    PROMOTER_PHONE_NO_FAIL("7021", "推广员手机号不可用"),
    SKU_CAN_NOT_SELF_MENTION_SHIPMENT("7022", "订单中包含不支持自提的商品"),


    BATCH_UPDATE_SKU_FAIL("9001","批量修改商品execl中存在错误的数据"),
    BATCH_UPDATE_SKU_NO_DATA("9002","批量修改商品execl中无数据"),
    SKUNO_ERROR_FAILURE("1000001", "第一列名称错误，应为sku_no"),
    TAGID_ERROR_FAILURE("1000002", "第二列名称错误，应为tag_id"),


    //  ---------------------------------------  统一默认规则状态码 --------------------------------------

    // 0000 通用成功，0001  通用失败

    //  ---------------------------------------  旧业务状态码（不在额外添加） --------------------------------------

    //  鉴权 刷新token： 401   4000 (兼容旧业务状态码)

    // 系统异常： 5000

    // 9999 及以下，兼容旧业务状态码


    //  ---------------------------------------  新规则状态码 --------------------------------------
    //  ---------------  规则建议： 10000 20000 等整数万 建议为主业务统一异常 --------------------------
    //  ---------------  每种主业务下的细分业务，可以按100 累加，避免扩展超出范围-------------------------

    //  10000 用户账户(用户  vcc账户  现金贷账户  积分  会员)

    CANCELLATION_BILL_OUTSTANDING("11001", "因您仍有账单暂未结清，需要您结清账单后，重新申请账户注销服务"),
    CANCELLATION_UNCONFIRMED_RECEIPT_ORDER("11002", "因您的账户有待确认收货的订单，如您未收到商品，注销后将无法查询订单，请您确认收货后重新申请账户注销服务"),
    CANCELLATION_AFTER_SALES_ORDER("11003", "您的账户中有售后中的订单，售后服务完成后请重新申请账户注销服务"),
    CANCELLATION_UN_CANCEL_ORDER("11005", "因您的账户中有下单未支付的订单，请您取消后重新申请注销"),
    CANCELLATION_LOGOUT_SUCCEEDED("11004", "您的羊小咩账号已注销，非常感谢您这段时间的陪伴"),
    CANCELLATION_LOGOUT_CAN("11006", "没有售后订单和金融业务在途，可以注销"),

    USER_ERROR("40000", "用户信息异常"),
    USER_TENANT_INVALID("40100", "租户信息非法"),
    USER_TENANT_NOT_EXIST("40101", "租户信息不存在"),
    USER_TENANT_VERSION_NOT_EXIST("40102", "租户版本信息不存在"),

    GET_FINANCE_USER_ERROR("40103", "获取JR-Token信息异常"),

    //  20000 商品（商品  购物车  订单确认页  订单）


    //  30000 订单（购物车  订单确认页 订单  售后单 订单列表  订单详情  物流）
    SUPPLY_PREEMPTED_ORDER_ERROR("30000", "预占库存异常"),
    SUPPLY_PREEMPTED_ORDER_DATA_ERROR("30000", "预占库存返回数据异常"),


    // 40000 活动（ 优惠券  满减活动 零元购）卡
    ACTIVITY_ERROR("40000", "活动统一异常"),
    ACTIVITY_COUPON("40100", "优惠券统一异常"),
    ACTIVITY_COUPON_NOT_ONLINE("40101", "优惠券暂不可领"),
    ACTIVITY_COUPON_EXPIRE("40102", "优惠券领取时间已过期"),
    ACTIVITY_COUPON_NOT_START("40103", "还没到开抢时间哦~"),

    CARD_RECORD_NOT_EXISTS("46000","用户卡记录不存在"),
    CARD_OUT_OF_VALIDITY("46001","卡不在使用有效期内"),
    CARD_INSUFFICIENT_BALANCE("46002","卡余额不足"),
    CARD_BALANCE_DEDUCT_FAIL("46003","卡余额抵扣失败"),
    CARD_BALANCE_REPEAT_CHANGE("46005","卡余额重复变更"),
    CARD_DEDUCT_RECORD_NOT_EXISTS("46006","不存在抵扣记录"),
    CARD_IO_SAVE_FAIL("46007","卡交易流水生成失败"),
    CARD_PRODUCTION_DENOMINATION_BALANCE_EXCEPTION("46008","卡生产面值和余额异常"),
    CARD_PRODUCTION_REPEAT("46010","重复生产卡"),
    CARD_PRODUCTION_FAIL("46011","卡生产异常"),
    CARD_PRODUCTION_VALID_TIME_EXCEPTION("46012","卡生产有效期异常"),
    CARD_VALID_FAIL("46013","无效礼品卡"),
    //此编码已被H5和小程序在前端做特殊的过期判断，不要修改code值
    CARD_VALID_TIMEOUT_EXCEPTION("46014","礼品卡已过有效期"),
    GIFT_CARD_FEE_DIFF("46015","提交订单礼品卡金额与后端计算金额不一致"),
    WRONG_FREIGHT_FEE("46016","运费计算异常，请稍后"),

    GIFT_CARD_STATUS_ERROR("47000","礼品卡状态不正确"),




    // 50000 地址及收货地址


    // 60000 推荐 搜索 数据组对接业务

    // 70000 发票
    INVOICE_NO_TAX_NUMBER("70001","抬头类型为单位时，税号必填"),
    INVOICE_NUONUO_FAIL("70002","开票失败，请联系客服"),
    INVOICE_NUONUO_LIMIT("70003","开票失败 超出重开次数"),
    INVOICE_NO_SUCCESS("70004","无可重新发送的发票"),
    INVOICE_NO_TAXRATE("70005","哎呀，小咩没有找到商品对应的税率呢，您可以通过客服电话进行申请线下开票哦！"),
    INVOICE_NUONUO_EXPIRE_PHONE("70006","重发短信次数超限，请明天再试"),
    INVOICE_NUONUO_NO_PRICE("70007","可开票金额为0"),
    INVOICE_NUONUO_0_AMOUNT("70008", "您的提交的订单中(%s)包含0元支付的商品，该类商品不支持开票额"),
    QINGHAI_DUPLICATE_CARD("71000", "您已经办理过业务了，不能重复办理"),
    QINGHAI_WRONG_PACKAGE("71001", "办理套餐和礼品卡金额错误"),
    QINGHAI_WRONG_HEADER("71002", "当前请求头非青海组织"),


    DATA_DOES_NOT_EXIST("80001","数据不存在"),

    // 900** 评论相关
    COMMENT_REQUEST_PARAM_ERROR("90001", "参数有误")
    ;






    //90000 系统 参数  外部第三方异常

    ;


    private String code;

    private String msg;


    private ResultCode(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        LogWarnUtil.warn(log, ResultCode.FAILURE, "test-{}-{}-{}", 1, 2, 3, new BizException("kakakkakakaka"));
    }

}
