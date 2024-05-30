package com.cjc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.UserModel.User;
import com.cjc.exception.EmailInvalidException;
import com.cjc.exception.EnterValidName;
import com.cjc.exception.UserNotCapableForVoting;
import com.cjc.repository.UserRepository;
import com.cjc.serviceI.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	
	@Autowired
	UserRepository ur;
	
	@Override
	public  User save(User us) {
	if(us.getName()!=null && us.getName()!="")
	{
	  if(us.getAge()>=18 && us.getAge()<=80)
		{
			if(us.getEmail().endsWith("@gmail.com"))
			{
				return ur.save(us);
			}else
			{
				throw new EmailInvalidException("please enter valid emailid");
			}
	    } 
		
		    
		else
		{	
			throw new UserNotCapableForVoting("age should be more than 18");
		}
	 }
	
	else
	
	{
		throw new EnterValidName("name should not be null or empty");
	}
	

	}

	@Override
	public User getSingleUser(int rollno) {
	 ur.findById(rollno);
	 return null;
	}

	
}
	




	


