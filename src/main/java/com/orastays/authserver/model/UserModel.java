/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserModel extends CommonModel {

	private String userId;
	private String name;
	private String mobileNumber;
	private String emailId;
	private String isEmailVerified;
	private String isMobileVerified;
	private String otp;
	private CountryModel countryModel;
	private List<UserVsTypeModel> userVsTypeModels;
	private List<UserActivityModel> UserActivityModels;
	private List<UserVsLanguageModel> UserVsLanguageModels;
	private UserVsInfoModel userVsInfoModel;
	private List<UserVsIdentityModel> UserVsIdentityModels;
	private HostVsAccountModel hostVsAccountModel;
	private List<LoginDetailsModel> loginDetailsModels;

}