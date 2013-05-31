package fm.weplayfootball.web.signup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import fm.weplayfootball.web.signup.domain.SignupForm;

public class ProfileImageUploader {

	public static SignupForm upload(String path, SignupForm form){
		
		MultipartFile atchFile = form.getAtchFile();

		// 파일 업로드 !!!!
		if(atchFile != null && !atchFile.isEmpty()){
			FileOutputStream out = null;
			try {
				byte[] fileByte = atchFile.getBytes();

				String fileName = atchFile.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
				
				File destinationDir = new File(path);
				if (!destinationDir.exists()){
					destinationDir.mkdirs();
				}

				String currTime = Long.toString(System.currentTimeMillis());
				String newFileName = currTime +"-"+form.getMemail()+ fileExt;
				out = new FileOutputStream(destinationDir + File.separator + newFileName);
				out.write(fileByte);

				form.setMimage(newFileName);
				form.setMimagesize(fileByte.length);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		return form;
	}
}
