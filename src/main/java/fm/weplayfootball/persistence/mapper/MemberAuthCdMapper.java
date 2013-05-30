package fm.weplayfootball.persistence.mapper;

import fm.weplayfootball.persistence.domain.MemberAuthCd;

public interface MemberAuthCdMapper {

	MemberAuthCd read(String memail, String mauthcd);
	void insert(MemberAuthCd member);
	void update(MemberAuthCd member);

}
