package com.entity.model;

import com.entity.YishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 疑似人员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YishiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 打卡
     */
    private Integer dakaId;


    /**
     * 疑似名称
     */
    private String yishiName;


    /**
     * 疑似照片
     */
    private String yishiPhoto;


    /**
     * 疑似类型
     */
    private Integer yishiTypes;


    /**
     * 疑似介绍
     */
    private String yishiContent;


    /**
     * 逻辑删除
     */
    private Integer yishiDelete;


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
