package gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;

public class DocumentCreator {

	Document doc = new Document();
	
	Section icon = doc.addSection();
	
	Section header = doc.addSection();
	
	Paragraph paraIcon = icon.addParagraph();
	
	InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
	DocPicture iconPicture = paraIcon.appendPicture(iconstream);
	
	public DocumentCreator() {
		doc.saveToFile("outputWordOrder.docx");
		System.out.println("called");
	}
	
}
