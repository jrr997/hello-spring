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
 * 菜品管理
 * </p>
 *
 * @author jrr997
 * @since 2024-05-10 09:44:31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("dish")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜品名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜品分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 菜品价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 商品码
     */
    @TableField("code")
    private String code;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 0 停售 1 起售
     */
    @TableField("status")
    private Integer status;

    /**
     * 顺序
     */
    @TableField("sort")
    private Integer sort;

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
