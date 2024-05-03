package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YishiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 疑似人员
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yishi")
public class YishiView extends YishiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 疑似类型的值
	*/
	@ColumnInfo(comment="疑似类型的字典表值",type="varchar(200)")
	private String yishiValue;

	//级联表 健康码打卡
					 
		/**
		* 健康码打卡 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer dakaYonghuId;
		/**
		* 健康码打卡
		*/

		@ColumnInfo(comment="健康码打卡",type="varchar(200)")
		private String dakaName;
		/**
		* 健康码照片
		*/

		@ColumnInfo(comment="健康码照片",type="varchar(200)")
		private String dakaFile;
		/**
		* 体温
		*/
		@ColumnInfo(comment="体温",type="decimal(10,2)")
		private Double dakaWendu;
		/**
		* 备注
		*/

		@ColumnInfo(comment="备注",type="text")
		private String dakaText;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer dakaDelete;



	public YishiView() {

	}

	public YishiView(YishiEntity yishiEntity) {
		try {
			BeanUtils.copyProperties(this, yishiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 疑似类型的值
	*/
	public String getYishiValue() {
		return yishiValue;
	}
	/**
	* 设置： 疑似类型的值
	*/
	public void setYishiValue(String yishiValue) {
		this.yishiValue = yishiValue;
	}


	//级联表的get和set 健康码打卡
		/**
		* 获取：健康码打卡 的 用户
		*/
		public Integer getDakaYonghuId() {
			return dakaYonghuId;
		}
		/**
		* 设置：健康码打卡 的 用户
		*/
		public void setDakaYonghuId(Integer dakaYonghuId) {
			this.dakaYonghuId = dakaYonghuId;
		}

		/**
		* 获取： 健康码打卡
		*/
		public String getDakaName() {
			return dakaName;
		}
		/**
		* 设置： 健康码打卡
		*/
		public void setDakaName(String dakaName) {
			this.dakaName = dakaName;
		}

		/**
		* 获取： 健康码照片
		*/
		public String getDakaFile() {
			return dakaFile;
		}
		/**
		* 设置： 健康码照片
		*/
		public void setDakaFile(String dakaFile) {
			this.dakaFile = dakaFile;
		}

		/**
		* 获取： 体温
		*/
		public Double getDakaWendu() {
			return dakaWendu;
		}
		/**
		* 设置： 体温
		*/
		public void setDakaWendu(Double dakaWendu) {
			this.dakaWendu = dakaWendu;
		}

		/**
		* 获取： 备注
		*/
		public String getDakaText() {
			return dakaText;
		}
		/**
		* 设置： 备注
		*/
		public void setDakaText(String dakaText) {
			this.dakaText = dakaText;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getDakaDelete() {
			return dakaDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setDakaDelete(Integer dakaDelete) {
			this.dakaDelete = dakaDelete;
		}


	@Override
	public String toString() {
		return "YishiView{" +
			", yishiValue=" + yishiValue +
			", dakaName=" + dakaName +
			", dakaFile=" + dakaFile +
			", dakaWendu=" + dakaWendu +
			", dakaText=" + dakaText +
			", dakaDelete=" + dakaDelete +
			"} " + super.toString();
	}
}
