package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.FengkongEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 隔离信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("fengkong")
public class FengkongView extends FengkongEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 地区的值
	*/
	@ColumnInfo(comment="地区的字典表值",type="varchar(200)")
	private String fengkongDidianValue;
	/**
	* 风险类型的值
	*/
	@ColumnInfo(comment="风险类型的字典表值",type="varchar(200)")
	private String fengkongValue;




	public FengkongView() {

	}

	public FengkongView(FengkongEntity fengkongEntity) {
		try {
			BeanUtils.copyProperties(this, fengkongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 地区的值
	*/
	public String getFengkongDidianValue() {
		return fengkongDidianValue;
	}
	/**
	* 设置： 地区的值
	*/
	public void setFengkongDidianValue(String fengkongDidianValue) {
		this.fengkongDidianValue = fengkongDidianValue;
	}
	//当前表的
	/**
	* 获取： 风险类型的值
	*/
	public String getFengkongValue() {
		return fengkongValue;
	}
	/**
	* 设置： 风险类型的值
	*/
	public void setFengkongValue(String fengkongValue) {
		this.fengkongValue = fengkongValue;
	}




	@Override
	public String toString() {
		return "FengkongView{" +
			", fengkongDidianValue=" + fengkongDidianValue +
			", fengkongValue=" + fengkongValue +
			"} " + super.toString();
	}
}
