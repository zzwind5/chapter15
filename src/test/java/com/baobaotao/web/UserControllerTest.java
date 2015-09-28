package com.baobaotao.web;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
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
	
	@Test
	public void handle42() throws IOException {
		RestTemplate rsTemp = new RestTemplate();
		byte[] response = rsTemp.postForObject(
				"http://localhost:8080/chapter15/user/handle42/{imageName}.html", 
				null, byte[].class, "apple");
		Resource fileRes = new FileSystemResource("D://apple_bak.jpg");
		FileCopyUtils.copy(response, fileRes.getFile());
	}
}