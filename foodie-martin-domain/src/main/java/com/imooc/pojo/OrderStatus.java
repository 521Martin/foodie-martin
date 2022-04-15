package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单状态表;订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderStatus extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单ID;对应订单表的主键id
     */
    private String orderId;

    /**
     * 订单状态
     */
    private Integer orderStatus;


    /**
     * 支付成功时间;对应[20:已付款，待发货]状态
     */
    private LocalDateTime payTime;

    /**
     * 发货时间;对应[30：已发货，待收货]状态
     */
    private LocalDateTime deliverTime;

    /**
     * 交易成功时间;对应[40：交易成功]状态
     */
    private LocalDateTime successTime;

    /**
     * 交易关闭时间;对应[50：交易关闭]状态
     */
    private LocalDateTime closeTime;

    /**
     * 留言时间;用户在交易成功后的留言时间
     */
    private LocalDateTime commentTime;


}
