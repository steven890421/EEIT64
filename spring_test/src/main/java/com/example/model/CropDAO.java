package com.example.model;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
@Component
public class CropDAO {

    private SessionFactory sessionFactory;

    public CropDAO() {
        // 初始化 Hibernate SessionFactory
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void saveCrop(Crop crop) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(crop);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void cleanTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // 删除表
            String dropQuery = "DROP TABLE IF EXISTS crops";
            session.createNativeQuery(dropQuery).executeUpdate();

            // 重新创建表
            String createQuery = "CREATE TABLE `crops` (\r\n"
            		+ "  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n"
            		+ "  `MarketName` varchar(255) DEFAULT NULL,\r\n"
            		+ "  `CropCode` varchar(255) DEFAULT NULL,\r\n"
            		+ "  `CropName` varchar(255) DEFAULT NULL,\r\n"
            		+ "  `TransDate` varchar(255) DEFAULT NULL,\r\n"
            		+ "  `Lower_Price` double DEFAULT NULL,\r\n"
            		+ "  `Avg_Price` double DEFAULT NULL,\r\n"
            		+ "  `Middle_Price` double DEFAULT NULL,\r\n"
            		+ "  `MarketCode` varchar(255) DEFAULT NULL,\r\n"
            		+ "  `Upper_Price` double DEFAULT NULL,\r\n"
            		+ "  `Trans_Quantity` int(11) DEFAULT NULL,\r\n"
            		+ "  PRIMARY KEY (`id`)\r\n"
            		+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            session.createNativeQuery(createQuery).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Crop> getAllCrop() {
        
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        List<Crop> crops = session.createQuery("FROM Crop", Crop.class).list();

        session.getTransaction().commit();

   
        session.close();
        

        return crops;
    }
    public void closeSessionFactory() {
        // 关闭 SessionFactory
        sessionFactory.close();
    }
    
    public String toJson(List<Crop> crops) {
    	JSONArray jsonArray = new JSONArray();

		for (Crop crop : crops) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("MarketName", crop.getMarketName());
			jsonObject.put("CropCode", crop.getCropCode());
			jsonObject.put("CropName", crop.getCropName());
			jsonObject.put("TransDate", crop.getTransDate());
			jsonObject.put("MarketCode", crop.getMarketCode());
			jsonObject.put("Upper_Price", crop.getUpper_Price());
			jsonObject.put("Middle_Price", crop.getMiddle_Price());
			jsonObject.put("Lower_Price", crop.getLower_Price());
			jsonObject.put("Trans_Quantity", crop.getTrans_Quantity());
			jsonObject.put("Avg_Price", crop.getAvg_Price());

			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
		
    }
}
