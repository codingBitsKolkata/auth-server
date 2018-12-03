/**
 * @author Krishanu 
 */
package com.orastays.authserver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class HostVsAccountModel extends CommonModel {
	
	private String hostVsAccountId;
	private UserModel userModel;
	private String accountNumber;
	private String accountHolderName;
	private String accountType;
	private String bankName;
	private String branchName;
	private String ifscCode;
}
