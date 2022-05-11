package gui;

import java.io.InputStream;
import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.HyperlinkType;
import com.spire.doc.documents.PageOrientation;
import com.spire.doc.documents.PageSize;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.VerticalOrigin;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.formatting.ParagraphFormat;

import model.Order;

public class DocumentCreator {
	
	public DocumentCreator() {
		//Creates the document
		Document doc = new Document();

		//Section for the header
		Section header = doc.addSection();


		
		//Paragraph for icon
		Paragraph paraIcon = header.addParagraph();
//		ParagraphFormat format = paraIcon.getFormat();
//		format.setLeftIndent(0);
//		format.setRightIndent(0);
		InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
		DocPicture iconPicture = paraIcon.appendPicture(iconstream);
		
		//Header
        paraIcon.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		
		//Paragraph for header
		Paragraph orderInfo = header.addParagraph();
//		ParagraphFormat format2 = orderInfo.getFormat();
//		format2.setLeftIndent(0);
//		format2.setRightIndent(0);
		//Body for header
        orderInfo.appendText("Bestilling: \n"
        		+ "Dato: \n"
        		+ "Mail: \n"
        		+ "Adr: \n"
        		+ "Antal: \n"
        		+ "Gæster ankommer kl: \n"
        		+ "Spisetid: Afgang fra Jebjr.: \n"
        		+ "Ankomst: I vil få besked i dagene før jeres fest. \n"
        		+ "");
		
		//Paragraph for general information
		Paragraph generalInfo = header.addParagraph();
//		ParagraphFormat format3 = generalInfo.getFormat();
//		format3.setLeftIndent(0);
//		format3.setRightIndent(0);
		//Body for general information
        generalInfo.appendText("Hej\n"
        		+ "Tak for snakken og for din bestilling. forespørgsel. Det vil vi rigtig gerne lave til dig.\n"
        		+ "Du får en kopi af mit arbejdspapir, som også er din bekræftelse.\n"
        		+ "Her menuen til jeres middag.\n"
        		+ "Her er et menuforslag til jeres fest.\n"
        		+ "Du kan her linke direkte til vores hjemmeside ");
        generalInfo.appendHyperlink("www.spaendendemad.dk","www.spaendendemad.dk", HyperlinkType.Web_Link);
        generalInfo.appendText(		
        		" og se vores menuer.\n"
        		+ "Ved forespørgsel er det vigtig vi får besked om I ønsker at benytte vores tilbud.\n"
        		+ "\n"
        		+ "Lidt generalt: \n"
        		+ "Her er også lidt med vigtige oplysninger og vejledning om at modtage menuen fra os. \n"
        		+ "Her vil du kunne finde de retter I har bestilt. En vejledning kan ses her: \n");
        generalInfo.appendHyperlink("www.spændendemad.dk/vejledning ","www.spændendemad.dk/vejledning", HyperlinkType.Web_Link);
        generalInfo.appendText("\n"
        		+ "\n"
        		+ "Hvis I har ændringer til antal couv., vil jeg gerne I mailer det aktuelle antal 10 dage før jeres fest, \n"
        		+ "da vi ønsker at have dokumenter klar til at købe ind mandag morgen. Spisetid må også være oplyst \n"
        		+ "her \n"
        		+ "Ønsker du maden leveret? Ved levering må du oplyse leverings adresse. \n"
        		+ "\n"
        		);
        
        //Style FIXME navn
        ParagraphStyle styleHeader = new ParagraphStyle(doc);
        styleHeader.setName("Header");
        styleHeader.getCharacterFormat().setFontName("Times New Roman");
        styleHeader.getCharacterFormat().setFontSize(14);
        doc.getStyles().add(styleHeader);
        orderInfo.applyStyle("Header");
        
        //Style FIXME navn
        ParagraphStyle styleHeader2 = new ParagraphStyle(doc);
        styleHeader2.setName("Header2");
        styleHeader2.getCharacterFormat().setFontName("Times New Roman");
        styleHeader2.getCharacterFormat().setFontSize(12);
        styleHeader2.getCharacterFormat().setItalic(true);
        doc.getStyles().add(styleHeader2);
        generalInfo.applyStyle("Header2");
        
        Section section = doc.getSections().get(0);
		section.getPageSetup().setPageSize(PageSize.A4);
		section.getPageSetup().setOrientation(PageOrientation.Portrait);

        
		doc.saveToFile("outputWordOrder.docx");
		System.out.println("called");
		

	
		
		
		
	}
	
}
