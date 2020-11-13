package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.sun.tools.javac.code.Attribute.Array;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		String pathOriginal = "images/original";
		String pathRefactor = "images/refactor";
				
		File directory = new File (pathOriginal);
		
		if(!directory.exists()) {
			
			System.out.println("Directory - \"" + pathOriginal + "\" not found");
			
			return;
		}
		
		File[] lastImages = {};
		
		while(true) {
			
			File[] currentImages = directory.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					
//					String path = pathname.getName().substring(pathname.getName().lastIndexOf(".") + 1);//					
//					if(pathname.isFile() && (path.equalsIgnoreCase("jpg") || path.equalsIgnoreCase("jpeg") || path.equalsIgnoreCase("png")))
					
					if(pathname.isFile() && (pathname.getName().toLowerCase().endsWith(".jpg") || pathname.getName().toLowerCase().contains(".jpeg") || pathname.getName().toLowerCase().contains("png")))
						return true;
					
					return false;
				}
			});
									
			if(currentImages.length == 0)
				System.out.println("Directory - \"" + pathOriginal + "\" is empty");
						
			else if(!Arrays.equals(currentImages, lastImages)) {
				System.out.println("Directory - \"" + pathOriginal + "\" has been changed");
				System.out.println("FILES LIST:");
				
				for(var f : currentImages) {
					System.out.println(">>> " + f);
				}
				
				Arrays.mismatch(currentImages, lastImages);

			}
			
			lastImages = currentImages;
			
			Thread.sleep(10000);
			
		}		

	}
	
	public static void transformerProcessor(String name) {
		BufferedImage image = ImageIO.read(new File())
	}

}
