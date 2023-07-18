package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name="crops")
public class Crop implements Serializable {
	
	

	public Crop(){
		
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition = "INT default 0")
    private int id;
	
	private String MarketName;
    private String CropCode;
    private String CropName;
    private String TransDate;
    private String MarketCode;
    private double Upper_Price;
    private double Middle_Price;
    private double Lower_Price;
    private int Trans_Quantity;
    private double Avg_Price;
	
    
    
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarketName() {
		return MarketName;
	}
	public void setMarketName(String marketName) {
		MarketName = marketName;
	}
	public String getCropCode() {
		return CropCode;
	}
	public void setCropCode(String cropCode) {
		CropCode = cropCode;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public String getTransDate() {
		return TransDate;
	}
	public void setTransDate(String transDate) {
		TransDate = transDate;
	}
	public String getMarketCode() {
		return MarketCode;
	}
	public void setMarketCode(String marketCode) {
		MarketCode = marketCode;
	}
	public double getUpper_Price() {
		return Upper_Price;
	}
	public void setUpper_Price(double upper_Price) {
		Upper_Price = upper_Price;
	}
	public double getMiddle_Price() {
		return Middle_Price;
	}
	public void setMiddle_Price(double middle_Price) {
		Middle_Price = middle_Price;
	}
	public double getLower_Price() {
		return Lower_Price;
	}
	public void setLower_Price(double lower_Price) {
		Lower_Price = lower_Price;
	}
	public int getTrans_Quantity() {
		return Trans_Quantity;
	}
	public void setTrans_Quantity(int trans_Quantity) {
		Trans_Quantity = trans_Quantity;
	}
	public double getAvg_Price() {
		return Avg_Price;
	}
	public void setAvg_Price(double avg_Price) {
		Avg_Price = avg_Price;
	}
	/**
	 * @param id
	 * @param marketName
	 * @param cropCode
	 * @param cropName
	 * @param transDate
	 * @param marketCode
	 * @param upper_Price
	 * @param middle_Price
	 * @param lower_Price
	 * @param trans_Quantity
	 * @param avg_Price
	 */
	public Crop( String marketName, String cropCode, String cropName, String transDate, String marketCode,
			double upper_Price, double middle_Price, double lower_Price, int trans_Quantity, double avg_Price) {
		super();
		
		MarketName = marketName;
		CropCode = cropCode;
		CropName = cropName;
		TransDate = transDate;
		MarketCode = marketCode;
		Upper_Price = upper_Price;
		Middle_Price = middle_Price;
		Lower_Price = lower_Price;
		Trans_Quantity = trans_Quantity;
		Avg_Price = avg_Price;
	}
	@Override
	public String toString() {
		return "Crop [ MarketName=" + MarketName + ", CropCode=" + CropCode + ", CropName=" + CropName
				+ ", TransDate=" + TransDate + ", MarketCode=" + MarketCode + ", Upper_Price=" + Upper_Price
				+ ", Middle_Price=" + Middle_Price + ", Lower_Price=" + Lower_Price + ", Trans_Quantity="
				+ Trans_Quantity + ", Avg_Price=" + Avg_Price + "]";
	}
	

    
}
    
    
    
    
    