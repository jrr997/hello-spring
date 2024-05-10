package com.jrr997.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 10:14:15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("setmeal")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜品分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @TableField("name")
    private String name;

    /**
     * 套餐价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 状态 0:停用 1:启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 修改人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}
