package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Product;

public class ProductListModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	private static final String []COL_NAMES = {"Description", "Price"};
	
	private List<Product> data;
	
	public ProductListModel() {
		super();
		data = new ArrayList<>();
	}
	
	public void setModelData(List<Product> data) {
		this.data = data;
		super.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		int res = 0;
		if(data != null)  {
			res = data.size();
		}
		return res;
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COL_NAMES[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		String res = "UNKNOWN";
		Product currProduct = data.get(row);
		switch(column) {
		case 0:	
			res = currProduct.getDescription();
			break;
		case 1: 
			res = ""+currProduct.getPrice(); //TODO Tjek ny løsning
			break;
		
		}
		return res;
	}
	
	
	
	
	
}
