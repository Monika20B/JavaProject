import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	Connection conn = null;
	PreparedStatement state = null;
	ResultSet result = null;
	
	int id = -1;
	
	List<Long> idPeople = new ArrayList<>();
	List<Long> idProduct = new ArrayList<>();
	List <Long> idSale = new ArrayList<>();
	
	//Tabs
	JTabbedPane tab = new JTabbedPane();
	
	
	//Panels
JPanel peoplePanel = new JPanel();
	JPanel upPanelPeople = new JPanel(new GridLayout(6, 1));
	JPanel midPanelPeople = new JPanel();
	JPanel downPanelPeople = new JPanel();
	JPanel searchPanelPeople = new JPanel();
	
JPanel productPanel = new JPanel();	
	JPanel upPanelProduct = new JPanel(new GridLayout(5, 1));
	JPanel midPanelProduct = new JPanel();
	JPanel downPanelProduct = new JPanel();
	JPanel searchPanelProduct = new JPanel();
	
JPanel salePanel = new JPanel();	
	JPanel upPanelSale = new JPanel(new GridLayout(3, 1));
	JPanel midPanelSale = new JPanel();
	JPanel downPanelSale = new JPanel();
	JPanel searchPanelSale = new JPanel();
	
JPanel profitPanel = new JPanel();	
	JPanel upPanelProfit = new JPanel(new GridLayout(3, 1));
	JPanel midPanelProfit = new JPanel();
	JPanel downPanelProfit = new JPanel();
	
	
	//Label People
	JLabel fnameL = new JLabel("Име:");
	JLabel lnameL = new JLabel("Фамилия:");
	JLabel ageL = new JLabel("Години:");
	JLabel cityL = new JLabel("Град:");
	JLabel addressL = new JLabel("Адрес:");		
	JLabel roleL = new JLabel("Роля:");	
	JLabel searchPeopleL = new JLabel("Търсене на човек:");
	
	//Label Product
	JLabel nameL = new JLabel("Име:");
	JLabel quantityL = new JLabel("Брой:");
	JLabel deliveryPriceL = new JLabel("Цена за брой при доставка:");
	JLabel salePriceL = new JLabel("Цена за брой при продажба:");
	JLabel searchProductL = new JLabel("Търсене на продукт:");
	
	//Label Sale
	JLabel peopleIdL = new JLabel("Име:");
	JLabel productIdL = new JLabel("Продукт:");
	JLabel quantitySaleL = new JLabel("Брой:");
	JLabel searchSaleL = new JLabel("Търсене по брой:");
	
	//Label Profit
	JLabel profitNameL = new JLabel("Име на продавач:");
	JLabel profitQuantityL = new JLabel("Брой продадени продукти:");
	
	JComboBox<String> profitNameBox = new JComboBox<String>();
	JComboBox<String> profitQuantityBox = new JComboBox<String>();
	
	//TextFields People
	JTextField fnameTF = new JTextField();
	JTextField lnameTF = new JTextField();
	JTextField ageTF = new JTextField();
	JTextField cityTF = new JTextField();
	JTextField addressTF = new JTextField();
	JTextField roleTF = new JTextField();
	
	//TextFields Product
	JTextField nameTF = new JTextField();
	JTextField quantityTF = new JTextField();
	JTextField deliveryPriceTF = new JTextField();
	JTextField salePriceTF = new JTextField();
	
	//TextFields Sale
	JTextField quantitySaleTF = new JTextField();
	
	//Buttons People
	JButton addButtonPeople = new JButton("Добавяне");
	JButton deleteButtonPeople = new JButton("Изтриване");
	JButton editButtonPeople = new JButton("Редактиране");
	JButton searchButtonPeople = new JButton("Търсене");
	JButton refreshButtonPeople = new JButton("Refresh");
	JComboBox<String> searchPeopleBox = new JComboBox<String>();
	
	//Buttons Product
	JButton addButtonProduct = new JButton("Добавяне");
	JButton deleteButtonProduct = new JButton("Изтриване");
	JButton editButtonProduct = new JButton("Редактиране");
	JButton searchButtonProduct = new JButton("Търсене");
	JButton refreshButtonProduct = new JButton("Refresh");
	JComboBox<String> searchProductBox = new JComboBox<String>();
	
	//Buttons Sale
	JButton addButtonSale = new JButton("Добавяне");
	JButton deleteButtonSale = new JButton("Изтриване");
	JButton editButtonSale = new JButton("Редактиране");
	JButton searchButtonSale = new JButton("Търсене");
	JButton refreshButtonSale = new JButton("Refresh");
	JComboBox<String> addPeopleBox = new JComboBox<String>();
	JComboBox<String> addProductBox = new JComboBox<String>();
	JComboBox<String> searchSaleBox = new JComboBox<String>();
	
	//Buttons Profit
	JButton searchButtonProfit = new JButton("Търсене");
	
	//Tables
	JTable tablePeople = new JTable();
	JTable tableProduct = new JTable();
	JTable tableSale = new JTable();
	JTable tableProfit = new JTable();
	
	//Scroll
	JScrollPane peopleScroller = new JScrollPane(tablePeople);
	JScrollPane productScroller = new JScrollPane(tableProduct);
	JScrollPane saleScroller = new JScrollPane(tableSale);
	JScrollPane profitScroller = new JScrollPane(tableProfit);
	
	public MyFrame() {
		DBHelper.init();
		this.setSize(500, 600);
		tab.add("Хора" , peoplePanel );
		tab.add("Продукти" , productPanel);
		tab.add("Продажба" , salePanel);
		tab.add("Печалба", profitPanel);
		tab.setSize(500, 600);
		
		
		
		//---------------------People-----------------------------------
		peoplePanel.setLayout(new GridLayout(4, 1));
		peoplePanel.add(upPanelPeople);
		peoplePanel.add(midPanelPeople);
		peoplePanel.add(downPanelPeople);
		peoplePanel.add(searchPanelPeople);
		
		upPanelPeople.add(fnameL);
		upPanelPeople.add(fnameTF);
		upPanelPeople.add(lnameL);
		upPanelPeople.add(lnameTF);
		upPanelPeople.add(ageL);
		upPanelPeople.add(ageTF);
		upPanelPeople.add(cityL);
		upPanelPeople.add(cityTF);
		upPanelPeople.add(addressL);
		upPanelPeople.add(addressTF);
		upPanelPeople.add(roleL);
		upPanelPeople.add(roleTF);
		
		midPanelPeople.add(addButtonPeople);
		midPanelPeople.add(editButtonPeople);
		midPanelPeople.add(deleteButtonPeople);
		midPanelPeople.add(refreshButtonPeople);
		
		
		downPanelPeople.add(peopleScroller);
		peopleScroller.setPreferredSize(new Dimension(480, 130));
		tablePeople.setModel(DBHelper.getAllData("PEOPLE"));
		tablePeople.removeColumn(tablePeople.getColumnModel().getColumn(0));
		tablePeople.addMouseListener(new TableListenerPeople());
		
		searchPanelPeople.add(searchPeopleL);
		searchPanelPeople.add(searchPeopleBox);
		searchPanelPeople.add(searchButtonPeople);
		
		DBHelper.fillPeopleCombo(searchPeopleBox);
		
		
		addButtonPeople.addActionListener(new AddActionPeople());
		refreshButtonPeople.addActionListener(new RefreshPeople());
		editButtonPeople.addActionListener(new EditPeople());
		deleteButtonPeople.addActionListener(new DeletePeople());
		searchButtonPeople.addActionListener(new SearchPeople());
		
		
		//-----------------------------------Product------------------------------------------
		productPanel.setLayout(new GridLayout(4, 1));
		productPanel.add(upPanelProduct);
		productPanel.add(midPanelProduct);
		productPanel.add(downPanelProduct);
		productPanel.add(searchPanelProduct);
		
		upPanelProduct.add(nameL);
		upPanelProduct.add(nameTF);
		upPanelProduct.add(quantityL);
		upPanelProduct.add(quantityTF);
		upPanelProduct.add(deliveryPriceL);
		upPanelProduct.add(deliveryPriceTF);
		upPanelProduct.add(salePriceL);
		upPanelProduct.add(salePriceTF);
		
		midPanelProduct.add(addButtonProduct);
		midPanelProduct.add(editButtonProduct);
		midPanelProduct.add(deleteButtonProduct);
		midPanelProduct.add(refreshButtonProduct);
		

		downPanelProduct.add(productScroller);
		productScroller.setPreferredSize(new Dimension(480, 130));
		tableProduct.setModel(DBHelper.getAllData("PRODUCT"));
		tableProduct.removeColumn(tableProduct.getColumnModel().getColumn(0));
		tableProduct.addMouseListener(new TableListenerProduct());
				
		searchPanelProduct.add(searchProductL);
		searchPanelProduct.add(searchProductBox);
		searchPanelProduct.add(searchButtonProduct);
		
		DBHelper.fillProductCombo(searchProductBox);
		
		
		addButtonProduct.addActionListener(new AddActionProduct());
		refreshButtonProduct.addActionListener(new RefreshProduct());
		editButtonProduct.addActionListener(new EditProduct());
		deleteButtonProduct.addActionListener(new DeleteProduct());
		searchButtonProduct.addActionListener(new SearchProduct());
		
		//----------------------------Sale----------------------------------
		salePanel.setLayout(new GridLayout(4, 1));
		salePanel.add(upPanelSale);
		salePanel.add(midPanelSale);
		salePanel.add(downPanelSale);
		salePanel.add(searchPanelSale);
		
		
		upPanelSale.add(peopleIdL);
		upPanelSale.add(addPeopleBox);
		upPanelSale.add(productIdL);
		upPanelSale.add(addProductBox);
		upPanelSale.add(quantitySaleL);
		upPanelSale.add(quantitySaleTF);
		
		midPanelSale.add(addButtonSale);
		midPanelSale.add(editButtonSale);
		midPanelSale.add(deleteButtonSale);
		midPanelSale.add(refreshButtonSale);
		
		DBHelper.filladdPeopleCombo(addPeopleBox, idPeople);
		DBHelper.filladdProductCombo(addProductBox, idProduct);
		DBHelper.fillSaleCombo(searchSaleBox, idSale);
		
		addButtonSale.addActionListener(new AddActionSale());
		refreshButtonSale.addActionListener(new RefreshActionSale());
		editButtonSale.addActionListener(new EditActionSale());
		deleteButtonSale.addActionListener(new DeleteActionSale());
		searchButtonSale.addActionListener(new SearchActionSale());
		
		downPanelSale.add(saleScroller);
		saleScroller.setPreferredSize(new Dimension(480, 130));
		tableSale.setModel(DBHelper.getAllSale());
		
		searchPanelSale.add(searchSaleL);
		searchPanelSale.add(searchSaleBox);
		searchPanelSale.add(searchButtonSale);
		
		tableSale.removeColumn(tableSale.getColumnModel().getColumn(0));
		tableSale.addMouseListener(new TableListenerSale());
		
		
		//-------------------------------------------Profit---------------------------------------
		
		profitPanel.setLayout(new GridLayout(3, 1));
		profitPanel.add(upPanelProfit);
		profitPanel.add(midPanelProfit);
		profitPanel.add(downPanelProfit);
		
		upPanelProfit.add(profitNameL);
		upPanelProfit.add(profitNameBox);
		upPanelProfit.add(profitQuantityL);
		upPanelProfit.add(profitQuantityBox);
		
		midPanelProfit.add(searchButtonProfit);
		searchButtonProfit.addActionListener(new SearchActionProfit());
		
		downPanelProfit.add(profitScroller);
		profitScroller.setPreferredSize(new Dimension(480, 130));
		
		DBHelper.fillProfitPeopleCombo(profitNameBox);
		DBHelper.fillProfitProductCombo(profitQuantityBox);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(tab);
		
		this.setVisible(true);
	}
	
	public void ClearFormPeople() {
		fnameTF.setText("");
		lnameTF.setText("");
		ageTF.setText("");
		cityTF.setText("");
		addressTF.setText("");
		roleTF.setText("");
	}
	
	public void ClearFormProduct() {
		nameTF.setText("");
		quantityTF.setText("");
		deliveryPriceTF.setText("");
		salePriceTF.setText("");
	}
	
	public void ClearFormSale() {
		quantityTF.setText("");
	}
	
	//---------------------------------------People-------------------------------------------
	class AddActionPeople implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBHelper.getConnection();
			String sql="INSERT INTO PEOPLE VALUES(null,?,?,?,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setInt(3,  Integer.parseInt((String)ageTF.getText()));
				state.setString(4, cityTF.getText());
				state.setString(5, addressTF.getText());
				state.setString(6, roleTF.getText());
				
				state.execute();
				tablePeople.setModel(DBHelper.getAllData("PEOPLE"));
				tablePeople.removeColumn(tablePeople.getColumnModel().getColumn(0));
				DBHelper.fillPeopleCombo(searchPeopleBox);
				DBHelper.fillProfitPeopleCombo(profitNameBox);
				DBHelper.filladdPeopleCombo(addPeopleBox, idPeople);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			ClearFormPeople();
			
		}
		
	}//end class AddAction
	
    class RefreshPeople implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tablePeople.setModel(DBHelper.getAllData("PEOPLE"));
			tablePeople.removeColumn(tablePeople.getColumnModel().getColumn(0));
			
			
		}
    	
    }
	
    class EditPeople implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = tablePeople.getSelectedRow();
			id = Integer.parseInt(tablePeople.getModel().getValueAt(row, 0).toString());
			
			conn = DBHelper.getConnection();
			String sql = "UPDATE People SET FNAME = ?, LNAME = ?, AGE = ?, CITY = ?, ADDRESS = ?, ROLE = ? WHERE PEOPLE_ID =" + id;
		 
		 try {
			 state=conn.prepareStatement(sql);
				state.setString(1, fnameTF.getText());
				state.setString(2, lnameTF.getText());
				state.setInt(3,  Integer.parseInt((String)ageTF.getText()));
				state.setString(4, cityTF.getText());
				state.setString(5, addressTF.getText());
				state.setString(6, roleTF.getText());
			
			state.execute();
			tablePeople.setModel(DBHelper.getAllData("PEOPLE"));
			tablePeople.removeColumn(tablePeople.getColumnModel().getColumn(0));
			DBHelper.fillPeopleCombo(searchPeopleBox);
			DBHelper.fillProfitPeopleCombo(profitNameBox);
			DBHelper.filladdPeopleCombo(addPeopleBox, idPeople);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 ClearFormPeople();
			
		}
		
	}

    class TableListenerPeople implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tablePeople.getSelectedRow(); // запазва селектрирания ред
			id = Integer.parseInt(tablePeople.getModel().getValueAt(row, 0).toString());
			
			if(e.getClickCount()==1){
				id = Integer.parseInt(tablePeople.getModel().getValueAt(row, 0).toString());
		        }
			 
			if(e.getClickCount()==2){
		           fnameTF.setText(tablePeople.getModel().getValueAt(row, 1).toString());
		           lnameTF.setText(tablePeople.getModel().getValueAt(row, 2).toString());
		           ageTF.setText(tablePeople.getModel().getValueAt(row, 3).toString());
		           cityTF.setText(tablePeople.getModel().getValueAt(row, 4).toString());
		           addressTF.setText(tablePeople.getModel().getValueAt(row, 5).toString());
		           roleTF.setText(tablePeople.getModel().getValueAt(row, 6).toString());
		        }
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
     
    class DeletePeople implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "DELETE FROM PEOPLE WHERE PEOPLE_ID = ?";
			try {
				state = conn.prepareStatement(sql);
				//TODO fill param
				state.setInt(1, id);
				state.execute();
				tablePeople.setModel(DBHelper.getAllData("PEOPLE"));
				id = -1;
				tablePeople.removeColumn(tablePeople.getColumnModel().getColumn(0));
				DBHelper.fillPeopleCombo(searchPeopleBox);
				DBHelper.fillProfitPeopleCombo(profitNameBox);
				DBHelper.filladdPeopleCombo(addPeopleBox, idPeople);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    }

    class SearchPeople implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String item = searchPeopleBox.getSelectedItem().toString();
			String[] content = item.split(" ");
			int peopleId = Integer.parseInt(content[0]);
			
			conn = DBHelper.getConnection();
			String sql = "select * from people where people_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, peopleId);
				result = state.executeQuery();
				tablePeople.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    }

    //------------------------------Product--------------------------------------------------
   
		
	class AddActionProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBHelper.getConnection();
			String sql="INSERT INTO PRODUCT VALUES(null,?,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setString(1, nameTF.getText());
				state.setInt(2,  Integer.parseInt((String)quantityTF.getText()));
				state.setDouble(3,  Double.parseDouble((String)deliveryPriceTF.getText()));
				state.setDouble(4,  Double.parseDouble((String)salePriceTF.getText()));
				
				state.execute();
				tableProduct.setModel(DBHelper.getAllData("PRODUCT"));
				tablePeople.removeColumn(tableProduct.getColumnModel().getColumn(0));
				DBHelper.fillProductCombo(searchProductBox);
				DBHelper.filladdProductCombo(addProductBox, idProduct);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			ClearFormProduct();
			
		}
		
	}
	
    class RefreshProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tableProduct.setModel(DBHelper.getAllData("PRODUCT"));
			tableProduct.removeColumn(tableProduct.getColumnModel().getColumn(0));
			
			
		}
    	
    }
   
    class EditProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = tableProduct.getSelectedRow();
			id = Integer.parseInt(tableProduct.getModel().getValueAt(row, 0).toString());
			
			conn = DBHelper.getConnection();
			String sql = "UPDATE Product SET NAME = ?, QUANTITY = ?, DELIVERYPRICE = ?, SALEPRICE = ? WHERE PRODUCT_ID =" + id;
		 
		 try {
			 state=conn.prepareStatement(sql);
				state.setString(1, nameTF.getText());
				state.setString(2, quantityTF.getText());
				state.setDouble(3,  Double.parseDouble((String)deliveryPriceTF.getText()));
				state.setDouble(4,  Double.parseDouble((String)salePriceTF.getText()));
			
			state.execute();
			tableProduct.setModel(DBHelper.getAllData("PRODUCT"));
			tableProduct.removeColumn(tableProduct.getColumnModel().getColumn(0));
			
			DBHelper.fillProductCombo(searchProductBox);
			DBHelper.filladdProductCombo(addProductBox, idProduct);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 ClearFormProduct();
			
		}
		
	}

    class TableListenerProduct implements MouseListener{

		@Override
		
			public void mouseClicked(MouseEvent e) {
				int row = tableProduct.getSelectedRow(); // запазва селектрирания ред
				id = Integer.parseInt(tableProduct.getModel().getValueAt(row, 0).toString());
				
				if(e.getClickCount()==1){
					id = Integer.parseInt(tableProduct.getModel().getValueAt(row, 0).toString());
			        }
				 
				if(e.getClickCount()==2){
			           nameTF.setText(tableProduct.getModel().getValueAt(row, 1).toString());
			           quantityTF.setText(tableProduct.getModel().getValueAt(row, 2).toString());
			           deliveryPriceTF.setText(tableProduct.getModel().getValueAt(row, 3).toString());
			           salePriceTF.setText(tableProduct.getModel().getValueAt(row, 4).toString());
			        }
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
    
}

    class DeleteProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
			try {
				state = conn.prepareStatement(sql);
				//TODO fill param
				state.setInt(1, id);
				state.execute();
				tableProduct.setModel(DBHelper.getAllData("PRODUCT"));
				id = -1;
				tableProduct.removeColumn(tableProduct.getColumnModel().getColumn(0));
				
				DBHelper.fillProductCombo(searchProductBox);
				DBHelper.filladdProductCombo(addProductBox, idProduct);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    }

    class SearchProduct implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String item = searchProductBox.getSelectedItem().toString();
			String[] content = item.split(" ");
			int productId = Integer.parseInt(content[0]);
			
			conn = DBHelper.getConnection();
			String sql = "select * from product where product_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, productId);
				result = state.executeQuery();
				tableProduct.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	
    } 

    //--------------------------------------Sale------------------------------------------------
    class AddActionSale implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conn=DBHelper.getConnection();
			String sql="INSERT INTO SALE VALUES(null,?,?,?)";
			try {
				state=conn.prepareStatement(sql);
				state.setLong(1, idPeople.get(addPeopleBox.getSelectedIndex()));
				state.setLong(2, idProduct.get(addProductBox.getSelectedIndex()));
				state.setInt(3,  Integer.parseInt((String)quantitySaleTF.getText()));
				
				state.execute();
				tableSale.setModel(DBHelper.getAllSale());
				tableSale.removeColumn(tableSale.getColumnModel().getColumn(0));
				DBHelper.fillSaleCombo(searchSaleBox, idSale);
				DBHelper.fillProfitProductCombo(profitQuantityBox);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			ClearFormSale();
			
		}
			
		}
    
    class EditActionSale implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = tableSale.getSelectedRow();
			id = Integer.parseInt(tableSale.getModel().getValueAt(row, 0).toString());
			
			conn = DBHelper.getConnection();
			String sql = "UPDATE Sale SET PEOPLE_ID = ?, PRODUCT_ID = ?, QUANTITY = ? WHERE SALE_ID =" + id;
		 
		 try {
			 state=conn.prepareStatement(sql);
				state.setLong(1, idPeople.get(addPeopleBox.getSelectedIndex()));
				state.setLong(2, idProduct.get(addProductBox.getSelectedIndex()));
				state.setInt(3,  Integer.parseInt((String)quantitySaleTF.getText()));
				
			
			state.execute();
			tableSale.setModel(DBHelper.getAllSale());
			tableSale.removeColumn(tableSale.getColumnModel().getColumn(0));
			DBHelper.fillSaleCombo(searchSaleBox, idSale);
			DBHelper.fillProfitProductCombo(profitQuantityBox);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 ClearFormProduct();
			
		}
			
		}
    
    class TableListenerSale implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableSale.getSelectedRow(); // запазва селектрирания ред
			id = Integer.parseInt(tableSale.getModel().getValueAt(row, 0).toString());
			
			if(e.getClickCount()==1){
				id = Integer.parseInt(tableSale.getModel().getValueAt(row, 0).toString());
		        }
			 
			if(e.getClickCount()==2){
				addPeopleBox.setSelectedItem(tableSale.getModel().getValueAt(row, 1).toString());
				addProductBox.setSelectedItem(tableSale.getModel().getValueAt(row, 3).toString());
		        quantitySaleTF.setText(tableSale.getModel().getValueAt(row, 4).toString());
		        }
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class DeleteActionSale implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "DELETE FROM SALE WHERE SALE_ID = ?";
			try {
				state = conn.prepareStatement(sql);
				//TODO fill param
				state.setInt(1, id);
				state.execute();
				tableSale.setModel(DBHelper.getAllSale());
				id = -1;
				tableSale.removeColumn(tableSale.getColumnModel().getColumn(0));
				DBHelper.fillSaleCombo(searchSaleBox, idSale);
				DBHelper.fillProfitProductCombo(profitQuantityBox);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			
		}
    
    class RefreshActionSale implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tableSale.setModel(DBHelper.getAllSale());
			tableSale.removeColumn(tableSale.getColumnModel().getColumn(0));
			
			
		}
			
		}
    
    class SearchActionSale implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String item = searchSaleBox.getSelectedItem().toString();
			String[] content = item.split(" ");
			int saleId = Integer.parseInt(content[0]);
			
			conn = DBHelper.getConnection();
			String sql = "SELECT SALE_ID, PEOPLE.FNAME,PEOPLE.ROLE, PRODUCT.NAME, SALE.QUANTITY FROM SALE JOIN PEOPLE ON PEOPLE.PEOPLE_ID = SALE.PEOPLE_ID JOIN PRODUCT ON SALE.PRODUCT_ID = PRODUCT.PRODUCT_ID where SALE.QUANTITY = ? ";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, saleId);
				result = state.executeQuery();
				tableSale.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    }
    
    //--------------------------Profit---------------------------------------------------------
    class SearchActionProfit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "SELECT FNAME,ROLE, SUM(SALE.QUANTITY * (DELIVERYPRICE - SALEPRICE)) AS DIFFERENCE FROM SALE JOIN PEOPLE p ON SALE.PEOPLE_ID = p.PEOPLE_ID\r\n" + 
					"JOIN PRODUCT PRO ON SALE.PRODUCT_ID = PRO.PRODUCT_ID\r\n" + 
					"WHERE FNAME = ? AND SALE.QUANTITY = ? AND ROLE LIKE 's%'\r\n" + 
					"GROUP BY FNAME";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, profitNameBox.getSelectedItem().toString());
				state.setString(2, profitQuantityBox.getSelectedItem().toString());
				
				result = state.executeQuery();
				tableProfit.setModel(new MyModel(result));
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	
    }
    
    }
    
    
    
    
    
    
    
    

    