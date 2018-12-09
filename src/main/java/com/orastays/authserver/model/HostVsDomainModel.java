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
public class HostVsDomainModel extends CommonModel {

	private String hostDomId;
	private UserModel userModel;
	private DomainModel domainModel;

}