package me.whiteshop;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.whiteshop.accounts.AccountDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmugonaApplication.class)
@WebAppConfiguration
@Transactional
public class AmugonaApplicationTests {

	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void createAccount() throws Exception {
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		AccountDto.Create createDto = new AccountDto.Create();
		createDto.setUsername("whiteship");
		createDto.setPassword("password");
		
		ResultActions result = mockMvc.perform(post("/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDto)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
		
		// Account account = new Account();
		// account.setUsername("홍길동");
		// assertNotNull(account);
		
		// assertTrue(true);
	}

}
