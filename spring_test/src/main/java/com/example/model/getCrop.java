package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.util.GetJson;

public class getCrop {

	public void putCropSQL() {

		try {
			List<Crop> crops = new LinkedList<>();
			int Page = 1;
			GetJson getjson = new GetJson();
			Boolean next = true;
			String Date = getDate();
			while (next) {
				String baseUrl = BuildURL(
						"https://data.coa.gov.tw/api/v1/AgriProductsTransType/"
				        ,Date
				        ,Date
				        ,Page
				        ,"0DXBQUILSRANO84KBQ9JIRJBI9GVSX");
				
				String test = getjson.getHttpJson(baseUrl, 1);
				JSONObject jsonObject = new JSONObject(test);
				next = jsonObject.getBoolean("Next");
				JSONArray dataArray = jsonObject.getJSONArray("Data");
				for (int i = 0; i < dataArray.length(); i++) {
					JSONObject dataObject = dataArray.getJSONObject(i);
					System.out.println(dataObject);
					String MarketName = dataObject.getString("MarketName");
			        String CropCode = dataObject.getString("CropCode");
			        String CropName = dataObject.getString("CropName");
			        String TransDate = dataObject.getString("TransDate");
			        double Lower_Price = dataObject.getDouble("Lower_Price");
			        double Avg_Price = dataObject.getDouble("Avg_Price");
			        double Middle_Price = dataObject.getDouble("Middle_Price");
			        String MarketCode = dataObject.getString("MarketCode");
			        double Upper_Price = dataObject.getDouble("Upper_Price");
			        int Trans_Quantity = dataObject.getInt("Trans_Quantity");					
					Crop crop = new Crop(MarketName, CropCode, CropName, TransDate, MarketCode, Upper_Price, Middle_Price, Lower_Price, Trans_Quantity, Avg_Price);
					crops.add(crop);
				}
				Page++;

			}

			
			CropDAO cropDao = new CropDAO();
			cropDao.cleanTable();
			
			for (Crop crop : crops) {
				cropDao.saveCrop(crop);
			}
			
			System.out.println("OK");
			

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	private static String BuildURL(String baseUrl, String startTime, String endTime, int page, String apiKey) {
		String url = String.format("%s?Start_time=%s&End_time=%s&Page=%d&api_key=%s",
                baseUrl, startTime, endTime, page, apiKey);
		return url;
	}
	public static String getDate() {
	   
	        LocalDate currentDate = LocalDate.now();
	        
	        // 計算民國年份
	        int rocYear = currentDate.getYear() - 1911;
	        
	        // 定義自訂的日期格式
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd");
	        
	        // 格式化日期
	        String formattedDate = rocYear + currentDate.format(formatter).substring(2);
	        
	        return formattedDate;
	    
	}
	
	

}
