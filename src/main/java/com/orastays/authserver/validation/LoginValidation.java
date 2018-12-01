package com.orastays.authserver.validation;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
public class LoginValidation extends AuthorizeUserValidation {

}
