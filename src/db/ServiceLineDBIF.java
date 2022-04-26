package db;

import java.util.List;

import model.ServiceLine;

public interface ServiceLineDBIF {
	public void insertServiceLines(List<ServiceLine> serviceLines, int orderNo);
}
