package com.example.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@TableName("crops")
@Data
public class Select {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private String MarketName;
	private String CropCode;
	private String CropName;
//	@TableField(value="lower_Price")
	private double Lower_Price;
	private double Avg_Price;
	private String TransDate;
	private double Middle_Price;
	private String MarketCode;
	private double Upper_Price;
	private Integer Trans_Quantity;

}
