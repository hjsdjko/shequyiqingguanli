package com.entity.vo;

import com.entity.YishiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 疑似人员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yishi")
public class YishiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 打卡
     */

    @TableField(value = "daka_id")
    private Integer dakaId;


    /**
     * 疑似名称
     */

    @TableField(value = "yishi_name")
    private String yishiName;


    /**
     * 疑似照片
     */

    @TableField(value = "yishi_photo")
    private String yishiPhoto;


    /**
     * 疑似类型
     */

    @TableField(value = "yishi_types")
    private Integer yishiTypes;


    /**
     * 疑似介绍
     */

    @TableField(value = "yishi_content")
    private String yishiContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "yishi_delete")
    private Integer yishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：打卡
	 */
    public Integer getDakaId() {
        return dakaId;
    }


    /**
	 * 获取：打卡
	 */

    public void setDakaId(Integer dakaId) {
        this.dakaId = dakaId;
    }
    /**
	 * 设置：疑似名称
	 */
    public String getYishiName() {
        return yishiName;
    }


    /**
	 * 获取：疑似名称
	 */

    public void setYishiName(String yishiName) {
        this.yishiName = yishiName;
    }
    /**
	 * 设置：疑似照片
	 */
    public String getYishiPhoto() {
        return yishiPhoto;
    }


    /**
	 * 获取：疑似照片
	 */

    public void setYishiPhoto(String yishiPhoto) {
        this.yishiPhoto = yishiPhoto;
    }
    /**
	 * 设置：疑似类型
	 */
    public Integer getYishiTypes() {
        return yishiTypes;
    }


    /**
	 * 获取：疑似类型
	 */

    public void setYishiTypes(Integer yishiTypes) {
        this.yishiTypes = yishiTypes;
    }
    /**
	 * 设置：疑似介绍
	 */
    public String getYishiContent() {
        return yishiContent;
    }


    /**
	 * 获取：疑似介绍
	 */

    public void setYishiContent(String yishiContent) {
        this.yishiContent = yishiContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYishiDelete() {
        return yishiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYishiDelete(Integer yishiDelete) {
        this.yishiDelete = yishiDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
