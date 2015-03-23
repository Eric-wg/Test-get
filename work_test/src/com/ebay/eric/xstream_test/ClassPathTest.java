package com.ebay.eric.xstream_test;

public class ClassPathTest {

	public static void main(String[] args) {
		String path = System.getProperty("java.class.path");
		String bootPath = System.getProperty("sun.boot.class.path");
		String extPath = System.getProperty("java.ext.dirs");
		System.out.println(bootPath);
		System.out.println(extPath);
		System.out.println(path);
	}
}
