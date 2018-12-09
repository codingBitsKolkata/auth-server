/**
 * @author Abhideep
 */
package com.orastays.authserver.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class DomainModel extends CommonModel {

	private String DomainId;
	private String DomainName;
	private List<HostVsDomainModel> hostVsDomainModels;

}