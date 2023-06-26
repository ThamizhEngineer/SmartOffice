package com.ss.smartoffice.shared.ImageService;


import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
public class ImageServiceShared {
	
//	 public static void main(String... args) throws IOException {

		    //reduce by 70%

//		        float reduceByPercentHeight = (float) 0.7;
//
//		        float reduceByPercentWidth = (float) 0.7;
//
//		        ImageService rz = new ImageService();
//
//		        rz.resizeImageByPercentage("/Users/dinesh/Downloads/img/img1.jpeg", "/Users/dinesh/Downloads/img/img1-new1.jpeg", reduceByPercentHeight, reduceByPercentWidth);
//
//		    }
	float reduceBytHeight = (float) 0.9;
	float reduceBytWidth = (float) 0.9;
	float fixedHeight = (float) 62;

	public  String findDimensions(String imagePath) {

		String dimensions = null;

		        try {

		    File inputFile = new File(imagePath);

		BufferedImage inputImage = ImageIO.read(inputFile);

		dimensions = inputImage.getHeight()+","+inputImage.getWidth();

		} catch (IOException e) {

			e.printStackTrace();

		}

		   return dimensions;

	}

		public  boolean resizeImageByPercentage(String inputImagePath, String outputImagePath, float imageHeight, float imageWidth) {

		int reduceHeight = (int) (imageHeight-fixedHeight);
		
		int newHeight = (int) (imageHeight-reduceHeight);
		
		int reduceWidth = (int) (imageWidth-fixedHeight);
		
		int newWidth = (int) (imageWidth-reduceWidth);
			
		return  resizeImage( inputImagePath,  outputImagePath,  newHeight,  newWidth);

	}
		public boolean resizeByPercentage(String inputImagePath, String outputImagePath,double percent ) {
			
			String dimensions = findDimensions(inputImagePath);
			
			int height =  Integer.parseInt(dimensions.split(",")[0]);

			int width =   Integer.parseInt(dimensions.split(",")[1]);
			
			int newHeight = (int) (height * (1-percent));
			
			int newWidth = (int) (width * (1-percent));
			
//			int fixedHeightAndWidth = Math.round(fixedHeight);
			
			return  resizeImage( inputImagePath,  outputImagePath,  newHeight,  newWidth);
		
			
		}

		public  boolean resizeImage(String inputImagePath, String outputImagePath, int height, int width) {

		boolean success = false;

		try {

		File input = new File(inputImagePath);

		BufferedImage image = ImageIO.read(input);

		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = resized.createGraphics();

		g2d.drawImage(tmp, 0, 0, null);

		g2d.dispose();

		File output = new File(outputImagePath);

		ImageIO.write(resized, "png", output);

		success = true;

		} catch (Exception e) {

		e.printStackTrace();

		}

		 return success;

		    }
}
