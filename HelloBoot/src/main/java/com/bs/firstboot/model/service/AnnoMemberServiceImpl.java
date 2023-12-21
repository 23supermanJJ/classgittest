package com.bs.firstboot.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bs.firstboot.common.mapper.MemberMapper;
import com.bs.firstboot.model.dto.Member;

@Primary
@Service
public class AnnoMemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public List<Member> selectMemberAll() {
		// TODO Auto-generated method stub
		return mapper.selectMemberAll();
	}

	@Override
	public Member selectById(String id) {
		// TODO Auto-generated method stub
		return mapper.selectMemberById(id);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return mapper.insertMember(m);
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return mapper.updateMember(m);
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return mapper.deleteMember(userId);
	}
	
	
	
}
