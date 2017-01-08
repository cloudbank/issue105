/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.util.Date;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * Auditable entity.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public abstract class Auditable {

	/**
	 * Id of the person or a process that originally created the record. Id
	 * (could be corp-id of a person or a NCID or a group/Process id, all are
	 * usually database login ids).
	 */
	@ColumnName("CREATE_ID")
	private String createId;

	/**
	 * Date and Time when the record was originally Created on this table,
	 * usually generated during the data load process.
	 */
	@ColumnName("CREATE_TS")
	private Date createTs;

	/**
	 * Id of the person or a process that originally Updated the record. Id
	 * (could be corp-id of a person or a NCID or a group/Process id, all are
	 * usually database login ids).
	 */
	@ColumnName("UPDATE_ID")
	private String updateId;

	/**
	 * Date and Time when the record was updated on this table, usually
	 * generated during the data load process.
	 */
	@ColumnName("UPDATE_TS")
	private Date updateTs;

	/**
	 * Constructor.
	 */
	protected Auditable() {
		// default empty constructor
	}

	/**
	 * Getter method for property <tt>createId</tt>.
	 * 
	 * @return property value of createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * Setter method for property <tt>createId</tt>.
	 * 
	 * @param createId
	 *            value to be assigned to property createId
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * Getter method for property <tt>createTs</tt>.
	 * 
	 * @return property value of createTs
	 */
	public Date getCreateTs() {
		return createTs;
	}

	/**
	 * Setter method for property <tt>createTs</tt>.
	 * 
	 * @param createTs
	 *            value to be assigned to property createTs
	 */
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	/**
	 * Getter method for property <tt>updateId</tt>.
	 * 
	 * @return property value of updateId
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * Setter method for property <tt>updateId</tt>.
	 * 
	 * @param updateId
	 *            value to be assigned to property updateId
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * Getter method for property <tt>updateTs</tt>.
	 * 
	 * @return property value of updateTs
	 */
	public Date getUpdateTs() {
		return updateTs;
	}

	/**
	 * Setter method for property <tt>updateTs</tt>.
	 * 
	 * @param updateTs
	 *            value to be assigned to property updateTs
	 */
	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	/**
	 * Test whether given object equals this object.
	 * 
	 * @return true if given object equals this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return obj == this || CommonUtility.getHashText(this).equals(CommonUtility.getHashText(obj));
	}

	/**
	 * Get hash code.
	 * 
	 * @return hash code.
	 */
	@Override
	public int hashCode() {
		return CommonUtility.getHashText(this).hashCode();
	}

	/**
	 * Get string representation of this object.
	 * 
	 * @return string representation of this object.
	 */
	@Override
	public String toString() {
		return CommonUtility.toString(this);
	}
}
