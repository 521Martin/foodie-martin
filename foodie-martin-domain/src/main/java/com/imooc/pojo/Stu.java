package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Stu extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String name;

    private Integer age;


}
