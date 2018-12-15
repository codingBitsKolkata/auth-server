/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class UserModel extends CommonModel {

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	
	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("isEmailVerified")
	private String isEmailVerified;
	
	@JsonProperty("isMobileVerified")
	private String isMobileVerified;
	
	@JsonProperty("otp")
	private String otp;
	
	@JsonProperty("country")
	private CountryModel countryModel;
	
	@JsonProperty("userVsTypes")
	private List<UserVsTypeModel> userVsTypeModels;
	
	@JsonProperty("userActivitys")
	private List<UserActivityModel> userActivityModels;
	
	@JsonProperty("userVsLanguages")
	private List<UserVsLanguageModel> userVsLanguageModels;
	
	@JsonProperty("userVsInfo")
	private UserVsInfoModel userVsInfoModel;
	
	@JsonProperty("userVsIdentitys")
	private List<UserVsIdentityModel> userVsIdentityModels;
	
	@JsonProperty("hostVsAccount")
	private HostVsAccountModel hostVsAccountModel;
	
	@JsonProperty("loginDetails")
	private List<LoginDetailsModel> loginDetailsModels;
	
	@JsonProperty("hostVsInterests")
	private List<HostVsInterestModel> hostVsInterestModels;
	
	@JsonProperty("hostVsDomains")
	private List<HostVsDomainModel> hostVsDomainModels;

}