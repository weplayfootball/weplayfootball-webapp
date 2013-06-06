package fm.weplayfootball.common.utils;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import fm.weplayfootball.common.NotImageFilesException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

	public static BufferedImage cropResize(BufferedImage bufferedImage, int width, int height) {
		BufferedImage out = null;
		try {
			double bufferRatio = (double)bufferedImage.getWidth() / bufferedImage.getHeight();
			double outRatio = (double)width / height;
			if (bufferRatio > outRatio) {
				out = Thumbnails.of(bufferedImage).height(height).asBufferedImage();
				out = Thumbnails.of(out).sourceRegion(Positions.CENTER, width, height).size(width, height).asBufferedImage();
			} else if (bufferRatio < outRatio) {
				out = Thumbnails.of(bufferedImage).width(width).asBufferedImage();
				out = Thumbnails.of(out).sourceRegion(Positions.CENTER, width, height).size(width, height).asBufferedImage();
			} else {
				out = Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, width, height).size(width, height).asBufferedImage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	public static boolean isImageContentType(String ct){
		return "image/gif,image/png,image/jpg,image/jpeg,image/bmp".indexOf(ct) > 0;
	}
	
	public static void save(InputStream is, String directory, String fileName, int width, int height) throws IOException{

		File dir = new File(directory);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
		BufferedImage originalImage = ImageIO.read(is);
		Thumbnails.of(originalImage).size(width, height).toFile(directory+File.separator+fileName);
	}
	
	public static void save(MultipartFile img, String directory, String fileName, int width, int height) throws NotImageFilesException, IOException{
		if(!img.isEmpty() && ImageUtil.isImageContentType(img.getContentType())){
			save(img.getInputStream(), directory, fileName, width, height);
		}else{
			throw new NotImageFilesException();
		}
	}
	
	public static void save(MultipartFile img, String directory, String fileName) throws NotImageFilesException, IllegalStateException, IOException {
		if(img != null && !img.isEmpty() && ImageUtil.isImageContentType(img.getContentType())){
			
			File dir = new File(directory);
	        if(!dir.exists()) {
	            dir.mkdirs();
	        }
	        
		    File file = new File(directory+File.separator+fileName);
		    img.transferTo(file);
		    
		}else{
			throw new NotImageFilesException();
		}
	}
	
	
}
