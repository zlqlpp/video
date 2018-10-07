package com.dao;

import org.apache.log4j.Logger;

/**
 * mysql数据库的分页实现
 * 
 * */
public class MysqlSQLPageHandleImpl implements SQLPageHandle {

	private Logger logger=Logger.getLogger(MysqlSQLPageHandleImpl.class);

	public String handlerPagingSQL(String oldSQL, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer(oldSQL);
		if (pageSize > 0) {
			int firstResult = (pageNo - 1)*pageSize;
			if (firstResult <= 0) {
				sql.append(" limit ").append(pageSize);
			} else {
				sql.append(" limit ").append(firstResult).append(",")
						.append(pageSize);
			}
		}
		return sql.toString();
	}

}
