package org.example.wps;

public class MainMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String test ="LineString 45754.005 ,787844.5458 "; 
		System.out.println("test :" + test);
		test = test.replaceAll("[^0-9 .]", "");
		System.out.println("test :" + test);

		
	}

}
