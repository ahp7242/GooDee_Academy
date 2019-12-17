package com.picaboo.nor.franchisee.vo;

import lombok.Data;

/*
 * 가맹점 VO
 * 가맹점 등록 시 번호, 사업자번호, 이름, 전화번호
 */

@Data
public class UnverifiedFranchisee {
	private String franchiseeNo;
	private String franchiseeCrn;
	private String franchiseeName;
	private String franchiseePhone;
	private String ownerNo;
}
