package fm.weplayfootball.common.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class AuthCdGenerator {

	public String generateAuthPassword(){
		Random rand = new Random(System.currentTimeMillis());  
		int randomindex =0;
		int r = 0;
		String rr = "";
		String fullpass = "";

		for(randomindex=0;randomindex<12;randomindex++){
			r = 0; 
			rr = ""; 
			r = rand.nextInt(9)+1; //1이상, 9이하
			rr = Integer.toString(r);
			fullpass += rr;
		}

		return fullpass;
	}


}
