package com.bs.firstboot.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.firstboot.model.dto.Member;
import com.bs.firstboot.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
	
	private final MemberService service;
	
	
	@GetMapping
	public ResponseEntity<List<Member>> selectMemberAll(){
		List<Member> list=service.selectMemberAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Member> selectMemberById(
				@PathVariable String id){
		Member m=service.selectById(id);
//		return ResponseEntity.ok(m);
		return ResponseEntity.status(HttpStatus.OK).body(m);
	}
	/* HttpStatus 
	 * CREATED : 정상처리 및 자원을 생성
	 * OK : 요청이 성공적으로 처리됐을때
	 * BAD_REQUEST : 요청값이 잘못되어 요청에 실패했을때
	 * FORBIDDEN : 권한이 없는 요청을 했을때
	 * NOT_FOUND : 서비스를 찾을 수 없을때 
	 * */
	
	
	@PostMapping
	public ResponseEntity<Integer> insertMember(@RequestBody Member member) {
		int result=service.insertMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable String id,
			@RequestBody Member member) {
		try {
			int result=service.updateMember(member);
			return ResponseEntity.ok(member);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMember(@PathVariable String id) {
		int result=service.deleteMember(id);
		return ResponseEntity.ok().build();
	}
	
	
}
