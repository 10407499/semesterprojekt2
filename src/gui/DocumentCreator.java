package gui;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.spire.doc.Document;
import com.spire.doc.DocumentViewType;
import com.spire.doc.FileFormat;
import com.spire.doc.LineSpacingRule;
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
import model.OrderLine;

public class DocumentCreator {
	
	public DocumentCreator(Order order) {
		
		String templateA4 = "templateA4.docx";
		
		//	Loads a word document with A4 template
		Document doc = new Document(templateA4);

		//	Section for the header
		Section header = doc.getSections().get(0);
		
		//	Page margins
		header.getPageSetup().getMargins().setTop(30);
		header.getPageSetup().getMargins().setBottom(30);
		header.getPageSetup().getMargins().setLeft(60);
		header.getPageSetup().getMargins().setRight(80);
		
		//	Paragraph for icon
		Paragraph paraIcon = header.addParagraph();

		//	Flectning the icon from image package
		InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
		DocPicture iconPicture = paraIcon.appendPicture(iconstream);
		
		//Header
        paraIcon.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
		
		//Paragraph for header
		Paragraph orderInfo = header.addParagraph();
		orderInfo.getFormat().setAfterSpacing(1);

		//Body for header
        orderInfo.appendText("Bestilling: \n"
        		+ "Dato: " + order.getFulfillmentDate() + "\n"
        		+ "Mail: " + order.getCustomer().getEmail() +"\n"
        		+ "Adr: " + order.getCustomer().getStreet() + " " + order.getCustomer().getHouseNo() + ", " + order.getCustomer().getZipCode() + " " + order.getCustomer().getCity() + "\n"
        		+ "Antal: " + order.getCoverAmount() + "\n"
        		+ "Gæster ankommer kl: " +  "\n" //FIXME tilf�jes spisetidspunkt til order 
        		+ "Spisetid: " + order.getEatingTime() + " Afgang fra Jebjr.: \n"
        		+ "Ankomst: I vil få besked i dagene før jeres fest. \n"
        		+ "");
        
		//Paragraph for general information
		Paragraph generalInfo = header.addParagraph();
		generalInfo.getFormat().setAfterSpacing(1);
		//Body for general information
        generalInfo.appendText("Hej\n"
        		+ "Tak for snakken og for din bestilling. forespørgsel. Det vil vi rigtig gerne lave til dig.\n"
        		+ "Du får en kopi af mit arbejdspapir, som også er din bekr�ftelse.\n"
        		+ "Her menuen til jeres middag.\n"
        		+ "Her er et menuforslag til jeres fest.\n"
        		+ "Du kan her linke direkte til vores hjemmeside ");
        generalInfo.appendHyperlink("www.spaendendemad.dk","www.spaendendemad.dk", HyperlinkType.Web_Link);
        generalInfo.appendText(		
        		" og se vores menuer.\n"
        		+ "Ved foresp�rgsel er det vigtig vi f�r besked om I �nsker at benytte vores tilbud.\n"
        		+ "\n"
        		+ "Lidt generelt: \n"
        		+ "Her er ogs� lidt med vigtige oplysninger og vejledning om at modtage menuen fra os. \n"
        		+ "Her vil du kunne finde de retter I har bestilt. En vejledning kan ses her: \n");
        generalInfo.appendHyperlink("www.sp�ndendemad.dk/vejledning ","www.sp�ndendemad.dk/vejledning", HyperlinkType.Web_Link);
        generalInfo.appendText("\n"
        		+ "\n"
        		+ "Hvis I har �ndringer til antal couv., vil jeg gerne I mailer det aktuelle antal 10 dage f�r jeres \n"
        		+ "fest, da vi �nsker at have dokumenter klar til at k�be ind mandag morgen. \n"
        		+ "Spisetid m� ogs� v�re oplyst her \n"
        		+ "�nsker du maden leveret? Ved levering m� du oplyse leverings adresse. \n"
        		+ "\n"
        		);
        
       generalInfo.appendText("Vedr. betaling: Hvis ikke andet er aftalt, betales der ved levering/afhentning, dette kan g�res med Swipp, Mobilpay eller kontant, i vil modtage en mail med fakturaen senest 2 dage f�r festen \n"
    		   + "\n"
    		   + "Vi vil gerne I aflevere fade mandag efter jeres middag. Her holder vi �bent i k�kkenet for at modtage udstyr fra kl. 16.00 til 18.00. \n"
    		   + "\n"
    		   + "Hvis I �nsker det, s� kan jeg sende en� eller to med i k�kkenet. Serveringshj�lp kan vi ogs� henvise til jer \n"
    		   + "\n"
    		   + "Du m� endelig henvende, hvis du har sp�rgsm�l. dig \n"
    		   + "                                           De bedste hilsner fra Sanne."
    		   + "\n \n \n"
    		   );
       
       Paragraph menu = header.addParagraph();
       menu.appendText("*Menu\n");
       
       String menues = "";
       for(OrderLine ol : order.getOrderLines()) {
    	   menues += "\t" + ol.getProduct().getDescription() + "\t Antal: " + ol.getQuantity() + "\n";
       }
       menu.appendText(menues);
       
        //	Style header text
        ParagraphStyle styleHeader = new ParagraphStyle(doc);
        styleHeader.setName("Header");
        styleHeader.getCharacterFormat().setFontName("Times New Roman");
        styleHeader.getCharacterFormat().setFontSize(14);
        doc.getStyles().add(styleHeader);
        orderInfo.applyStyle("Header");
        
        //	Style information text
        ParagraphStyle styleInformation = new ParagraphStyle(doc);
        styleInformation.setName("Information");
        styleInformation.getCharacterFormat().setFontName("Times New Roman");
        styleInformation.getCharacterFormat().setFontSize(12);
        styleInformation.getCharacterFormat().setItalic(true);
        doc.getStyles().add(styleInformation);
        generalInfo.applyStyle("Information");
        
        ParagraphStyle menuInformation = new ParagraphStyle(doc);
        menuInformation.setName("menuInfo");
        menuInformation.getCharacterFormat().setFontName("Times New Roman");
        menuInformation.getCharacterFormat().setFontSize(14);
        menuInformation.getCharacterFormat().setItalic(true);
        doc.getStyles().add(menuInformation);
        menu.applyStyle("menuInfo");
        
        
        
        //	For at få dato'en til at se passe
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YY");
		LocalDate dd = order.getFulfillmentDate().toLocalDate();
		String dato = dd.format(dtf);
        
        String docname = dato + " " + order.getCustomer().getfName() + " " + order.getCustomer().getlName() + ".docx";
		doc.saveToFile(docname, FileFormat.Docx);
		System.out.println("called");
		
	}
	
}
