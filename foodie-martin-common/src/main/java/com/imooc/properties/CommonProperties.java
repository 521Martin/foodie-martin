package com.imooc.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Describe:
 * @Created by tangfeng 2020-11-19 15:10
 */
@NoArgsConstructor
@Data
@Component
public class CommonProperties {

    /**
     * 搜索相关
     */
    @Value("${kdsp.search.price.area}")
    private String searchPriceArea;

    @Value("${search.engine.domain}")
    private String searchEngineDomain;

    @Value("${search.words.domain}")
    private String searchWordsDomain;

    /**
     * 0329需求新增
     */
    @Value("${search.words.v2.domain}")
    private String searchWordsV2Domain;

    @Value("${search.filter.show.front.category.ids}")
    private String searchFilterShowFrontCategoryIds;

    /**
     * 数据组事件上报 DEV PROD TEST
     */
    @Value("${data.stream.report.env}")
    private String dataStreamReportEnv;


    /**
     * H5
     */
    @Value("${h5.config.host}")
    private String h5Host;

    @Value("${h5.config.host.mall}")
    private String h5MallHost;

    @Value("${finance.m.https}")
    private String h5LoanHost;

    @Value("${tob.http}")
    private String tobHost;


    /**
     * LogAspect
     * true false
     */
    @Value("${log.aspect.return.log}")
    private String returnResultLogPrint;

    /**
     * {"printReturnResult":false,"reqPath":""}
     */
    @Value("${log.aspect.return.log.path}")
    private String returnResultLogPath;


    @Value("${survey.count.limit}")
    private Integer surveyCountLimit;


    @Value("${sku.activity.cache.open}")
    private String skuActivityCacheOpen;

    @Value("${coupon.category.label.limit.open:false}")
    private String couponCategoryLabelLimitOpen;


    @Value("${user.order.return.cash.labelIds:\"\"}")
    private String userOrderReturnCashLabelIds;

    @Value("${user.order.return.cash.send.mq:false}")
    private Boolean userOrderReturnCashSendMq;




    /**
     * 虚拟商品 栏目配置
     */
    @Value("${recharge.center.config}")
    private String rechargeCenterConfig;

    @Value("${coupon.excel.batch.process.config}")
    private String couponExcelBatchProcessConfig;

    /**
     * 订单向供应链下单次数（临时）
     */
    @Value("${order.limit}")
    private Integer orderLimit;

    /**
     * 供应链OAuth认证
     */
    @Value("${supply.oauth_url}")
    private String oauthUrl;

    @Value("${supply.oauth_client_id}")
    private String oauthClientId;

    @Value("${supply.oauth_secret}")
    private String oauthSecret;

    @Value("${supply.oauth_prikey}")
    private String oauthPrikey;

    @Value("${activity.error.notice.token}")
    private String activityErrorNoticeToken;

    /**
     * 租户公钥私钥
     */
    @Value("${tenant.oauth.keys:}")
    private String tenantKeys;

    //羊小咩订单队列
    @Value("${order.queue:order_queue}")
    String queueName;

    @Value("${order.queue.key:order_queue.key}")
    String routingKey;

    @Value("${order.exchange:order_exchange}")
    String orderExchange;

    //支付成功以后会员权益累计已省的记录
    @Value("${discount.queue:discount_queue}")
    String discountQueueName;

    @Value("${discount.queue.key:discount_queue.key}")
    String discountRoutingKey;

    @Value("${discount.exchange:discount_exchange}")
    String discountExchange;

    /**
     * kdsp-api  服务异常报警
     */
    @Value("${ding.talk.webhook.access.token}")
    private String kdspApiDingtalkToken;

    @Value("${ding.talk.webhook.mark.msg}")
    private String kdspApiDingtalkKeyWords;

    // 会员日
    @Value("${members.day:25}")
    private Integer membersDay;

    /**
     * 会员标签图片url
     */
    @Value("${kdsp.api.user.benefits.label.img.url}")
    private String userBenefitsLabelImgUrl;

    /**
     * 会员标签图片宽高比
     */
    @Value("${kdsp.api.user.benefits.label.img.ratio}")
    private BigDecimal userBenefitsLabelImgRatio;

    //临时增加的开关，是否接受用户的各种行为事件，默认是不接受
    @Value("${receiver.user.benefits.evet:false}")
    private Boolean isReceiveUserBenefitsEvent;



    /**
     * 金融登录认证
     */
    @Value("${finance.oauth_url}")
    private String financeOauthUrl;


    /**
     * 预下单模式开关
     */
    @Value("${preemption.supply.orderinfo.open.flag}")
    private String preemptionSupplyOrderInfoFlag;

    //====华云客服开始====
    /**
     * 商品详情页客服图标的跳转地址
     */
    @Value("${huayun.customer.link}")
    private String huayunCustomerLink;

    /**
     * 是否使用华云客服
     */
    @Value("${huayun.customer.show}")
    private Boolean huayunCustomerShowHuayun;

    /**
     * 商品详情页客服图标 商品地址
     */
    @Value("${huayun.customer.skulink}")
    private String huayunCustomerSkulinkHuayun;

    @Value("${huayun.customer.link.skillGroupId.sku}")
    private String huayunCustomerLinkSkillGroupIdSku;

    @Value("${huayun.customer.link.skillGroupId.afsService}")
    private String huayunCustomerLinkSkillGroupIdAfsService;

    //====华云客服结束====


    //礼品卡有效天数
    @Value("${user.card.valid.period}")
    private Integer userCardValidPeriod;

    /**
     * 是否展示评论入口
     */
    @Value("${sku.detail.show.comment.entry}")
    private Boolean skuDetailShowCommentEntry;


    //企业微信（客户加入企业微信）
    @Value("${max.member.service.qrcode:}")
    private String qrcode;

    @Value("${max.member.friends.isvalid:true}")
    private Boolean isValidFriends;

    @Value("${viturl.max.members:356}")
    private Integer viturlMaxMembers;


    /**
     * kdsp-api  服务异常报警
     */
    @Value("${ding.talk.webhook.skuservice.access.token}")
    private String kdspSkuServiceExtesionToken;

}
