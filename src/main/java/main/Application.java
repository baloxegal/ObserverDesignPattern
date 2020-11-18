package main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Application {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		String pathOriginal = "images/original";
		String pathRefactor = "images/refactor";
				
		File directoryOriginal = new File (pathOriginal);
		File directoryRefactor = new File (pathRefactor);
		
		List <String> extensions = new ArrayList<>(Arrays.asList(".jpg", ".jpeg", ".png"));
		
		if(!directoryOriginal.exists()) {
			
			System.out.println("Directory - \"" + pathOriginal + "\" not found");
			
			return;
		}
		
		if(!directoryRefactor.exists()) {
			
			System.out.println("Directory - \"" + pathRefactor + "\" not found");
			
			return;
		}
		
		File[] lastImages = directoryOriginal.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
			
				if(pathname.isFile() && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")).toLowerCase()))
					return true;
				
				return false;
			}
		});
		
		File[] refactorImages = directoryRefactor.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
			
				if(pathname.isFile() && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")).toLowerCase()))
					return true;
				
				return false;
			}
		});;	
		
		if(lastImages.length == 0 && refactorImages.length != 0) {
		
			for(var f : refactorImages) {
				f.delete();
			}
				
			Arrays.asList(refactorImages).clear();
			
		}
		
		if(lastImages.length != 0) {
		
			Arrays.sort(lastImages);
			Arrays.sort(refactorImages);
			
			System.out.println("Directory - \"" + pathOriginal + "\" contains");
			System.out.println("FILES LIST:");
			
			for(var f_1 : lastImages) {
														
				System.out.println(">>> " + f_1);
				
			}
						
			if(!Arrays.stream(refactorImages).map(r -> r.getName()).collect(Collectors.toList()).equals(Arrays.stream(lastImages).map(l -> l.getName()).collect(Collectors.toList()))) {
				
				for(var f : lastImages) {
					if(!Arrays.stream(refactorImages).map(rf -> rf.getName()).collect(Collectors.toList()).contains(f.getName())) {
						
						BufferedImage image = ImageIO.read(f);
						ImageIO.write(image, "jpeg", new File (pathRefactor + "/" + f.getName()));						
					}
				}
								
				for(var f : refactorImages) {
					if(!Arrays.stream(lastImages).map(lf -> lf.getName()).collect(Collectors.toList()).contains(f.getName())) {
						
						f.delete();
					
					}
				}
								
			}
			
		}
				
		File[] currentImages = {};
		List<File> differenceAddImages = new ArrayList<>();
		List<File> differenceDeleteImages = new ArrayList<>();
		
				
		while(true) {
			
			currentImages = directoryOriginal.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {

					if(pathname.isFile() && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")).toLowerCase()))
						return true;
					
					return false;
				}
			});
						
			if(currentImages.length != 0)
				Arrays.sort(currentImages);
								
			if(!Arrays.equals(currentImages, lastImages)) {
								
				System.out.println("Directory - \"" + pathOriginal + "\" has been changed");
					
				if(currentImages.length == 0) {
					
					System.out.println("Directory - \"" + pathOriginal + "\" is empty now, all files have been deleted");
					lastImages = currentImages;
					continue;
				}
					
				System.out.println("FILES LIST:");
					
				for(var f : currentImages) {
						
					if(!Arrays.asList(lastImages).contains(f)) {
						differenceAddImages.add(f);
					}
																			
					System.out.println(">>> " + f);
						
				}
				
				for(var f : lastImages) {
					
					if(!Arrays.asList(currentImages).contains(f)) {
						differenceDeleteImages.add(f);
					}
				}
				
				if(!differenceAddImages.isEmpty()) {
					
					System.out.println("To the directory - \"" + pathOriginal + "\" has been added files: ");
					
					for(var f : differenceAddImages) {
						
						BufferedImage image = ImageIO.read(f);
						ImageIO.write(image, "jpeg", new File (pathRefactor + "/" + f.getName()));						
						
						System.out.println(">>> " + f);
							
					}
					
				}
				
				if(!differenceDeleteImages.isEmpty()) {
					
					System.out.println("From the directory - \"" + pathOriginal + "\" has been deleted files: ");
					
					for(var f : differenceDeleteImages) {
						
						new File (pathRefactor + "/" + f.getName()).delete();
						
						System.out.println(">>> " + f);
							
					}
									
				}
				
				lastImages = currentImages;
				differenceAddImages.clear();
				differenceDeleteImages.clear();
				
				refactorImages = directoryRefactor.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {
					
						if(pathname.isFile() && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")).toLowerCase()))
							return true;
						
						return false;
					}
				});;	
				
			}
						
			Thread.sleep(10000);
			
		}		

	}
	
//	public static void transformerProcessor(String name) {
//		BufferedImage image = ImageIO.read(new File())
//	}

}
