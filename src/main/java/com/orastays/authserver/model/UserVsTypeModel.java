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
public class UserVsTypeModel extends CommonModel {

	private Long userVsTypeId;
	private UserModel userModel;
	private UserTypeModel userTypeModel;
}
