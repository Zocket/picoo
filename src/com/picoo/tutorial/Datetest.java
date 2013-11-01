package com.picoo.tutorial;

import java.util.Calendar;
import java.util.Date;

public class Datetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now=Calendar.getInstance().getTime();
		System.out.println(now.getTime()/1000);
	}

}
