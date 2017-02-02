package com.agiledge.atom.test;

import java.util.Date;

import com.agiledge.atom.utils.DateUtil;

public class DateTest {

	public static void main(String[] args) {
		String strDate = "17/12/2015";
		System.out.println("String date :: "+strDate);
		Date convDate = DateUtil.convertStringToDate(strDate, "dd/MM/yyyy","yyyy-MM-dd hh:mm:ss");
		System.out.println("convDate = "+convDate);

	}

}
