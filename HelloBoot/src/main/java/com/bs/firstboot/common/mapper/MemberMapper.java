package com.bs.firstboot.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bs.firstboot.model.dto.Member;

@Mapper
public interface MemberMapper {
	
//	@Results(id="", value= {
//			@Result(property = column= ),
//			@Result(property = column= ),
//			@Result(property = column= ),
//			@Result(property = column= ),
//			@Result(property = column= )
//
//	})
	
	
	
	@Select("SELECT * FROM MEMBER")
	List<Member> selectMemberAll();
	@Select("SELECT * FROM MEMBER WHERE USERID=#{userId}")
	Member selectMemberById(String userId);
	@Insert("INSERT INTO MEMBER "
			+ "	VALUES(#{userId},#{password},#{name},"
			+ "	#{gender},#{age},#{email},#{phone},#{address},NULL,DEFAULT)")
	int insertMember(Member m);
	@Update("UPDATE MEMBER SET USERNAME=#{name}, PASSWORD=#{password},"
			+ "	GENDER=#{gender},AGE=#{age},EMAIL=#{email},PHONE=#{phone},ADDRESS=#{address}\r\n"
			+ "	WHERE USERID=#{userId}")
	int updateMember(Member m);
	@Delete("DELETE FROM MEMBER WHERE USERID=#{userId}")
	int deleteMember(String userId);
	
}
