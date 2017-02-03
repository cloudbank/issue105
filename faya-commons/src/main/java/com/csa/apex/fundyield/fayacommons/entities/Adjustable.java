/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.util.Date;

/**
 * Adjustable entity.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public abstract class Adjustable extends Auditable {

	/**
	 * The id of the user that adjusted the values on this record. The user id
	 * of the previously adjustment will be overwritten for the same reporting
	 * period. Business assumes that the user adjusting the values signs off and
	 * agrees to the previous adjustment or re-adjusts the numbers if not OK.
	 */
	@ColumnName("LAST_ADJ_USER_ID")
	private String lastAdjUserId;

	/**
	 * Date and Time when the record was originally Created on this table,
	 * usually sysdate. The Date time of the previously adjustment will be
	 * overwritten for the same reporting period. Business assumes that the
	 * user adjusting the values signs off and agrees to the previous
	 * adjustment or re-adjusts the numbers if not OK.
	 */
	@ColumnName("LAST_ADJ_TS")
	private Date lastAdjTs;

	/**
	 * Status of the Approval of the adjustment to the values on this record.
	 * valid Values: PendingApproval, approved. Unapproved status is not needed,
	 * as we delete the row if some change is unapproved. The Approval status of
	 * the previously adjustment will be overwritten for the same reporting
	 * period. Business assumes that the user adjusting the values signs off and
	 * agrees to the previous adjustment or re-adjusts the numbers if not OK.
	 */
	@ColumnName("LAST_ADJ_APPROVAL_STATUS_CD")
	private String lastAdjApprovalStatusCd;

	/**
	 * The user id who approved the adjustment. Approval user id of the
	 * previously adjustment will be overwritten for the same reporting period.
	 * Business assumes that the approver signs off and agrees to the previous
	 * adjustment.
	 */
	@ColumnName("LAST_ADJ_APPROVER_USER_ID")
	private String lastAdjApproverUserId;

	/**
	 * Date and Time when the adjustments were approved. Approval Date time of
	 * the previous adjustment will be overwritten for the same reporting
	 * period. Business assumes that the approver signs off and agrees to the
	 * previous adjustment.
	 */
	@ColumnName("LAST_ADJ_APPROVAL_TS")
	private Date lastAdjApprovalTs;

	/**
	 * Constructor.
	 */
	protected Adjustable() {
		// Empty
	}

	/**
	 * Getter method for property <tt>lastAdjUserId</tt>.
	 * 
	 * @return property value of lastAdjUserId
	 */
	public String getLastAdjUserId() {
		return lastAdjUserId;
	}

	/**
	 * Setter method for property <tt>lastAdjUserId</tt>.
	 * 
	 * @param lastAdjUserId
	 *            value to be assigned to property lastAdjUserId
	 */
	public void setLastAdjUserId(String lastAdjUserId) {
		this.lastAdjUserId = lastAdjUserId;
	}

	/**
	 * Getter method for property <tt>lastAdjTs</tt>.
	 * @return property value of lastAdjTs
	 */
	public Date getLastAdjTs() {
		return lastAdjTs;
	}

	/**
	 * Setter method for property <tt>lastAdjTs</tt>.
	 * @param lastAdjTs value to be assigned to property lastAdjTs
	 */
	public void setLastAdjTs(Date lastAdjTs) {
		this.lastAdjTs = lastAdjTs;
	}

	/**
	 * Getter method for property <tt>lastAdjApprovalStatusCd</tt>.
	 * 
	 * @return property value of lastAdjApprovalStatusCd
	 */
	public String getLastAdjApprovalStatusCd() {
		return lastAdjApprovalStatusCd;
	}

	/**
	 * Setter method for property <tt>lastAdjApprovalStatusCd</tt>.
	 * 
	 * @param lastAdjApprovalStatusCd
	 *            value to be assigned to property lastAdjApprovalStatusCd
	 */
	public void setLastAdjApprovalStatusCd(String lastAdjApprovalStatusCd) {
		this.lastAdjApprovalStatusCd = lastAdjApprovalStatusCd;
	}

	/**
	 * Getter method for property <tt>lastAdjApproverUserId</tt>.
	 * 
	 * @return property value of lastAdjApproverUserId
	 */
	public String getLastAdjApproverUserId() {
		return lastAdjApproverUserId;
	}

	/**
	 * Setter method for property <tt>lastAdjApproverUserId</tt>.
	 * 
	 * @param lastAdjApproverUserId
	 *            value to be assigned to property lastAdjApproverUserId
	 */
	public void setLastAdjApproverUserId(String lastAdjApproverUserId) {
		this.lastAdjApproverUserId = lastAdjApproverUserId;
	}

	/**
	 * Getter method for property <tt>lastAdjApprovalTs</tt>.
	 * 
	 * @return property value of lastAdjApprovalTs
	 */
	public Date getLastAdjApprovalTs() {
		return lastAdjApprovalTs;
	}

	/**
	 * Setter method for property <tt>lastAdjApprovalTs</tt>.
	 * 
	 * @param lastAdjApprovalTs
	 *            value to be assigned to property lastAdjApprovalTs
	 */
	public void setLastAdjApprovalTs(Date lastAdjApprovalTs) {
		this.lastAdjApprovalTs = lastAdjApprovalTs;
	}

}
