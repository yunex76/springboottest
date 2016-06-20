package me.whiteshop.accounts;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Account createAccount( AccountDto.Create dto ) {
		
		/*
		Account account = new Account();
		account.setUsername(dto.getUsername());
		account.setPassword(dto.getPassword());
		
		// modelMapper를 이용해서 축약가능
		*/
		
		/*
		Account account = new Account();
		BeanUtils.copyProperties(dto, account);
		
		// 아래와 동일한 기능
		*/
		
		Account account = modelMapper.map(dto,  Account.class);
		
		Date now = new Date();
		account.setJoined(now);
		account.setUpdated(now);
		
		Account save = repository.save(account);
		return save;
	}
}
