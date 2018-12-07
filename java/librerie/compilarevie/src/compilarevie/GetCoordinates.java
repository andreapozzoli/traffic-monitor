package compilarevie;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.io.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.*;

import org.apache.log4j.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class GetCoordinates {
		    
	static String address="The White House, Washington DC";

	
	public static void main(String[] args) throws BiffException, IOException {
		
		
		
		BasicConfigurator.configure();

	Workbook wb= Workbook.getWorkbook(new File("C://Users//Matteo//Desktop//prova finale//traffic-monitor//java//andrea//src//vie.xls"));
		Sheet sheet = wb.getSheet(0);
		FileWriter w= new FileWriter("scrittura3.txt");
		 FileWriter y= new FileWriter("scrittura4.txt");
	        BufferedWriter b=new BufferedWriter (w);
	        BufferedWriter s=new BufferedWriter (y);


		
		for (int i=0; i<730; i++) {
			Cell cella = sheet.getCell(0,i);
			address = cella.getContents()+", Como, Italia";
			Map<String, Double> coords;
	        coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
	        System.out.println(address);



	        if (!(coords.get("lat")==(null))) {
	        	b.write(coords.get("lat").toString()+"\r\n");
	        }
	        else {
	        	b.write("nulla\r\n");
	        }
	        b.flush();
			
		}
		for (int i=0; i<730; i++) {
			Cell cella = sheet.getCell(0,i);
			address = cella.getContents()+", Como, Italia";
			Map<String, Double> coords;
	        coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
	        System.out.println(address);
	        

	       
	        if (!(coords.get("lon")==(null))) {
	        	s.write(coords.get("lon").toString()+"\r\n");
	        }
	        else {
	        	s.write("nulla\r\n");
	        }

	        s.flush();
			
		}
		
		
		
		
		
			      
		    }
		}
