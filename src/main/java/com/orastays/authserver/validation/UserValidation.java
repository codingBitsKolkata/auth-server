package com.orastays.authserver.validation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.orastays.authserver.entity.HostVsDomainEntity;
import com.orastays.authserver.entity.HostVsInterestEntity;
import com.orastays.authserver.entity.LoginDetailsEntity;
import com.orastays.authserver.entity.UserEntity;
import com.orastays.authserver.entity.UserVsInfoEntity;
import com.orastays.authserver.exceptions.FormExceptions;
import com.orastays.authserver.helper.AuthConstant;
import com.orastays.authserver.helper.Status;
import com.orastays.authserver.helper.Util;
import com.orastays.authserver.model.HostVsDomainModel;
import com.orastays.authserver.model.HostVsInterestModel;
import com.orastays.authserver.model.UserActivityModel;
import com.orastays.authserver.model.UserModel;
import com.orastays.authserver.model.UserVsIdentityModel;
import com.orastays.authserver.model.UserVsInfoModel;

@Component
@Transactional
public class UserValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(UserValidation.class);
	private static final Tika TIKA = new Tika();
	
	public UserEntity validateFetchUserByID(String userId) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchUserByID -- Start");
		}

		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		// Validate User Id
		if(StringUtils.isBlank(userId)) {
			exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
		} else {
			userEntity = userDAO.find(Long.parseLong(userId));
			if(Objects.isNull(userEntity)) {
				exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateFetchUserByID -- End");
		}
		
		return userEntity;
	}
	
	public UserEntity validateCheckToken(String userToken) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateCheckToken -- Start");
		}

		UserEntity userEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		LoginDetailsEntity loginDetailsEntity = null;
				
		// Validate User Id
		if(StringUtils.isBlank(userToken)) {
			exceptions.put(messageUtil.getBundle("user.token.null.code"), new Exception(messageUtil.getBundle("user.token.null.message")));
		} else {
			
			try {
				Map<String, String> innerMap1 = new LinkedHashMap<>();
				innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
				innerMap1.put("sessionToken", userToken);
		
				Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
				outerMap1.put("eq", innerMap1);
		
				Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
				alliasMap.put(entitymanagerPackagesToScan+".LoginDetailsEntity", outerMap1);
		
				loginDetailsEntity = loginDetailsDAO.fetchObjectBySubCiteria(alliasMap);
				if(Objects.isNull(loginDetailsEntity)) {
					exceptions.put(messageUtil.getBundle("user.token.invalid.code"), new Exception(messageUtil.getBundle("user.token.invalid.message")));
				} else {
					if(StringUtils.isBlank(loginDetailsEntity.getModifiedDate())) {
						if(Util.getMinuteDiff(loginDetailsEntity.getCreatedDate()) > Integer.parseInt(messageUtil.getBundle("session.timeout"))) {
							exceptions.put(messageUtil.getBundle("session.expires.code"), new Exception(messageUtil.getBundle("session.expires.message")));
						}
					} else {
						if(Util.getMinuteDiff(loginDetailsEntity.getModifiedDate()) > Integer.parseInt(messageUtil.getBundle("session.timeout"))) {
							exceptions.put(messageUtil.getBundle("session.expires.code"), new Exception(messageUtil.getBundle("session.expires.message")));
						}
					}
				}
			} catch (Exception e) {
				exceptions.put(messageUtil.getBundle("user.token.invalid.code"), new Exception(messageUtil.getBundle("user.token.invalid.message")));
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			userEntity = loginDetailsEntity.getUserEntity();
			if(userEntity.getStatus() != Status.ACTIVE.ordinal()) {
				exceptions.put(messageUtil.getBundle("user.inactive.code"), new Exception(messageUtil.getBundle("user.inactive.message")));
				throw new FormExceptions(exceptions);
			}
			loginDetailsEntity.setModifiedBy(userEntity.getUserId());
			loginDetailsEntity.setModifiedDate(Util.getCurrentDateTime());
			loginDetailsDAO.update(loginDetailsEntity);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateCheckToken -- End");
		}
		
		return userEntity;
	}
	
	public UserEntity validateUserActivity(UserActivityModel userActivityModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateUserActivity -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userActivityModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userActivityModel)) {
			
			
			/*// Validate User Id
			if(StringUtils.isBlank(userActivityModel.getPageVisit())) {
				exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
			} else {
				userEntity = userDAO.find(Long.parseLong(userId));
				if(Objects.isNull(userEntity)) {
					exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
				}
			}
			
			// Validate User Id
			if(StringUtils.isBlank(userId)) {
				exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
			} else {
				userEntity = userDAO.find(Long.parseLong(userId));
				if(Objects.isNull(userEntity)) {
					exceptions.put(messageUtil.getBundle("user.id.invalid.code"), new Exception(messageUtil.getBundle("user.id.invalid.message")));
				}
			}*/
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateUserActivity -- End");
		}
		
		return userEntity;
	}
	
	public UserVsInfoEntity validateUserInfo(UserVsInfoModel userVsInfoModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateUserInfo -- Start");
		}

		UserVsInfoEntity userVsInfoEntity = null;
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userVsInfoModel)) {
			
			userVsInfoEntity = this.validateCheckToken(userVsInfoModel.getUserToken()).getUserVsInfoEntity();
			
			// Validate Country Code
			if(Objects.nonNull(userVsInfoModel.getUserModel())) {
				if(Objects.nonNull(userVsInfoModel.getUserModel().getCountryModel())) {
					if(StringUtils.isBlank(userVsInfoModel.getUserModel().getCountryModel().getCountryId())) {
						exceptions.put(messageUtil.getBundle("country.id.null.code"), new Exception(messageUtil.getBundle("country.id.null.message")));
					} else {
						if(Util.isNumeric(userVsInfoModel.getUserModel().getCountryModel().getCountryId())) {
							if(Objects.isNull(countryDAO.find(Long.parseLong(userVsInfoModel.getUserModel().getCountryModel().getCountryId())))) {
								exceptions.put(messageUtil.getBundle("country.id.invalid.code"), new Exception(messageUtil.getBundle("country.id.invalid.message")));
							}
						} else {
							exceptions.put(messageUtil.getBundle("country.id.invalid.code"), new Exception(messageUtil.getBundle("country.id.invalid.message")));
						}
					}
				}
			}
			
			// Validate Alternate Mobile Number of the User
			if(StringUtils.isNotBlank(userVsInfoModel.getAltPhno())) {
				if(!Util.isNumeric(userVsInfoModel.getAltPhno())) {
					exceptions.put(messageUtil.getBundle("user.alt.mobile.invalid.code"), new Exception(messageUtil.getBundle("user.alt.mobile.invalid.message")));
				} else {
					userVsInfoEntity.setAltPhno(userVsInfoModel.getAltPhno());
				}
			}
						
			// Validate User Bio Info
			if(StringUtils.isNotBlank(userVsInfoModel.getBioInfo())) {
				userVsInfoEntity.setBioInfo(userVsInfoModel.getBioInfo());
			}
			
			// Validate DOB of the User
			if(StringUtils.isNotBlank(userVsInfoModel.getDob())) {
				if(Util.checkDate(userVsInfoModel.getDob())) {
					exceptions.put(messageUtil.getBundle("user.dob.invalid.code"), new Exception(messageUtil.getBundle("user.dob.invalid.message")));
				} else {
					userVsInfoEntity.setDob(userVsInfoModel.getDob());
				}
			}
						
			// Validate User Company Name
			if(StringUtils.isNotBlank(userVsInfoModel.getCompanyName())) {
				userVsInfoEntity.setCompanyName(userVsInfoModel.getCompanyName());
			}
			
			// Validate User Location
			if(StringUtils.isNotBlank(userVsInfoModel.getLocation())) {
				userVsInfoEntity.setLocation(userVsInfoModel.getLocation());
			}
			
			//Validate User Image
			if(!(userVsInfoModel.getImage() == null || userVsInfoModel.getImage().isEmpty())) {
				uploadImage(userVsInfoModel.getImage(), userVsInfoEntity);
				
			}
			
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			userVsInfoEntity.setModifiedBy(userVsInfoEntity.getUserEntity().getUserId());
			userVsInfoEntity.setModifiedDate(Util.getCurrentDateTime());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateUserInfo -- End");
		}
		
		return userVsInfoEntity;
	}
	
	private void uploadImage(MultipartFile inputFile, UserVsInfoEntity userVsInfoEntity) throws FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("uploadImage -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		try {
			// to do file upload
			MultipartFile multipartFile = inputFile;
			this.imageFormatValidation(multipartFile);
			/*ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");*/
			String rootPath = System.getProperty("user.dir") + File.separator;
			String dirStr = rootPath + messageUtil.getBundle("logo.upload.foldername");
			File dir = new File(dirStr);
			if(!dir.exists()){
				dir.mkdir();
			}
			
			// construct the complete absolute path of the file
			String fileName = new Date().getTime() +"_"+ multipartFile.getOriginalFilename();
			String fullPath = dirStr + File.separator + fileName;
			
			File file = new File(fullPath);
			InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			
			/*int width          = bImageFromConvert.getWidth();
			int height         = bImageFromConvert.getHeight();*/
			
			//if(width >= Integer.parseInt(messageUtil.getBundle("logo.IMG_WIDTH")) && height >= Integer.parseInt(messageUtil.getBundle("logo.IMG_HEIGHT"))) {
	
				int type = bImageFromConvert.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bImageFromConvert.getType();
				
				BufferedImage resizeImageJpg = Util.resizeImage(bImageFromConvert, type, Integer.parseInt(messageUtil.getBundle("logo.IMG_WIDTH")), Integer.parseInt(messageUtil.getBundle("logo.IMG_HEIGHT")));
	
				
				ImageIO.write(resizeImageJpg, "jpg", file);
				FileInputStream input = new FileInputStream(file);
				multipartFile = new MockMultipartFile(fileName, IOUtils.toByteArray(input));
				/*multipartFile.transferTo(file);*/
				userVsInfoEntity.setImageUrl(azureApp.uploadFile(multipartFile, fileName));
			} catch (Exception e) {
				exceptions.put(messageUtil.getBundle("image.upload.error.code"), new Exception(messageUtil.getBundle("image.upload.error.message")));
				throw new FormExceptions(exceptions);
			}

			
			
		/*} else {
			
			exceptions.put("error", new Exception("Image Dimension Should Be "+messageUtil.getBundle("logo.IMG_WIDTH")+ " x "+messageUtil.getBundle("logo.IMG_HEIGHT")));
		}*/
			
			if (logger.isDebugEnabled()) {
				logger.debug("uploadImage -- End");
			}
	}
	
	private void imageFormatValidation(MultipartFile inputFile) throws FormExceptions, IOException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("imageFormatValidation -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		String imageType = TIKA.detect(inputFile.getBytes());
		if (StringUtils.equals(imageType, "image/jpeg")
				|| StringUtils.equals(imageType, "image/jpg")
				|| StringUtils.equals(imageType, "image/jif")
				|| StringUtils.equals(imageType, "image/png")
				|| StringUtils.equals(imageType, "image/gif")
				|| StringUtils.equals(imageType, "image/bmp")) {

		} else {
			exceptions.put(messageUtil.getBundle("image.format.mismatch.code"), new Exception(messageUtil.getBundle("image.format.mismatch.message")));
			throw new FormExceptions(exceptions);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("imageFormatValidation -- End");
		}
	}
	
	public UserEntity validateUserIdentity(UserVsIdentityModel userVsIdentityModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateUserIdentity -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userVsIdentityModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userVsIdentityModel)) {
			
			// Validate Identity
			if(Objects.nonNull(userVsIdentityModel.getIdentityModel())) {
				if(StringUtils.isBlank(userVsIdentityModel.getIdentityModel().getIdentityId())) {
					exceptions.put(messageUtil.getBundle("identity.id.null.code"), new Exception(messageUtil.getBundle("identity.id.null.message")));
				} else {
					if(Util.isNumeric(userVsIdentityModel.getIdentityModel().getIdentityId())) {
						if(Objects.isNull(identityDAO.find(Long.parseLong(userVsIdentityModel.getIdentityModel().getIdentityId())))) {
							exceptions.put(messageUtil.getBundle("identity.id.invalid.code"), new Exception(messageUtil.getBundle("identity.id.invalid.message")));
						}
					} else {
						exceptions.put(messageUtil.getBundle("identity.id.invalid.code"), new Exception(messageUtil.getBundle("identity.id.invalid.message")));
					}
				}
			}
			
			// Validate Identity Number
			if(StringUtils.isBlank(userVsIdentityModel.getIdentityNumber())) {
				exceptions.put(messageUtil.getBundle("identitynumber.null.code"), new Exception(messageUtil.getBundle("identitynumber.null.message")));
			} else {
				if(Util.checkSpecialCharacter(userVsIdentityModel.getIdentityNumber())) {
					exceptions.put(messageUtil.getBundle("identitynumber.invalid.code"), new Exception(messageUtil.getBundle("identitynumber.invalid.message")));
				}
			}
			
			// Validate File
			if(!(userVsIdentityModel.getFile() == null || userVsIdentityModel.getFile().isEmpty())){
				uploadFile(userVsIdentityModel, userEntity.getUserId());
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateUserIdentity -- End");
		}
		
		return userEntity;
	}
	
	private void uploadFile(UserVsIdentityModel userVsIdentityModel, Long userId) throws FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("uploadFile -- Start");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();	
		try {
			// to do file upload
			MultipartFile multipartFile = userVsIdentityModel.getFile();
			/*ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");*/
			String rootPath = System.getProperty("user.dir") + File.separator;
			String dirStr = rootPath + messageUtil.getBundle("useridentity.upload.foldername");
			
			File dir = new File(dirStr);
			if(!dir.exists()){
				dir.mkdir();
			}
	
			// construct the complete absolute path of the file
			String fileName = userId+"_"+new Date().getTime()+"_" + multipartFile.getOriginalFilename();
			String fullPath = dirStr + File.separator + fileName;
			
			File file = new File(fullPath);
			multipartFile.transferTo(file);
			//fileCopyService.copyFiles(file, messageUtil.getBundle("userfile.upload.foldername"));
			userVsIdentityModel.setFileUrl(fileName);
		} catch (IOException e) {
			exceptions.put(messageUtil.getBundle("userfile.upload.error.code"), new Exception(messageUtil.getBundle("userfile.upload.error.message")));
			throw new FormExceptions(exceptions);
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("uploadFile -- End");
		}
	}
	
	public void validateHostDomain(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateHostDomain -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userModel)) {
			userModel.setUserId(String.valueOf(userEntity.getUserId()));
			// Validate Domain
			if(CollectionUtils.isEmpty(userModel.getHostVsDomainModels())) {
				exceptions.put(messageUtil.getBundle("domain.id.null.code"), new Exception(messageUtil.getBundle("domain.id.null.message")));
			} else {
				for (HostVsDomainModel hostVsDomainModel : userModel.getHostVsDomainModels()) {
					if(Objects.isNull(hostVsDomainModel.getDomainModel())) {
						exceptions.put(messageUtil.getBundle("domain.id.null.code"), new Exception(messageUtil.getBundle("domain.id.null.message")));
					} else {
						if(StringUtils.isBlank(hostVsDomainModel.getDomainModel().getDomainId())) {
							exceptions.put(messageUtil.getBundle("domain.id.null.code"), new Exception(messageUtil.getBundle("domain.id.null.message")));
						} else {
							if(Util.isNumeric(hostVsDomainModel.getDomainModel().getDomainId())) {
								if(Objects.isNull(domainDAO.find(Long.parseLong(hostVsDomainModel.getDomainModel().getDomainId())))) {
									exceptions.put(messageUtil.getBundle("domain.id.invalid.code"), new Exception(messageUtil.getBundle("domain.id.invalid.message")));
								}
							} else {
								exceptions.put(messageUtil.getBundle("domain.id.invalid.code"), new Exception(messageUtil.getBundle("domain.id.invalid.message")));
							}
						}
						if (exceptions.size() > 0)
							throw new FormExceptions(exceptions);
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			for (HostVsDomainModel hostVsDomainModel : userModel.getHostVsDomainModels()) {
				UserModel userModel2 = new UserModel();
				userModel2.setUserId(String.valueOf(userEntity.getUserId()));
				hostVsDomainModel.setUserModel(userModel);
				HostVsDomainEntity hostVsDomainEntity = hostVsDomainConverter.modelToEntity(hostVsDomainModel);
				hostVsDomainEntity.setDomainEntity(domainDAO.find(Long.parseLong(hostVsDomainModel.getDomainModel().getDomainId())));
				hostVsDomainEntity.setUserEntity(userEntity);
				hostVsDomainDAO.save(hostVsDomainEntity);
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateHostDomain -- End");
		}
	}
	
	public void validateHostInterest(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateHostInterest -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userModel)) {
			userModel.setUserId(String.valueOf(userEntity.getUserId()));
			// Validate Interest
			if(CollectionUtils.isEmpty(userModel.getHostVsInterestModels())) {
				exceptions.put(messageUtil.getBundle("interest.id.null.code"), new Exception(messageUtil.getBundle("interest.id.null.message")));
			} else {
				for (HostVsInterestModel hostVsInterestModel : userModel.getHostVsInterestModels()) {
					if(Objects.isNull(hostVsInterestModel.getInterestModel())) {
						exceptions.put(messageUtil.getBundle("interest.id.null.code"), new Exception(messageUtil.getBundle("interest.id.null.message")));
					} else {
						if(StringUtils.isBlank(hostVsInterestModel.getInterestModel().getInterestId())) {
							exceptions.put(messageUtil.getBundle("interest.id.null.code"), new Exception(messageUtil.getBundle("interest.id.null.message")));
						} else {
							if(Util.isNumeric(hostVsInterestModel.getInterestModel().getInterestId())) {
								if(Objects.isNull(interestDAO.find(Long.parseLong(hostVsInterestModel.getInterestModel().getInterestId())))) {
									exceptions.put(messageUtil.getBundle("interest.id.invalid.code"), new Exception(messageUtil.getBundle("interest.id.invalid.message")));
								}
							} else {
								exceptions.put(messageUtil.getBundle("interest.id.invalid.code"), new Exception(messageUtil.getBundle("interest.id.invalid.message")));
							}
						}
						if (exceptions.size() > 0)
							throw new FormExceptions(exceptions);
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		else {
			for (HostVsInterestModel hostVsInterestModel : userModel.getHostVsInterestModels()) {
				UserModel userModel2 = new UserModel();
				userModel2.setUserId(String.valueOf(userEntity.getUserId()));
				hostVsInterestModel.setUserModel(userModel);
				HostVsInterestEntity hostVsInterestEntity = hostVsInterestConverter.modelToEntity(hostVsInterestModel);
				hostVsInterestEntity.setInterestEntity(interestDAO.find(Long.parseLong(hostVsInterestModel.getInterestModel().getInterestId())));
				hostVsInterestEntity.setUserEntity(userEntity);
				hostVsInterestDAO.save(hostVsInterestEntity);
			}
		}
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateHostInterest -- End");
		}
	}
	
	public void validateVerifiedEmailOTP(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateVerifiedEmailOTP -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userModel)) {
			
			if(StringUtils.equals(userEntity.getIsEmailVerified(), AuthConstant.TRUE)) {
				exceptions.put(messageUtil.getBundle("email.verified.code"), new Exception(messageUtil.getBundle("email.verified.message")));
			} else {
				// Validate OTP
				if(StringUtils.isBlank(userModel.getOtp())) {
					exceptions.put(messageUtil.getBundle("otp.null.code"), new Exception(messageUtil.getBundle("otp.null.message")));
				} else {
					
					// Check with Email OTP
					if(!StringUtils.equals(userModel.getOtp(), userEntity.getEmailOTP())) {
						exceptions.put(messageUtil.getBundle("otp.invalid.code"), new Exception(messageUtil.getBundle("otp.invalid.message")));
					} else {
						if(Util.getMinuteDiff(userEntity.getEmailOTPValidity()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
							exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
						} else {
							userEntity.setIsEmailVerified(AuthConstant.TRUE);
							userDAO.update(userEntity);
						}
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateVerifiedEmailOTP -- End");
		}
	}
	
	public void validateVerifiedMobileOTP(UserModel userModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateVerifiedMobileOTP -- Start");
		}

		UserEntity userEntity = this.validateCheckToken(userModel.getUserToken());
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		if(Objects.nonNull(userModel)) {
			
			if(StringUtils.equals(userEntity.getIsEmailVerified(), AuthConstant.TRUE)) {
				exceptions.put(messageUtil.getBundle("mobile.verified.code"), new Exception(messageUtil.getBundle("mobile.verified.message")));
			} else {
				// Validate OTP
				if(StringUtils.isBlank(userModel.getOtp())) {
					exceptions.put(messageUtil.getBundle("otp.null.code"), new Exception(messageUtil.getBundle("otp.null.message")));
				} else {
					
					// Check with Mobile OTP
					if(!StringUtils.equals(userModel.getOtp(), userEntity.getMobileOTP())) {
						exceptions.put(messageUtil.getBundle("otp.invalid.code"), new Exception(messageUtil.getBundle("otp.invalid.message")));
					} else {
						if(Util.getMinuteDiff(userEntity.getMobileOTPValidity()) > Integer.parseInt(messageUtil.getBundle("otp.timeout"))) {
							exceptions.put(messageUtil.getBundle("otp.expires.code"), new Exception(messageUtil.getBundle("otp.expires.message")));
						} else {
							userEntity.setIsMobileVerified(AuthConstant.TRUE);
							userDAO.update(userEntity);
						}
					}
				}
			}
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateVerifiedMobileOTP -- End");
		}
	}
}