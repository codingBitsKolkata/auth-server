/**
 * @author Krishanu
 */
package com.orastays.authserver.model;

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
public class LoginDetailsModel extends CommonModel {
	
	@JsonProperty("loginId")
	private String loginId;
	
	@JsonProperty("user")
	private UserModel userModel;
	
	@JsonProperty("ip")
	private String ip;
	
	@JsonProperty("deviceId")
	private String deviceId;
	
	@JsonProperty("sessionToken")
	private String sessionToken;
}
