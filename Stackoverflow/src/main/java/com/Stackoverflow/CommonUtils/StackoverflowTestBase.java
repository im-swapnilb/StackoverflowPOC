package com.Stackoverflow.CommonUtils;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.Stackoverflow.Reports.Log;


public class StackoverflowTestBase {
	static String key;
	
	 public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
	        // Create a list from elements of HashMap 
	        List<Map.Entry<String, Integer> > list = 
	               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
	            public int compare(Map.Entry<String, Integer> o1,  
	                               Map.Entry<String, Integer> o2) 
	            { 
	                return (o1.getValue()).compareTo(o2.getValue()); 
	            } 
	        }); 
	          
	        // put data from sorted list to hashmap  
	        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
	        for (Map.Entry<String, Integer> aa : list) { 
	            temp.put(aa.getKey(), aa.getValue()); 
	        } 
	        return temp; 
	  }

	
	 public static String getKeyByValue(HashMap<String, Integer> sortedMap, int maxNumber) {
		for (Entry<String, Integer> entry : sortedMap.entrySet()) {                     // Created entry set for iteration
		
	            if (entry.getValue()==maxNumber) {
	            	
	            	key = entry.getKey();
	                Log.info("Key is "+ key);
	            }
		}
		return key;
	}
	 
}


