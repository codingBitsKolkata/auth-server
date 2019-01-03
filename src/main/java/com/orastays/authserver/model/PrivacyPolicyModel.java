/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class PrivacyPolicyModel extends CommonModel {

	@JsonProperty("privacyPolicyId")
	private Long privacyPolicyId;

	@JsonProperty("privacyPolicy")
	private String privacyPolicy;
}
