package io.msa.authserver.controllers;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.msa.authserver.exception.ExceptionErrorMessages;
import io.msa.authserver.exception.ResourceNotFoundException;
import io.msa.authserver.exception.UserAlreadyExistException;
import io.msa.authserver.exception.UsernameNotFoundException;
import io.msa.authserver.model.User;
import io.msa.authserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(value = "USER", description = "OPEATIONS PERTAINING TO USER IN USER DETAILS")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ApiOperation(value = "CREATE A NEW USER DETAILS")
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<Object> userSignUp(@RequestBody final User userDetails) {
		User accDetails = userService.fetchByName(userDetails.getUsername());
		if (accDetails != null && accDetails.getUsername() != null) {
			throw new UserAlreadyExistException(ExceptionErrorMessages.RECORD_ALREADY_EXISTS.getErrMessage());
		} else {
			log.info("Addeed:" + userDetails);
			userDetails.setActivationKey(UUID.randomUUID().toString().replace("-", ""));
			if (userDetails.getActiveFlg() == null)
				userDetails.setActiveFlg("NO");
			User accDetail = userService.saveUser(userDetails);
			return new ResponseEntity<Object>(accDetail, HttpStatus.CREATED);
		}

	}

	@ApiOperation(value = "ACTIVATE USER BASED ON USERNAME AND ACTIVATIONKEY")
	@RequestMapping(value = "/activate/{username}/{activationKey}", method = RequestMethod.GET)
	public ResponseEntity<String> userActivation(@PathVariable String username, @PathVariable String activationKey) {
		log.info("User with Username is --> " + username + " And ActivationKey is --> " + activationKey + "");
		User user = userService.fetchByUnameAndActivationKey(username, activationKey);
		if (null == user) {
			throw new ResourceNotFoundException(ExceptionErrorMessages.INVALID_ACTIVATION_KEY.getErrMessage());
		} else {
			if (user.getId() > 0 && user.getActiveFlg().equals("NO"))
				user.setActiveFlg("YES");
			user.setUpdateDate(new Date());
			userService.savenopwd(user);
			return new ResponseEntity<String>("User account has been activatation is Successfully done ....",
					HttpStatus.OK);
		}
	}

	@ApiOperation(value = " UPDATING A EXISTING A PASSWORD USING AN USERNAME")
	@RequestMapping(value = "/setpassword", method = RequestMethod.POST)
	public ResponseEntity<String> updatePassword(@RequestBody User user) {
		User userDb = userService.fetchByName(user.getUsername());
		String newPassword = "";
		if (user.getPassword().isEmpty())
			;
		else
			newPassword = user.getPassword();
		userDb.setPassword(newPassword);
		userDb.setUpdateDate(new Date());
		userService.savePassword(userDb);
		return new ResponseEntity<String>("Your Password has been Successfully Updated....", HttpStatus.OK);
	}

	@ApiOperation(value = "VIEW A LIST OF AVAILABLE USER DETAILS", response = Iterable.class)
	@RequestMapping(value = "/listofusers", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getAllUserDetails() {
		Iterable<User> userDetails = userService.listOfUserDetails();
		return new ResponseEntity<Iterable<User>>(userDetails, HttpStatus.OK);
	}

	@ApiOperation(value = "SEARCH A USER DETAILS WITH AN ID", response = User.class)
	@RequestMapping(value = "/getuserbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findUserDetails(@PathVariable("id") final Long id) {
		log.info("Fetch UserDetails with id: {}", id);
		User userDetails = userService.fetchById(id);
		if (null == userDetails) {
			throw new ResourceNotFoundException(ExceptionErrorMessages.RECORD_NOT_AVAILABLE.getErrMessage());
		} else {
			return new ResponseEntity<User>(userDetails, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "SEARCH A USER DETAILS WITH AN USERNAME", response = User.class)
	@RequestMapping(value = "/getuserbyname/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> findByUserName(@PathVariable("username") final String username) {
		log.info("Fetch UserDetails with name : {}", username);
		User userDetails = userService.fetchByName(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(ExceptionErrorMessages.USERNAME_NOT_AVAILABLE.getErrMessage());
		} else {
			return new ResponseEntity<User>(userDetails, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "UPDATING A EXISTING USER DETAILS")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUserDetails(@RequestBody User userDetails) {
		User userDetail = userService.fetchById(userDetails.getId());
		if (null == userDetail) {
			throw new ResourceNotFoundException(ExceptionErrorMessages.RECORD_NOT_AVAILABLE.getErrMessage());
		} else {
			userDetail.setId(userDetails.getId());
			userDetail.setUsername(userDetails.getUsername());
			userDetail.setCity(userDetails.getCity());
			userDetail.setState(userDetails.getState());
			userDetail.setUpdateDate(new Date());
			userService.saveUser(userDetail);
			return new ResponseEntity<User>(userDetail, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "DELETE A USER DETAILS BASED ON ID")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteByAccId(@PathVariable("id") final Long id) {
		log.info("User with id " + id + " deleted");
		userService.deleteById(id);
		return new ResponseEntity<String>("User has been Successfully Deleted....", HttpStatus.GONE);
	}

}
