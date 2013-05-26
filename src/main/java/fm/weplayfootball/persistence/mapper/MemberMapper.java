package fm.weplayfootball.persistence.mapper;

import fm.weplayfootball.persistence.domain.Member;


public interface MemberMapper {

	Member read(String memail);
	Member readsno(String msno);
	void insert(Member member);

}
