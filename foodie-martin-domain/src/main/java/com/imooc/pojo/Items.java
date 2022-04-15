package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.imooc.pojo.supers.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Items extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    private String itemsId;

    /**
     * 商品名称 商品名称
     */
    private String itemName;

    /**
     * 分类外键id 分类id
     */
    private Integer catId;

    /**
     * 一级分类外键id
     */
    private Integer rootCatId;

    /**
     * 累计销售 累计销售
     */
    private Integer sellCounts;

    /**
     * 上下架状态 上下架状态,1:上架 2:下架
     */
    private Integer onOffStatus;

    /**
     * 商品内容 商品内容
     */
    private String content;


    @TableField(exist = false)
    private Integer pageNo;

    @TableField(exist = false)
    private Integer pageSize;

    @TableField(exist = false)
    private String enableTypeMsg;

    @TableField(exist = false)
    private String itemTypeName;

    @TableField(exist = false)
    private String createdAtStr;


}
