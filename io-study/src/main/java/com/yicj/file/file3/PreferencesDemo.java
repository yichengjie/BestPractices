package com.yicj.file.file3;

import java.util.prefs.Preferences;

public class PreferencesDemo {
	
	public static void main(String[] args) throws Exception {
		Preferences prefs = 
		   Preferences.userNodeForPackage(PreferencesDemo.class) ;
		prefs.put("Location", "Oz");
		prefs.put("Footwear", "Ruby Slippers");
		prefs.putInt("Companions", 4);
		prefs.putBoolean("Are there witches?", true);
		int  usageCount = prefs.getInt("UsageCount", 0) ;
		usageCount ++ ;
		prefs.putInt("UsageCount", usageCount);
		for(String key : prefs.keys())
			System.out.println(key + " : " + prefs.get(key, null));
		// you must always provide a default value:
		System.out.println("How many companions does dorothy hava?"
				+ prefs.getInt("Companions", 0));
		
	}

}
