package main;

public class Test {
	public static void main(String[] args) {
		long abc = 0xabcd;
		
		String str = Long.toHexString(abc);
		
		System.out.println(str);
	}
}
