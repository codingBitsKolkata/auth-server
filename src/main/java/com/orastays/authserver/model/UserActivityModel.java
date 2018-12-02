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
public class UserActivityModel extends CommonModel {
	private Long activityId;
	private UserModel userModel;
	private String pageVisit;
	private String propertyVisit;
}
