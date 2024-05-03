package com.entity.model;

import com.entity.FengkongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 隔离信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FengkongModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 地区名称
     */
    private String fengkongName;


    /**
     * 地区照片
     */
    private String fengkongPhoto;


    /**
     * 地区
     */
    private Integer fengkongDidianTypes;


    /**
     * 风险类型
     */
    private Integer fengkongTypes;


    /**
     * 地区介绍
     */
    private String fengkongContent;


    /**
     * 逻辑删除
     */
    private Integer fengkongDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
