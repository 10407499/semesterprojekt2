package gui;

import java.io.InputStream;
import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.VerticalOrigin;
import com.spire.doc.fields.DocPicture;

import model.Order;

public class DocumentCreator {

	Document doc = new Document();
	//Section for the header
	Section header = doc.addSection();
	
	//Paragraph for icon
	Paragraph paraIcon = header.addParagraph();
	//Paragraph for header
	Paragraph orderInfo = header.addParagraph();
	
	InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
	DocPicture iconPicture = paraIcon.appendPicture(iconstream);
	
	public DocumentCreator() {
		//Header
        paraIcon.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        
        //Body for header
        orderInfo.appendText("Bestilling: \n");
        orderInfo.appendText("Dato: \n");
        orderInfo.appendText("Mail: \n");
        orderInfo.appendText("Adr: \n");
        orderInfo.appendText("Antal: \n");
        orderInfo.appendText("Gæster ankommer: \n");
        orderInfo.appendText("Spisetid " + "Afgang fra Jebjr.: " + "Ankomst: I vil få besked i dagene før jeres fest \n");
        
        //Style
        ParagraphStyle styleHeader = new ParagraphStyle(doc);
        styleHeader.setName("Header");
        styleHeader.getCharacterFormat().setFontName("Times New Roman");
        styleHeader.getCharacterFormat().setFontSize(14);
        doc.getStyles().add(styleHeader);
        orderInfo.applyStyle("Header");
        
        
		doc.saveToFile("outputWordOrder.docx");
		System.out.println("called");
		
		
		
	}
	
}
