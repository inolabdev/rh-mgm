package mz.inolabdev.rh.config;

public class MainTest {

	public static void main(String[] args) {

		String fileName = "application/pdf";
		String format = fileName.substring(fileName.indexOf("/")+1);
		System.out.println(format);
	}
}
