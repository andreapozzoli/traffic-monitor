package compilarevie;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

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

import org.apache.log4j.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class GetCoordinates {
		    
	static String address="The White House, Washington DC";

	
	public static void main(String[] args) /*throws BiffException, IOException*/ {
		
		
		
		BasicConfigurator.configure();

//	Workbook wb= Workbook.getWorkbook(new File("C://Users//semmo//Desktop//Progetto 2018-2019//traffic-monitor//java//andrea//vie.xls"));
	//	Sheet sheet = wb.getSheet(0);
		
	//	for (int i=0; i<731; i++) {
	//		Cell cella = sheet.getCell(0,i);
	//		address = cella.getContents();
			Map<String, Double> coords;
	        coords = OpenStreetMapUtils.getInstance().getCoordinates(address);

	        System.out.println("Latitudine :" + coords.get("lat"));
	        System.out.println("Longitudine:" + coords.get("lon"));
			
		}
		
		
		
			      
		    }
		//}
