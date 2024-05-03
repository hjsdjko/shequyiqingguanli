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
 * 疑似人员
 *
 * @author 
 * @email
 */
@TableName("yishi")
public class YishiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YishiEntity() {

	}

	public YishiEntity(T t) {
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
     * 打卡
     */
    @ColumnInfo(comment="打卡",type="int(11)")
    @TableField(value = "daka_id")

    private Integer dakaId;


    /**
     * 疑似名称
     */
    @ColumnInfo(comment="疑似名称",type="varchar(200)")
    @TableField(value = "yishi_name")

    private String yishiName;


    /**
     * 疑似照片
     */
    @ColumnInfo(comment="疑似照片",type="varchar(200)")
    @TableField(value = "yishi_photo")

    private String yishiPhoto;


    /**
     * 疑似类型
     */
    @ColumnInfo(comment="疑似类型",type="int(11)")
    @TableField(value = "yishi_types")

    private Integer yishiTypes;


    /**
     * 疑似介绍
     */
    @ColumnInfo(comment="疑似介绍",type="text")
    @TableField(value = "yishi_content")

    private String yishiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yishi_delete")

    private Integer yishiDelete;


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
	 * 获取：打卡
	 */
    public Integer getDakaId() {
        return dakaId;
    }
    /**
	 * 设置：打卡
	 */

    public void setDakaId(Integer dakaId) {
        this.dakaId = dakaId;
    }
    /**
	 * 获取：疑似名称
	 */
    public String getYishiName() {
        return yishiName;
    }
    /**
	 * 设置：疑似名称
	 */

    public void setYishiName(String yishiName) {
        this.yishiName = yishiName;
    }
    /**
	 * 获取：疑似照片
	 */
    public String getYishiPhoto() {
        return yishiPhoto;
    }
    /**
	 * 设置：疑似照片
	 */

    public void setYishiPhoto(String yishiPhoto) {
        this.yishiPhoto = yishiPhoto;
    }
    /**
	 * 获取：疑似类型
	 */
    public Integer getYishiTypes() {
        return yishiTypes;
    }
    /**
	 * 设置：疑似类型
	 */

    public void setYishiTypes(Integer yishiTypes) {
        this.yishiTypes = yishiTypes;
    }
    /**
	 * 获取：疑似介绍
	 */
    public String getYishiContent() {
        return yishiContent;
    }
    /**
	 * 设置：疑似介绍
	 */

    public void setYishiContent(String yishiContent) {
        this.yishiContent = yishiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYishiDelete() {
        return yishiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYishiDelete(Integer yishiDelete) {
        this.yishiDelete = yishiDelete;
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
        return "Yishi{" +
            ", id=" + id +
            ", dakaId=" + dakaId +
            ", yishiName=" + yishiName +
            ", yishiPhoto=" + yishiPhoto +
            ", yishiTypes=" + yishiTypes +
            ", yishiContent=" + yishiContent +
            ", yishiDelete=" + yishiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
