package com.imooc.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;

/**
 * 订单状态枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatus implements IEnum<Integer> {
    /**
     * 待付款
     */
    WAITING_FOR_PAY(11, "待付款"),
    /**
     * 待发货
     */
    WAITING_FOR_DELIVERY(12, "待发货"),
    WAITING_FOR_RECEIVE(13, "待收货"),
    DELIVERYING_FOR_ORDER(14, "发货中"),
    /**
     * 订单完成
     */
    FINISH(21, "订单完成"),
    /**
     * 订单已付款主动取消
     */
    PAID_ORDER_CANCEL(31, "订单已付款主动取消"),
    /**
     * 只在拆单失败时，才会设置此状态，并且设置pay_status为5待退款
     */
    PAID_ORDER_AUTO_CANCEL(32, "订单已付款自动取消"),
    PAID_ORDER_REJECT_CANCEL(33, "订单拒收取消"),
    /**
     * 虚拟充值类订单已付款充值失败系统取消
     */
    PAID_VIRTUAL_RECHARGE_FAILED_CANCEL(36, "订单已付款充值失败取消"),

    PAID_ORDER_CANCEL_CLOSE(41, "订单已付款主动取消已退款订单关闭"),
    PAID_ORDER_AUTO_CANCEL_CLOSE(42, "订单已付款自动取消已退管订单关闭"),
    NOT_PAY_ORDER_CANCEL_CLOSE(43, "订单未支付主动取消订单关闭"),
    NOT_PAY_ORDER_TIMEOUT_CLOSE(44, "订单超时未支付订单关闭"),
    PAID_ORDER_REJECT_CANCEL_CLOSE(45, "订单拒收退款订单关闭"),
    /**
     * 虚拟充值类订单 已付款充值失败已退款订单关闭
     */
    PAID_VIRTUAL_RECHARGE_FAILED_CANCEL_CLOSE(46, "订单已付款充值失败已退款订单关闭"),
    SPLIT_ORDER_CLOSE(51, "订单拆单关闭"),
    ;

    private Integer value;
    private String desc;

    /**
     * 查看物流按钮展示的订单状态
     */
    public static final EnumSet DELIVERY_SET = EnumSet.of(WAITING_FOR_RECEIVE, FINISH);

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static OrderStatus valueOf(Integer status) {
        if (status == null || status == 0) {
            return null;
        }
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (Objects.equals(status, orderStatus.getValue())) {
                return orderStatus;
            }
        }
        throw new RuntimeException("未知的订单状态");
    }
}
