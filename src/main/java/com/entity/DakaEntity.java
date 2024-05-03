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
 * 健康码打卡
 *
 * @author 
 * @email
 */
@TableName("daka")
public class DakaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DakaEntity() {

	}

	public DakaEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 健康码打卡
     */
    @ColumnInfo(comment="健康码打卡",type="varchar(200)")
    @TableField(value = "daka_name")

    private String dakaName;


    /**
     * 健康码照片
     */
    @ColumnInfo(comment="健康码照片",type="varchar(200)")
    @TableField(value = "daka_file")

    private String dakaFile;


    /**
     * 体温
     */
    @ColumnInfo(comment="体温",type="decimal(10,2)")
    @TableField(value = "daka_wendu")

    private Double dakaWendu;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "daka_text")

    private String dakaText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "daka_delete")

    private Integer dakaDelete;


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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：健康码打卡
	 */
    public String getDakaName() {
        return dakaName;
    }
    /**
	 * 设置：健康码打卡
	 */

    public void setDakaName(String dakaName) {
        this.dakaName = dakaName;
    }
    /**
	 * 获取：健康码照片
	 */
    public String getDakaFile() {
        return dakaFile;
    }
    /**
	 * 设置：健康码照片
	 */

    public void setDakaFile(String dakaFile) {
        this.dakaFile = dakaFile;
    }
    /**
	 * 获取：体温
	 */
    public Double getDakaWendu() {
        return dakaWendu;
    }
    /**
	 * 设置：体温
	 */

    public void setDakaWendu(Double dakaWendu) {
        this.dakaWendu = dakaWendu;
    }
    /**
	 * 获取：备注
	 */
    public String getDakaText() {
        return dakaText;
    }
    /**
	 * 设置：备注
	 */

    public void setDakaText(String dakaText) {
        this.dakaText = dakaText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getDakaDelete() {
        return dakaDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setDakaDelete(Integer dakaDelete) {
        this.dakaDelete = dakaDelete;
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
        return "Daka{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", dakaName=" + dakaName +
            ", dakaFile=" + dakaFile +
            ", dakaWendu=" + dakaWendu +
            ", dakaText=" + dakaText +
            ", dakaDelete=" + dakaDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
