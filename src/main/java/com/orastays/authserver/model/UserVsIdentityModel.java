/**
 * @author Krishanu
 */
package com.orastays.authserver.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserVsIdentityModel extends CommonModel {
	
	private String userVsIdentityId;
	private IdentityModel identityModel;
	private UserModel userModel;
	private String identityNumber;
	private String fileUrl;
	private MultipartFile file;
}
