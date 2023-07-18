package com.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Crop;
import com.example.model.CropDAO;
import com.example.entity.Member;
import com.example.model.MemberDAO;
import com.example.util.EmailSender;
import com.example.util.JwtToken;

@Controller

public class ShowController {
	@Autowired
	private MemberDAO memberDao;

	@Autowired
	private CropDAO cropDao;
	@Autowired
	HttpSession httpsession;

	@GetMapping("/")
	public String index(Model model) {
		String token = (String) httpsession.getAttribute("token");
		model.addAttribute("token", token);
		return "index";
	}

	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("photo")  MultipartFile photo   ,  Model model) {
			
			
		
				if(photo != null) {
					
					byte[] photoData;
					try {
						photoData = photo.getBytes();
						Member member = new Member(0, email, password, name, photoData);
						memberDao.saveMember(member);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					
					
					Member member = new Member(0, email, password, name, null);
					memberDao.saveMember(member);
					
				}
			

		return "redirect:login";
	}

	@GetMapping("/register")
	public String register(Model model) {

		return "register";
	}

//	@PostMapping("/login")
//	public String login(HttpServletRequest request, Model model ) {
//		
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		System.out.println(email);
//		System.out.println(password);
//
//		if (memberDao.checkMemberExists(email, password)) {
//			String name = memberDao.getMemberNameByEmail(email);
//			System.out.println(name);
//			
//			JwtToken jwtToken = new JwtToken();
//		    HashMap<String, String> user = new HashMap<>();
//		    user.put("userName", name);
//		    String token = jwtToken.generateToken(user); // 生成 Token		    
//		    httpsession.setAttribute("token", token);		
//			model.addAttribute("token", token);
//
////			memberDao.closeSessionFactory();
//			return token;
//		} else {
//			model.addAttribute("email", email);
////			memberDao.closeSessionFactory();
//			System.out.println("XX");
//			return "login";
//		}
//	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("email", "");
		return "login";

	}

	@GetMapping("/allMember")
	public String allMember(Model model) {
		
		List<Member> members = memberDao.getAllMembers();
		memberDao.closeSessionFactory();
		model.addAttribute("members", members);

		return "allMember";
	}

	@GetMapping("/select")
	public String select(Model model) {
		
		List<Crop> crops = cropDao.getAllCrop();

//		cropDao.closeSessionFactory();
		String jsonStr = cropDao.toJson(crops);
		

		model.addAttribute("crops", jsonStr);

		return "select";
	}
	@GetMapping("/googleLogin")
	public String googleLogin() {
		return "googleLogin";
	}
	

	
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	@PostMapping("/test")
	public String test(@RequestParam("name") String name , Model model) {
		System.out.println("test "+name);
	    JwtToken jwtToken = new JwtToken();
	    HashMap<String, String> user = new HashMap<>();
	    user.put("userName", name);
	    String token = jwtToken.generateToken(user); // 生成 Token
	    model.addAttribute("token", token);

	    return "test";
		}
	@GetMapping("/test1")
	public String test1() {
		return "test1";
	}
	@PostMapping("/test1")
	public String hello(@RequestParam("token") String authorizationHeader, Model model) {
	    // 從請求標頭中獲取 Authorization 標頭值（包含 JWT Token）
		System.out.println(authorizationHeader);
	    String token = authorizationHeader; // 去除 "Bearer " 字符串，保留 JWT Token 部分
	    
	    JwtToken jwtToken = new JwtToken();
	    
	    try {
	        // 驗證 JWT Token
	        boolean isValidToken = jwtToken.validateToken(token);
	        System.out.println(isValidToken);
	        
	        if(isValidToken) {
	        	
	        	return "redirect:/test";
	        }else {
	        	
	        	
	        	return "test";
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	        
	        return "test";
	    }
	}
	
	@GetMapping("/forgetPW")
	public String forgetPW() {
		return "forgetPW";
	}
	@PostMapping("/forgetPW")
	public String forgetPW(@RequestParam("email") String email) {
		
		EmailSender emailsender = new EmailSender();
		emailsender.sendMail(email);
		
		
		return"forgetPW";
	}
	@GetMapping("/updatePW")
	public String updatePW() {
		
		return "updatePW";
	}
	@PostMapping("/updatePW")
	public String updatePW(@RequestParam("email") String email, @RequestParam("password") String pass) {
		System.out.println(email);
		System.out.println(pass);
		memberDao.upDatePW(email, pass);
		
		
		return "updatePW";
	}
	
	
	

}
