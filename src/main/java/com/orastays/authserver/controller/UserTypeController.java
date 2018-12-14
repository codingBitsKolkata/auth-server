package com.orastays.authserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "User", tags = "User")
public class UserTypeController {

}
