package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 隔离信息
 *
 * @author 
 * @email
 */
@TableName("fengkong")
public class FengkongEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FengkongEntity() {

	}

	public FengkongEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 地区名称
     */
    @ColumnInfo(comment="地区名称",type="varchar(200)")
    @TableField(value = "fengkong_name")

    private String fengkongName;


    /**
     * 地区照片
     */
    @ColumnInfo(comment="地区照片",type="varchar(200)")
    @TableField(value = "fengkong_photo")

    private String fengkongPhoto;


    /**
     * 地区
     */
    @ColumnInfo(comment="地区",type="int(11)")
    @TableField(value = "fengkong_didian_types")

    private Integer fengkongDidianTypes;


    /**
     * 风险类型
     */
    @ColumnInfo(comment="风险类型",type="int(11)")
    @TableField(value = "fengkong_types")

    private Integer fengkongTypes;


    /**
     * 地区介绍
     */
    @ColumnInfo(comment="地区介绍",type="text")
    @TableField(value = "fengkong_content")

    private String fengkongContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "fengkong_delete")

    private Integer fengkongDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：地区名称
	 */
    public String getFengkongName() {
        return fengkongName;
    }
    /**
	 * 设置：地区名称
	 */

    public void setFengkongName(String fengkongName) {
        this.fengkongName = fengkongName;
    }
    /**
	 * 获取：地区照片
	 */
    public String getFengkongPhoto() {
        return fengkongPhoto;
    }
    /**
	 * 设置：地区照片
	 */

    public void setFengkongPhoto(String fengkongPhoto) {
        this.fengkongPhoto = fengkongPhoto;
    }
    /**
	 * 获取：地区
	 */
    public Integer getFengkongDidianTypes() {
        return fengkongDidianTypes;
    }
    /**
	 * 设置：地区
	 */

    public void setFengkongDidianTypes(Integer fengkongDidianTypes) {
        this.fengkongDidianTypes = fengkongDidianTypes;
    }
    /**
	 * 获取：风险类型
	 */
    public Integer getFengkongTypes() {
        return fengkongTypes;
    }
    /**
	 * 设置：风险类型
	 */

    public void setFengkongTypes(Integer fengkongTypes) {
        this.fengkongTypes = fengkongTypes;
    }
    /**
	 * 获取：地区介绍
	 */
    public String getFengkongContent() {
        return fengkongContent;
    }
    /**
	 * 设置：地区介绍
	 */

    public void setFengkongContent(String fengkongContent) {
        this.fengkongContent = fengkongContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getFengkongDelete() {
        return fengkongDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setFengkongDelete(Integer fengkongDelete) {
        this.fengkongDelete = fengkongDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fengkong{" +
            ", id=" + id +
            ", fengkongName=" + fengkongName +
            ", fengkongPhoto=" + fengkongPhoto +
            ", fengkongDidianTypes=" + fengkongDidianTypes +
            ", fengkongTypes=" + fengkongTypes +
            ", fengkongContent=" + fengkongContent +
            ", fengkongDelete=" + fengkongDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
