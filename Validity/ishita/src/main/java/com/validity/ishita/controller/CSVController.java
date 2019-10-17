package com.validity.ishita.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validity.ishita.algorithm.Levenshtein;




@RestController
public class CSVController {
	
	@RequestMapping("/uploadcsv")
	private String UploadCSV() {
		 String csvFile = "C:\\Users\\ishit\\OneDrive\\Desktop\\validity coding rounf\\Validity";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        List<String> fnameList = new ArrayList<String>();
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	           
	            while ((line = br.readLine()) != null) {

	                String[] normaldata = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	                fnameList.add(normaldata[1]);
	                
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	   
	        
	        Levenshtein levenshtein = new Levenshtein();
	        Set<String> duplicateData = new HashSet<String>();
	        for(int i=1; i< fnameList.size(); i++) {
	        	for (int j = i + 1 ; j < fnameList.size(); j++) { 
	        		
	        		//System.out.println(levenshtein.distance(emailList.get(i), emailList.get(j)));
	    	        	if(levenshtein.distance(fnameList.get(i), fnameList.get(j))<=1.0) {
	    	        		duplicateData.add(fnameList.get(i));
	    	        		
	    	        	}
//	        		if(emailList.get(i).equalsIgnoreCase("Evelin")&& 
//	        				emailList.get(j).equalsIgnoreCase("Emiline")) {
//	        			System.out.println(levenshtein.distance(emailList.get(i), emailList.get(j)));
//	        		}
//	    	        	
	        	}

	        	
	        }
	        for(String s:duplicateData)
	        System.out.println(s);

		return "";
	}
}
