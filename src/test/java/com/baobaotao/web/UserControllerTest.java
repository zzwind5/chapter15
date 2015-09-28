package com.baobaotao.web;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserControllerTest {
	
	@Test
	public void handle41() {
		RestTemplate rsTemp = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("userName", "zzwind5");
		form.add("password", "123456");
		form.add("realName", "Zhang");
		rsTemp.postForLocation("http://localhost:8080/chapter15/user/handle41.html", form);
	}
}