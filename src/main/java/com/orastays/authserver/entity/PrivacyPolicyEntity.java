/**
 * @author Abhideep
 */
package com.orastays.authserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_privacy_policy")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PrivacyPolicyEntity extends CommonEntity {

	private static final long serialVersionUID = -3851296119690535877L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "privacy_policy_id")
	@JsonProperty("privacyPolicyId")
	private Long privacyPolicyId;

	@Column(name = "privacy_policy", length = 65535,columnDefinition="Text")
	@JsonProperty("privacyPolicy")
	private String privacyPolicy;
	
	@Override
	public String toString() {
		return Long.toString(privacyPolicyId);
	}
}
