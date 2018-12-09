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
public class HostVsInterestModel extends CommonModel {

	private String hostIntId;
	private UserModel userModel;
	private InterestModel interestModel;

}