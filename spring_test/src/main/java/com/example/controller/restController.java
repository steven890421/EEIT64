package com.example.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Member;
import com.example.model.MemberDAO;
import com.example.util.JwtToken;

@RestController
@RequestMapping
public class restController {

	@Autowired
	MemberDAO memberDao;

	@PostMapping("/login")
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

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("photo") MultipartFile photo) {
		
	
		if (memberDao.checkMemberExists(email, password)) {
			return ResponseEntity.badRequest().body("用戶已存在"); // 回傳錯誤訊息
		} else {
			if (photo != null) {
				try {
					byte[] photoData = photo.getBytes();
					Member member = new Member(0, email, password, name, photoData);
					memberDao.saveMember(member);
				} catch (IOException e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("伺服器錯誤");
				}
			} else {
				Member member = new Member(0, email, password, name, null);
				memberDao.saveMember(member);
			}
		}

		return ResponseEntity.ok().build(); // 成功回應，可自行修改回應內容
	}

}
