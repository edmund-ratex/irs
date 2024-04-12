package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
 /**
   *
  *
  *2022.12.25: 
  */
public class TKolPartner {

		@JSONField(name = "id",serialize=true)
		public String id = "";
		@JSONField(name = "user_id",serialize=true)
		public String user_id;
		@JSONField(name = "name",serialize=true)
		public String name;	
		@JSONField(name = "society_role",serialize=true)
		public String society_role;	
		@JSONField(name = "country",serialize=true)
		public String country;	
		@JSONField(name = "main_promotion_areas",serialize=true)
		public String main_promotion_areas;		
		@JSONField(name = "promotion_platform",serialize=true)
		public String promotion_platform;	
		@JSONField(name = "fans_number",serialize=true)
		public String fans_number;		
		@JSONField(name = "contact_information",serialize=true)
		public String contact_information;		
		@JSONField(name = "invite_bd",serialize=true)
		public String invite_bd;	
		@JSONField(name = "remark",serialize=true)
		public String remark;	
		@JSONField(name = "status",serialize=true)
		public String status;		
		@JSONField(name = "create_time",serialize=true)
		public String create_time;	
		@JSONField(name = "update_time",serialize=true)
		public String update_time;		
		@JSONField(name = "kol_time",serialize=true)
		public String kol_time;		
		@JSONField(name = "close_by",serialize=true)
		public String close_by;		
		@JSONField(name = "grade_name",serialize=true)
		public String grade_name;		
		@JSONField(name = "direct_cus_ret_serv",serialize=true)
		public BigDecimal direct_cus_ret_serv;	
		@JSONField(name = "second_com_prop",serialize=true)
		public BigDecimal second_com_prop;		
		@JSONField(name = "invite_bd_per",serialize=true)
		public BigDecimal invite_bd_per;
	    @JSONField(name = "fee_discount_ratio",serialize=true)
		public BigDecimal fee_discount_ratio;
	}