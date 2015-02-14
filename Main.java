package bing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		get precision, query from command line 
		*/
		int newPrecision = getPrecision();
	
	}
	
	public static int getPrecision() throws Exception{
		BingTest bing = new BingTest();
		String content = bing.useAPI();
		
		XmlAnalyser xml = new XmlAnalyser();
		
		List<String> url = xml.parse(content, "d:Url");
		List<String> title = xml.parse(content, "d:Title");
		List<String> summary = xml.parse(content, "d:Description");
		
		// Count the number of relevant entries
		int count = 0;
		
		// If any of the three lists are not of size 10, wrong 
		if (url.size() != 10 || title.size() != 10 || summary.size() != 10){
			System.out.print("wrong info");
		}
		else{
			
			for (int i = 0; i < 10; i ++){
				// Print result i 
				System.out.println("Result "+ (i+1) + "\r\n" + "[\r\nURL: "+ url.get(i) + "\r\n" 
						+ "Title: "+ title.get(i) + "\r\n" + "Summary: "+ summary.get(i) + "\r\n]\r\n");
				if (relevant()){
					count ++;
				}
			}
		}
		return count;
	}
	
	// read from user input and return user's decision
	public static Boolean relevant(){
		System.out.println("Relevant (Y/N)?");
		//  open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		String relevant = null;
 
		//  read the input
		try {
			relevant = br.readLine();
		} catch (IOException ioe) {
			System.exit(1);
		}
		
		
		// For wrong inputs, ask the users to input again
		while (!relevant.equals("Y") && !relevant.equals("y") && !relevant.equals("n") && !relevant.equals("N")){
			System.out.println("Relevant (Y/N)?");
			//  open up standard input
			br = new BufferedReader(new InputStreamReader(System.in));
	 
			//  read the input
			try {
				relevant = br.readLine();
			} catch (IOException ioe) {
				System.exit(1);
			}
			
		}
		
		if (relevant.equals("Y") || relevant.equals("y")){
			return true;
		}
		else{
			return false;
		}
			
	}
	
		
	
}
