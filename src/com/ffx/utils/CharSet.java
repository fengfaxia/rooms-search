package com.ffx.utils;

import java.io.UnsupportedEncodingException;

public class CharSet {
	public static String getString(String str) {
		String s = "";
		try {
			s = new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
