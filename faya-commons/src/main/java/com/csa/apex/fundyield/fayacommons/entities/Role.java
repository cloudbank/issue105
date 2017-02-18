package com.csa.apex.fundyield.fayacommons.entities;

/**
 * The role.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class Role extends Versionable {

	

	/**
	 * Identifies an role by its full legal name plus descriptive information.
	 */
	@ColumnName("APPLICATION_USER_ROLE_DSC")
	private String roleDesc;

	/**
	 * Code that can be used to classify roles based on an internal Company
	 * Internal classification scheme. An role TYPE CODE can be further refined
	 * by a role STRUCTURE TYPE CODE.
	 */
	@ColumnName("APPLICATION_USER_ROLE_CD")
	private RoleTypeCode roleTypeCode;

	/**
	 * Codes that can be used to define the income (interest etc.) rate type
	 * (e.g., fixed, variable, etc) . Also known as coupon Rate in MDM
	 * particularly for bonds. Valid Values are: unknown!
	 * <ul>
	 * <li>F FIXED RATE (NON-ZERO COUPON)</li>
	 * <li>L FLOATING RATE</li>
	 * <li>S STEPPED COUPON</li>
	 * <li>V VARIABLE RATE</li>
	 * <li>Z ZERO COUPON</li>
	 * </ul>
	 */

	/**
	 * Constructor.
	 */
	public Role() {
		// default empty constructor
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public RoleTypeCode getRoleTypeCode() {
		return roleTypeCode;
	}

	public void setRoleTypeCode(RoleTypeCode roleTypeCode) {
		this.roleTypeCode = roleTypeCode;
	}



}
