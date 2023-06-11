package main;

public class Test {
	public static void main(String[] args) {
		String abc = "abcd";

        String cut = abc.substring(1, 1 + 1); // Extract the portion to be cut
        
        System.out.println("Remain: " + abc + "\nCut: " + cut);
	}
}
