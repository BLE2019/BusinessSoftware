package com.zyth.web.common.basic;

import java.io.Serializable;

import com.zyth.web.common.constants.StateCodeConstant;

/**
 * ajax json 返回值包装对象
 * @author Tommy
 *
 */
public class JsonResult implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -7073856910135669765L;
	/**
	 * 操作结果（成功或失败）
	 */
	boolean isOk = true;
	/**
	 * 操作状态码
	 */
	String stateCode = StateCodeConstant.SUCCESS_CODE;
	/**
	 * 操作提示信息
	 */
	String message;
	/**
	 * 备注
	 */
	String comment;
	/**
	 * 数据
	 */
	Object data;

	/**
	 * 配合jqDataTable分页用，查询总数
	 */
	int recordsTotal;

	/**
	 * 配合jqDataTable分页用，查询总数
	 */
	int recordsFiltered;


	public boolean isOk() {
		return isOk;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


}
