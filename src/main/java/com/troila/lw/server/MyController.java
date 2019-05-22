package com.troila.lw.server;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {

	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person call(HttpServletRequest request) {
		Person p = new Person();
		p.setId(1);
		p.setName("dark");
		p.setMessage(request.getRequestURI().toString()+"=="+request.getLocalAddr()+"--"+request.getLocalPort());
		return p;
	}
}
