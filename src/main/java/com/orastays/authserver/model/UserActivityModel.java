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
public class UserActivityModel extends CommonModel {
	
	
	@JsonProperty("activityId")
	private String activityId;
	
	@JsonProperty("user")
	private UserModel userModel;
	
	@JsonProperty("pageVisit")
	private String pageVisit;
	
	@JsonProperty("propertyVisit")
	private String propertyVisit;
}
