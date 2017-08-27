package com.rummy.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class DataService {
	private static Connection con;

	public DataService() {
	}
	/**
	 * Method to get result set after running SQL query
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet getResultSet(String query) throws SQLException {
		con = DatabaseConnect.getDbCon().con;
		ResultSet rs;

		PreparedStatement st = con.prepareStatement(query);
		rs = st.executeQuery();

		return rs;
	}
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static int runQuery(String query) throws SQLException {
		con = DatabaseConnect.getDbCon().con;

		PreparedStatement st = con.prepareStatement(query);
		int num = st.executeUpdate();
		System.out.println("num  "+num);
		return num;
	}
	/**
	 * Method to get number of columns in a table
	 * @param tableName
	 * @return count of columns
	 */
	public int getColumnCount(String tableName){

		String query = "SELECT * FROM "+tableName;
		int columnCount=0;

		try {
			ResultSet rs = getResultSet(query);
			ResultSetMetaData md = rs.getMetaData();
			columnCount = md.getColumnCount();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return columnCount;
	}
	/**
	 * Method to get column names in a table
	 * @param tableName
	 * @return
	 */
	public String[] getColumnNames(String tableName){
		String query = "SELECT * FROM "+tableName;
		String colName="";
		String[] columnNames=null;
		int columnCount = 0;

		try 
		{
			ResultSet rs = getResultSet(query);
			ResultSetMetaData md = rs.getMetaData();

			columnCount = getColumnCount(tableName);
			columnNames =new String[columnCount];

			for(int i=0;i<columnCount;i++)
			{
				colName = md.getColumnName(i+1);
				columnNames[i]=colName;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return columnNames;
	}
	/**
	 * Method to count numbers of records returned by a query 
	 * @param query
	 * @return
	 */
	public static int getRowCount(String query){
		int size = 0;
		try 
		{
			ResultSet resultSet = DataService.getResultSet(query);
			resultSet.last();
			size = resultSet.getRow();
			resultSet.beforeFirst();
		}
		catch(Exception ex) {
			return 0;
		}
		
		return size;
	}
}
