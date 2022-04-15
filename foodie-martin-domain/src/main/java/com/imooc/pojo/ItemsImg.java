package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品图片 
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemsImg extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品图片id
     */
    private String itemsImgId;

    /**
     * 商品外键id 商品外键id
     */
    private String itemId;

    /**
     * 图片地址 图片地址
     */
    private String url;

    /**
     * 顺序 图片顺序，从小到大
     */
    private Integer sort;

    /**
     * 是否主图 是否主图，1：是，0：否
     */
    private Integer isMain;



}
