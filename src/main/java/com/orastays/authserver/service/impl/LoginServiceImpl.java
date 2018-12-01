/**
 * @author Abhideep
 */
package com.orastays.authserver.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orastays.authserver.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

}
