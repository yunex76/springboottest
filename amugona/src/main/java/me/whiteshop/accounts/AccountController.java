package me.whiteshop.accounts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.whiteshop.commons.ErrorResponse;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring Boot";
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create, BindingResult result) {
		
		if ( result.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage("잘못된 요청입니다.");
			errorResponse.setCode("bad.request");
			// TODO BindingResult 안에 들어있는 에러 정보 사용하기.
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		Account newAccount = service.createAccount(create);
		
		return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
	}

	@ExceptionHandler(UserDuplicatedException.class)
	public ResponseEntity handleUserDuplicatedExcdption(UserDuplicatedException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getUsername() + " 중복 username 입니다!");
		errorResponse.setCode("duplicated.username.exception");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
}
