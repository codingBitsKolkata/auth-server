package com.orastays.authserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_activity")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class UserActivityEntity extends CommonEntity {

	private static final long serialVersionUID = 2323083670888082080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id")
	@JsonProperty("activityId")
	private Long activityId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonProperty("user")
	private UserEntity userEntity;

	@Column(name = "page_visit")
	@JsonProperty("pageVisit")
	private String pageVisit;

	@Column(name = "property_visit")
	@JsonProperty("propertyVisit")
	private String propertyVisit;

	@Override
	public String toString() {
		return Long.toString(activityId);
	}

}
