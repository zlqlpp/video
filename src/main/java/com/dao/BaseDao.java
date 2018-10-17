package com.dao;


import java.util.List;
import java.util.Map;

public interface BaseDao {

	boolean insertAndSave();

	List findUser(String userName, String passWd);

	boolean updateGoldReduceAndInsertSpendHistory(String userId,
			String userName, String fileName, String totalGold);


}
