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
public class UserVsInfoModel extends CommonModel {
	
	private String userVsInfoId;
	private UserModel userModel;
	private String name;
	private String imageUrl;
	private String altPhno;
	private String bioInfo;
	private String dob;
	private String companyName;
	private String location;
	private String noShowCount;
}
