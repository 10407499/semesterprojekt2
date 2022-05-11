package gui;

import java.io.InputStream;
import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.VerticalOrigin;
import com.spire.doc.fields.DocPicture;

public class DocumentCreator {

	Document doc = new Document();
	
	Section icon = doc.addSection();
	
	Section header = doc.addSection();
	
	Paragraph paraIcon = icon.addParagraph();
	
	InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
	DocPicture iconPicture = paraIcon.appendPicture(iconstream);
	
	public DocumentCreator() {
		iconPicture.setHorizontalPosition(422);
        iconPicture.setVerticalOrigin(VerticalOrigin.Top_Margin_Area);
        iconPicture.setVerticalPosition(0);
        
		doc.saveToFile("outputWordOrder.docx");
		System.out.println("called");
	}
	
}
