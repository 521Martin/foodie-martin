package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类 
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
public class Category {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类类型
     */
    private Integer type;

    /**
     * 父id
     */
    private Integer fatherId;

    /**
     * 图标
     */
    private String logo;

    /**
     * 口号
     */
    private String slogan;

    /**
     * 分类图
     */
    private String catImage;

    /**
     * 背景颜色
     */
    private String bgColor;


}
