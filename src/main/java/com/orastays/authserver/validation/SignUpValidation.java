package com.orastays.authserver.validation;

import java.security.Principal;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.model.UserModel;

@Component
@Transactional
public class SignUpValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(SignUpValidation.class);
	
	public void validateAddUser(String authorization, Principal user, UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateAddUser -- Start");
		}

		/*Util.printLog(userModel, AuthConstant.INCOMING, "Add User");
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		UserModel adminUserModel = getUserDetails(authorization, user);
		userModel.setCreatedBy(Long.parseLong(adminUserModel.getUserId()));
		userModel.setCreatedDate(Util.getCurrentDateTime());
		userModel.setStatus(1);
		if (Util.isEmpty(userModel.getEmployeeId())) {
			exceptions.put(messageUtil.getBundle("user.employeeId.null.code"), new Exception(messageUtil.getBundle("user.employeeId.null.message")));
		} else {
			if(Objects.nonNull(userService.fetchUserDetails(userModel.getEmployeeId()))) {
				exceptions.put(messageUtil.getBundle("user.alreadypresent.code"), new Exception(messageUtil.getBundle("user.alreadypresent.message")));
			}
		}
		
		List<UserVsRoleModel> userVsRoleModels = userModel.getUserVsRoleModels();
		if (!(Objects.nonNull(userVsRoleModels) && !userVsRoleModels.isEmpty())) {
			exceptions.put(messageUtil.getBundle("role.notpresent.code"), new Exception(messageUtil.getBundle("role.notpresent.message")));
		} else {
			for(int i = 0; i < userVsRoleModels.size(); i++) {
				UserVsRoleModel userVsRoleModel = userVsRoleModels.get(i);
				if(Objects.nonNull(userVsRoleModel)) {
					
					if(Objects.nonNull(userVsRoleModel.getRoleModel())) {
						if(Util.isEmpty(userVsRoleModel.getRoleModel().getRoleId())) {
							exceptions.put(messageUtil.getBundle("role.notpresent.code"), new Exception(messageUtil.getBundle("role.notpresent.message")));
						} else {
							if(Objects.isNull(roleDAO.find(Long.parseLong(userVsRoleModel.getRoleModel().getRoleId())))) {
								exceptions.put(messageUtil.getBundle("role.invalid.code"), new Exception(messageUtil.getBundle("role.invalid.message")));
							}
						}
					} else {
						exceptions.put(messageUtil.getBundle("role.notpresent.code"), new Exception(messageUtil.getBundle("role.notpresent.message")));
					}
					
				} else {
					exceptions.put(messageUtil.getBundle("role.notpresent.code"), new Exception(messageUtil.getBundle("role.notpresent.message")));
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);*/

		if (logger.isDebugEnabled()) {
			logger.debug("validateAddUser -- End");
		}
		
	}
}
