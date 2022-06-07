import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JTable;


	    
	public class DBHelper {
		
		public static Connection conn = null;
		public static MyModel model = null;
		public static PreparedStatement state = null;
		public static ResultSet result = null;
	    private static String dbPath = "";
	    private static String dbDriver = "";
	    private static String dbUsername = "";
	    private static String dbPassword = "";

	 	public static void init() {
	 		Properties properties = new Properties();
	        InputStream inputStream = null;
	        try {
	            inputStream = new FileInputStream("config/config.properties"); //TODO това за сега върши рабора, обаче като е jar?
	            properties.load(inputStream);
	            dbPath = properties.getProperty("db_path");
	            dbDriver = properties.getProperty("db_driver");
	            dbUsername = properties.getProperty("db_username");
	            dbPassword = properties.getProperty("db_password");

	        } catch (FileNotFoundException e) { //
	            try {
	                File file = new File("config/config.properties");
	                file.getParentFile().mkdirs();
	                file.createNewFile();
	                FileOutputStream fileOutputStream = new FileOutputStream(file);
	                properties.setProperty("db_path", "");
	                properties.setProperty("db_driver", "");
	                properties.setProperty("db_username", "");
	                properties.setProperty("db_password", "");
	                properties.store(fileOutputStream, "properties");

	            } catch (IOException exception) {
	                exception.printStackTrace();
	            }
	            e.printStackTrace(); //TODO create config or show error|create default config
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 	}
		
	 	public static Connection getConnection() {
		
			try {
				Class.forName(dbDriver);
				conn = DriverManager.getConnection(dbPath,dbUsername, dbPassword);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}//end getConnection();
	 	
	 	
	 	public static MyModel getAllData(String tableName) {
			
			conn = getConnection();
			try {
				state = conn.prepareStatement("SELECT * FROM " + tableName);
				result = state.executeQuery();
				model = new MyModel(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return model;
		}//end getAllData
	 	
	 	public static MyModel getAllSale() {
			
			conn = getConnection();
			try {
				state = conn.prepareStatement("SELECT SALE_ID, PEOPLE.FNAME,PEOPLE.ROLE, PRODUCT.NAME, SALE.QUANTITY FROM SALE JOIN PEOPLE ON PEOPLE.PEOPLE_ID = SALE.PEOPLE_ID JOIN PRODUCT ON SALE.PRODUCT_ID = PRODUCT.PRODUCT_ID  ");
				result = state.executeQuery();
				model = new MyModel(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return model;
		}//end getAllData
	 	
	 	
	 	
	 	static void fillPeopleCombo(JComboBox<String> combo) {
	 		conn = getConnection();
	 				String sql = "select people_id, fname from people";
	 				try {
	 					state = conn.prepareStatement(sql);
	 					result = state.executeQuery();
	 					combo.removeAllItems(); 
	 					while(result.next()) { 
	 						String item = result.getObject(1).toString() + " " + result.getObject(2).toString();
	 						combo.addItem(item);
	 					}
	 				} catch (SQLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	}
	 	
	 	static void fillProductCombo(JComboBox<String> combo) {
	 		conn = getConnection();
	 				String sql = "select product_id, name from product";
	 				try {
	 					state = conn.prepareStatement(sql);
	 					result = state.executeQuery();
	 					combo.removeAllItems(); 
	 					while(result.next()) { 
	 						String item = result.getObject(1).toString() + " " + result.getObject(2).toString();
	 						combo.addItem(item);
	 					}
	 				} catch (SQLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	}
	 	
	 	static void filladdPeopleCombo(JComboBox<String> combo, List<Long> idPeople) {
	 		conn = getConnection();
	 				String sql = "select people_id, fname from people";
	 				try {
	 					state = conn.prepareStatement(sql);
	 					result = state.executeQuery();
	 					combo.removeAllItems(); 
	 					while(result.next()) { 
	 						idPeople.add(result.getLong(1));
	 						combo.addItem(result.getString(2));
	 					}
	 				} catch (SQLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	}
	 	
	 	static void filladdProductCombo(JComboBox<String> combo, List<Long> idProduct) {
	 		conn = getConnection();
	 				String sql = "select product_id, name from product";
	 				try {
	 					state = conn.prepareStatement(sql);
	 					result = state.executeQuery();
	 					combo.removeAllItems(); 
	 					while(result.next()) { 
	 						idProduct.add(result.getLong(1));
	 						combo.addItem(result.getString(2));
	 					}
	 				} catch (SQLException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	}

	 	static void fillSaleCombo(JComboBox<String> combo, List<Long> idSale) {
	 		conn = getConnection();
				String sql = "select sale_id, quantity from sale  ";
				try {
					state = conn.prepareStatement(sql);
					result = state.executeQuery();
					combo.removeAllItems(); 
					
					while(result.next()) { 
						idSale.add(result.getLong(1));
						combo.addItem(result.getString(2));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	}
	 	
	 	static void fillProfitPeopleCombo(JComboBox<String> combo) {
	 		conn = getConnection();
				String sql = "select fname  from people where role like 's%'  ";
				try {
					state = conn.prepareStatement(sql);
					result = state.executeQuery();
					combo.removeAllItems(); 
					
					while(result.next()) { 
						String item = result.getObject(1).toString();
						combo.addItem(item);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	}
	 	static void fillProfitProductCombo(JComboBox<String> combo) {
	 		conn = getConnection();
				String sql = "select quantity  from sale ";
				try {
					state = conn.prepareStatement(sql);
					result = state.executeQuery();
					combo.removeAllItems(); 
					
					while(result.next()) { 
						String item = result.getString(1);
						combo.addItem(item);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	}
	 	
	}
	



	
	


