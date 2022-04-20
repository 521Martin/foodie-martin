package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by renfeng on 2020/8/12.
 */
@Getter
@AllArgsConstructor
public enum FrontOrderStatus {
    /**
     * 待付款
     */
    PENDING_PAYMENT(11,"等待买家付款","等待买家付款","剩%s小时%s分钟自动关闭",OrderStatus.WAITING_FOR_PAY),
    /**
     * 待发货
     */
    PENDING_DELIVER(21,"等待卖家发货","等待卖家发货","等待卖家发货",OrderStatus.WAITING_FOR_DELIVERY),
    /**
     * 待收货
     */
    DELIVERED(31,"卖家已发货","卖家已发货","还剩%s天自动确认收货",OrderStatus.WAITING_FOR_RECEIVE),
    /**
     * 已完成
     */
    FINISH(41,"已完成","交易完成","感谢您的购物，欢迎再次光临！",OrderStatus.FINISH),
    /**
     * 已取消
     */
    CANCELED(51,"已取消","交易关闭","您的订单已取消！",null),

    /**
     * 取消订单审核中
     */
//    CANCELING(52,"等待商家审核","等待商家审核","等待商家审核",OrderStatus.PAID_ORDER_CANCEL),

    /**
     * 充值失败 退款中
     */
    RECHARGE_FAILED(61,"充值失败","充值失败","退款中",null),
    /**
     * 充值失败 已退款成功
     */
    RECHARGE_FAILED_REFUND(62,"充值失败","充值失败","已退款成功",null),
    ;

    private Integer code;
    private String desc;
    private String msg;
    private String text;
    private OrderStatus orderStatus;


    public static FrontOrderStatus valueOf(Integer code) {
        for (FrontOrderStatus status : FrontOrderStatus.values()) {
            if(Objects.equals(code,status.getCode())) {
                return status;
            }
        }
        return null;
    }
}
