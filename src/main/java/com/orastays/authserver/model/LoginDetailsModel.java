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
public class LoginDetailsModel extends CommonModel {
	
	private String loginId;
	private UserModel userModel;
	private String ip;
	private String deviceId;
	private String sessionToken;
}
