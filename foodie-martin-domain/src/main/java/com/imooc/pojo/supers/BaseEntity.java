package com.imooc.pojo.supers;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.imooc.enums.EnableType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseEntity {

    @TableId(value = "id",
            type = IdType.AUTO)
    private Long id;

    /**
     * 0-无效 1-有效
     */
    private EnableType enable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE,update = "now()")
    private LocalDateTime updatedTime;
}
