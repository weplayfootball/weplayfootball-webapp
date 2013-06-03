package fm.weplayfootball.persistence.mapper;

import fm.weplayfootball.persistence.domain.Member;

public interface MemberMapper {

	Member getByMemail(String memail);
	Member getByMsno(int msno);
	void insert(Member member);

}


