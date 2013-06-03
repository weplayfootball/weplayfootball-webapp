package fm.weplayfootball.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fm.weplayfootball.persistence.domain.ClubInfo;
import fm.weplayfootball.persistence.domain.ClubInfoList;
import fm.weplayfootball.persistence.domain.common.SearchCondition;

public interface ClubInfoMapper {

	int getCsno();
	
	ClubInfo getByCname(@Param("cname") String cname);
	
	ClubInfo getByCsno(@Param("csno") int csno);

	List<ClubInfoList> listClubInfo(SearchCondition cond);

	void insert(ClubInfo clubInfo);

}
