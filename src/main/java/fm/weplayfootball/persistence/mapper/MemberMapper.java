package fm.weplayfootball.persistence.mapper;

import fm.weplayfootball.persistence.domain.Member;


public interface MemberMapper {

	Member getMemberByUseremail(String useremail);

	void insertMember(Member member);

}
