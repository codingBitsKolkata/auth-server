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
public class UserVsLanguageModel extends CommonModel {
	private Long userVsLanguageId;
	private UserModel userModel;
	private LanguageModel masterLanguageModel;
}
