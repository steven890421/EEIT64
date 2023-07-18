package com.example.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mapper.selectMapper;

@RestController
@RequestMapping("/selects")
public class selectController {

		@Resource
		private selectMapper selectMapper;
		
		@GetMapping
		public Object select() {
			return selectMapper.selectList(Wrappers.lambdaQuery());
			
		}
//		@GetMapping("/login")
//		public String login() {
//			return "login";
//			
//		}

}
