/**
 * @author Krishanu
 */
package com.orastays.authserver.model;

import org.springframework.web.multipart.MultipartFile;

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
public class UserVsIdentityModel extends CommonModel {

	@JsonProperty("userVsIdentityId")
	private String userVsIdentityId;

	@JsonProperty("identity")
	private IdentityModel identityModel;

	@JsonProperty("user")
	private UserModel userModel;

	@JsonProperty("identityNumber")
	private String identityNumber;

	@JsonProperty("fileUrl")
	private String fileUrl;

	private MultipartFile file;
}
