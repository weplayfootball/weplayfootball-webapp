package fm.weplayfootball.persistence.mapper;

import org.apache.ibatis.annotations.Param;

import fm.weplayfootball.persistence.domain.MemberAuthCd;

public interface MemberAuthCdMapper {

	MemberAuthCd read(@Param("memail")String memail, @Param("mauthcd")String mauthcd);
	void insert(MemberAuthCd member);
	void update(MemberAuthCd member);

}
