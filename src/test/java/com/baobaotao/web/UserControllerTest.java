package com.baobaotao.web;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.baobaotao.model.User;
import com.thoughtworks.xstream.io.xml.StaxDriver;

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
	
	@Test
	public void handle51() {
		RestTemplate restTemplate = buildRestTemplate();
		
		User user = new User();
		user.setUserName("zzwind5");
		user.setPassword("123456");
		
		HttpHeaders entityHeader = new HttpHeaders();
		entityHeader.setContentType(MediaType.APPLICATION_XML);
		entityHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, entityHeader);
		ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8080/chapter15/user/handle51.html", 
				HttpMethod.POST, requestEntity, User.class);
		
		User userResp = responseEntity.getBody();
		System.out.println(userResp);
	}
	
	private RestTemplate buildRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
		xmlMarshaller.setStreamDriver(new StaxDriver());
		xmlMarshaller.setAnnotatedClasses(User.class);
		
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
		xmlConverter.setMarshaller(xmlMarshaller);
		xmlConverter.setUnmarshaller(xmlMarshaller);
		
		restTemplate.getMessageConverters().add(xmlConverter);
		
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		restTemplate.getMessageConverters().add(jsonConverter);
		
		return restTemplate;
	}
	
}