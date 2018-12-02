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
public class IdentityModel extends CommonModel{
	private Long identityId;
	private String name;
}
