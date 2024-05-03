package com.entity.vo;

import com.entity.FengkongEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 隔离信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fengkong")
public class FengkongVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 地区名称
     */

    @TableField(value = "fengkong_name")
    private String fengkongName;


    /**
     * 地区照片
     */

    @TableField(value = "fengkong_photo")
    private String fengkongPhoto;


    /**
     * 地区
     */

    @TableField(value = "fengkong_didian_types")
    private Integer fengkongDidianTypes;


    /**
     * 风险类型
     */

    @TableField(value = "fengkong_types")
    private Integer fengkongTypes;


    /**
     * 地区介绍
     */

    @TableField(value = "fengkong_content")
    private String fengkongContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "fengkong_delete")
    private Integer fengkongDelete;


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
	 * 设置：地区名称
	 */
    public String getFengkongName() {
        return fengkongName;
    }


    /**
	 * 获取：地区名称
	 */

    public void setFengkongName(String fengkongName) {
        this.fengkongName = fengkongName;
    }
    /**
	 * 设置：地区照片
	 */
    public String getFengkongPhoto() {
        return fengkongPhoto;
    }


    /**
	 * 获取：地区照片
	 */

    public void setFengkongPhoto(String fengkongPhoto) {
        this.fengkongPhoto = fengkongPhoto;
    }
    /**
	 * 设置：地区
	 */
    public Integer getFengkongDidianTypes() {
        return fengkongDidianTypes;
    }


    /**
	 * 获取：地区
	 */

    public void setFengkongDidianTypes(Integer fengkongDidianTypes) {
        this.fengkongDidianTypes = fengkongDidianTypes;
    }
    /**
	 * 设置：风险类型
	 */
    public Integer getFengkongTypes() {
        return fengkongTypes;
    }


    /**
	 * 获取：风险类型
	 */

    public void setFengkongTypes(Integer fengkongTypes) {
        this.fengkongTypes = fengkongTypes;
    }
    /**
	 * 设置：地区介绍
	 */
    public String getFengkongContent() {
        return fengkongContent;
    }


    /**
	 * 获取：地区介绍
	 */

    public void setFengkongContent(String fengkongContent) {
        this.fengkongContent = fengkongContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getFengkongDelete() {
        return fengkongDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setFengkongDelete(Integer fengkongDelete) {
        this.fengkongDelete = fengkongDelete;
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
