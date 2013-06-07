package fm.weplayfootball.persistence.mapper;

import org.apache.ibatis.annotations.Param;

import fm.weplayfootball.persistence.domain.Member;

public interface MemberMapper {

	Member getByMemail(String memail);
	
	Member getByMsno(int msno);
	
	void insert(Member member);
	
	void update(Member member);
	
	void updatePasswordByEmail(
			@Param("memail") 	String memail, 
			@Param("mpasswd") 	String mpasswd
			);
	
	void updatePassword(
			@Param("msno") 		int 	msno, 
			@Param("mpasswd") 	String 	mpasswd
			);

}


