package com.example.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.MemberDAO;
import com.example.util.JwtToken;

@RestController
@RequestMapping("/login")
public class restController {
	
	@Autowired
	MemberDAO memberDao;
	
	
	@PostMapping
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		
	
		System.out.println(email);
		System.out.println(password);

		if (memberDao.checkMemberExists(email, password)) {
			String name = memberDao.getMemberNameByEmail(email);
			System.out.println(name);
			
			JwtToken jwtToken = new JwtToken();
		    HashMap<String, String> user = new HashMap<>();
		    user.put("userName", name);
		    String token = jwtToken.generateToken(user); // 生成 Token	
		    JSONObject responseJson = new JSONObject();
	        // 将 Token 字符串添加到 JSON 对象中
	        responseJson.put("token", token);

	        // 返回 JSON 字符串
	        return responseJson.toString();
		} else {
			
//			memberDao.closeSessionFactory();
			System.out.println("XX");
			return "login";
		}
	}

}
