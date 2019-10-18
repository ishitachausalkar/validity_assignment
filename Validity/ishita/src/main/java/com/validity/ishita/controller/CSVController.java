package com.validity.ishita.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validity.ishita.algorithm.Levenshtein;




@RestController
public class CSVController {
	
	@RequestMapping("/uploadcsv")
	private String UploadCSV() {
		 String csvFile = "C:/Users/ishit/OneDrive/Desktop/validity coding round/Validity/ishita/src/main/resources/normal.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        List<String> fnameList = new ArrayList<String>();
	        Map<String,String[]> fnameDateMap = new HashMap<String,String[]>();
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	           
	            while ((line = br.readLine()) != null) {

	                String[] normaldata = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	                fnameList.add(normaldata[1]);
	                fnameDateMap.put(normaldata[1],normaldata);
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
	        		
	    	        	if(levenshtein.distance(fnameList.get(i), fnameList.get(j))<=1.0) {
	    	        		duplicateData.add(fnameList.get(i));
	    	        		
	    	        	}
	        	
	        	}

	        	
	        }
	        
	        String[] duprow =null;
	        Iterator<String> it = duplicateData.iterator();
	        System.out.println("=======Duplicate Rows===========");
	        while (it.hasNext()) {
				//System.out.println(it.next());
	        	
				System.out.println(java.util.Arrays.toString(fnameDateMap.get(it.next())));
			}
	        
//	    	System.out.println("===========Duplicate Rows:============");
//	        for(String dupfname:duplicateData) {
//	        	System.out.println(fnameDateMap.get(dupfname).toString());
//	        }
	        
	        
	        
//	        for(String s:duplicateData)
//	        System.out.println(s);

		return "";
	}
}
