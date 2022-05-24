package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.OrderLine;

public class ProductListModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	private static final String []COL_NAMES = {"Beskrivelse", "Antal", "Pris pr. kurvert"};
	
	private List<OrderLine> data;
	
	public ProductListModel() {
		super();
		data = new ArrayList<>();
	}
	
	public void setModelData(List<OrderLine> data) {
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
		OrderLine currProduct = data.get(row);
		switch(column) {
		case 0:	
			res = currProduct.getProduct().getDescription();
			break;
		case 1: 
			res = "" + currProduct.getQuantity();
			break;
		case 2:
			res = "" +currProduct.getProduct().getPrice();
		}
		return res;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false; 
	}

}
