package com.ebay.eric.xstream_test;

public class StringTest {
	public static void main(String[] args) {
		String seatInfo = "101,1,1,100|101,1,2,100|101,1,3,100|101,1,4,100|101,1,5,100";
		String [] seatInfoArray = seatInfo.split("\\|");
		for(String seat : seatInfoArray){
			String [] seatDetail =  seat.split(",");
			System.out.println("Section: " + seatDetail[0]); 
			System.out.println("Row: " + seatDetail[1]); 
			System.out.println("SeatNum: " + seatDetail[2]); 
			System.out.println("Price: " + seatDetail[3]); 
		}
	}
}
