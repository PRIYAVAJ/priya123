package com.cjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.UserModel.User;
import com.cjc.exception.EmailInvalidException;
import com.cjc.exception.EnterValidName;
import com.cjc.exception.UserNotCapableForVoting;
import com.cjc.serviceI.UserServiceI;

@RestController
public class UserController
{
	@Autowired
	UserServiceI usi;
	@PostMapping("/regdata")
public ResponseEntity <User> postdata(@RequestBody User us) 
{
	usi.save(us);
	return new ResponseEntity <User>(us,HttpStatus.CREATED);
}
	@GetMapping("/user/{rollno}")
	public ResponseEntity<User> exposeUser(@PathVariable int rollno)
	{
		 User user=usi.getSingleUser(rollno);
		
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@ExceptionHandler(EnterValidName.class)
	public ResponseEntity<String> handleException(EnterValidName en)
	{
		return new ResponseEntity<String>(en.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailInvalidException.class)
	public ResponseEntity<String> handleException(EmailInvalidException ee)
	{
		return new ResponseEntity<String>(ee.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotCapableForVoting.class)
	public ResponseEntity<String> handleException(UserNotCapableForVoting uc)
	{
		return new ResponseEntity<String>(uc.getMessage(),HttpStatus.NOT_FOUND);
	}
}
