/**
 * @author Abhideep
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
public class UserTypeModel extends CommonModel {

	private String userTypeId;
	private String userType;
}
