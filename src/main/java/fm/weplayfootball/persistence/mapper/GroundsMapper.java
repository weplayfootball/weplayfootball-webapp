package fm.weplayfootball.persistence.mapper;

import java.util.List;

import fm.weplayfootball.persistence.domain.Grounds;


public interface GroundsMapper {

	List<String> listGlocaldo();
	List<String> listGlocalsi(String glocaldo);
	List<Grounds> listGname(String glocaldo, String glocalsi);

}
