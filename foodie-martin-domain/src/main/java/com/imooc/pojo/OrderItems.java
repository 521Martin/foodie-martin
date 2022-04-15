package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单商品关联表 
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItems extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 归属订单id
     */
    private String orderId;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品图片
     */
    private String itemImg;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 规格id
     */
    private String itemSpecId;

    /**
     * 规格名称
     */
    private String itemSpecName;

    /**
     * 成交价格
     */
    private Integer price;

    /**
     * 购买数量
     */
    private Integer buyCounts;


}
