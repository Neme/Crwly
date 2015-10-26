package com.ml.crwly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
//		String url = JOptionPane.showInputDialog("URL:");
		
		FrameTest test = new FrameTest();
		test.setSize(400, 400);
		test.setVisible(true);

		/*
		try{
		System.out.println(getUrlSource(url));
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(null, "Ooops\nIt seems like you're thumb");
		}*/
		
		
		

	}
	
	private static String getUrlSource(String url) throws IOException {
		URL source = new URL(url);
		
		URLConnection sourceC = source.openConnection();
		BufferedReader input = new BufferedReader(new InputStreamReader(sourceC.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuilder a = new StringBuilder();
		while ((inputLine = input.readLine()) != null)
			a.append(inputLine);
		input.close();
		
		return a.toString();
		
		
	}

}
