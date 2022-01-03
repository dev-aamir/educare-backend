package com.educare.admin.serviceImpl;

public class Demo {

	public static void main(String[] args) {
		Demo d = new Demo();
		System.out.println(d.validateRequestKey("HA$7000RZAL"));

	}

	public boolean validateRequestKey(String key) {
		
		//HA$9000RZSAS
		
		if(key.startsWith("HA")) {
			
			int keyCode = Integer.valueOf(key.substring(3, 7));
			int keyModular = key.substring(key.lastIndexOf('Z')).length();
			
			
			int value = keyCode % keyModular;
			
			if(value == 0) {
				return true;
			}
		}
		
		return false;
	}
}
