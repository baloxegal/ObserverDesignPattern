package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		String pathOriginal = "images/original";
		String pathRefactor = "images/refactor";
				
		File directory = new File (pathOriginal);
		
		if(!directory.exists()) {
			
			System.out.println("Directory - \"" + pathOriginal + "\" not found");
			
			return;
		}
		
		File[] currentImages = {};
		File[] lastImages = {};
		File[] differenceDeleteImages = {};
		File[] differenceAddImages = {};
		
		while(true) {
			
			currentImages = directory.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					
//					String path = pathname.getName().substring(pathname.getName().lastIndexOf(".") + 1);//					
//					if(pathname.isFile() && (path.equalsIgnoreCase("jpg") || path.equalsIgnoreCase("jpeg") || path.equalsIgnoreCase("png")))
					
					if(pathname.isFile() && (pathname.getName().toLowerCase().endsWith(".jpg") || pathname.getName().toLowerCase().contains(".jpeg") || pathname.getName().toLowerCase().contains("png")))
						return true;
					
					return false;
				}
			});
						
			if(currentImages.length != 0)
				Arrays.sort(currentImages);
								
			if(!Arrays.equals(currentImages, lastImages)) {
								
				System.out.println("Directory - \"" + pathOriginal + "\" has been changed");
					
				if(currentImages.length == 0) {
					System.out.println("Directory - \"" + pathOriginal + "\" is empty now");
					continue;
				}
					
				System.out.println("FILES LIST:");
					
				for(var f : currentImages) {
						
					if(!Arrays.asList(lastImages).contains(f))
						Arrays.asList(differenceAddImages).add(f);
										
					System.out.println(">>> " + f);
						
				}
				
				for(var f : lastImages) {
					
					if(!Arrays.asList(currentImages).contains(f))
						Arrays.asList(differenceDeleteImages).add(f);
					
				}
												
				lastImages = currentImages;

			}
						
			Thread.sleep(10000);
			
		}		

	}
	
	public static void transformerProcessor(String name) {
		BufferedImage image = ImageIO.read(new File())
	}

}
