package com.entity.vo;

import com.entity.DakaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 健康码打卡
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("daka")
public class DakaVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 健康码打卡
     */

    @TableField(value = "daka_name")
    private String dakaName;


    /**
     * 健康码照片
     */

    @TableField(value = "daka_file")
    private String dakaFile;


    /**
     * 体温
     */

    @TableField(value = "daka_wendu")
    private Double dakaWendu;


    /**
     * 备注
     */

    @TableField(value = "daka_text")
    private String dakaText;


    /**
     * 逻辑删除
     */

    @TableField(value = "daka_delete")
    private Integer dakaDelete;


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
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：健康码打卡
	 */
    public String getDakaName() {
        return dakaName;
    }


    /**
	 * 获取：健康码打卡
	 */

    public void setDakaName(String dakaName) {
        this.dakaName = dakaName;
    }
    /**
	 * 设置：健康码照片
	 */
    public String getDakaFile() {
        return dakaFile;
    }


    /**
	 * 获取：健康码照片
	 */

    public void setDakaFile(String dakaFile) {
        this.dakaFile = dakaFile;
    }
    /**
	 * 设置：体温
	 */
    public Double getDakaWendu() {
        return dakaWendu;
    }


    /**
	 * 获取：体温
	 */

    public void setDakaWendu(Double dakaWendu) {
        this.dakaWendu = dakaWendu;
    }
    /**
	 * 设置：备注
	 */
    public String getDakaText() {
        return dakaText;
    }


    /**
	 * 获取：备注
	 */

    public void setDakaText(String dakaText) {
        this.dakaText = dakaText;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getDakaDelete() {
        return dakaDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setDakaDelete(Integer dakaDelete) {
        this.dakaDelete = dakaDelete;
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
