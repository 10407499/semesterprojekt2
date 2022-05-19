package model;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;

import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.HyperlinkType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.fields.DocPicture;

public class OrderConfirmationDocument {

	private String docname;
	private Paragraph forret;
	private Paragraph hovedret;
	private Paragraph buffet;
	private Paragraph dessert;
	
	public OrderConfirmationDocument(Order order) {

		String templateA4 = "templateA4.docx";
		String usefulInformationTemplate = "usefulInformationTemplate.docx";

		// Loads a word document with A4 template
		Document doc = new Document(templateA4);

		// Section for the header
		Section header = doc.getSections().get(0);

		// Page margins
		header.getPageSetup().getMargins().setTop(30);
		header.getPageSetup().getMargins().setBottom(30);
		header.getPageSetup().getMargins().setLeft(60);
		header.getPageSetup().getMargins().setRight(80);

		// Paragraph for icon
		Paragraph paraIcon = header.addParagraph();

		// Flectning the icon from image package
		InputStream iconstream = getClass().getResourceAsStream("/images/fullicon.png");
		DocPicture iconPicture = paraIcon.appendPicture(iconstream);

		// Header
		paraIcon.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

		// Paragraph for header
		Paragraph orderInfo = header.addParagraph();
		orderInfo.getFormat().setAfterSpacing(1);

		String orderInfoString = "Bestilling: \n" + "Dato: " + order.getFulfillmentDateToString() + "\n" + "Mail: "
				+ order.getCustomer().getEmail() + "\n" + "Adr: " + order.getCustomer().getStreet() + " "
				+ order.getCustomer().getHouseNo() + ", " + order.getCustomer().getZipCode() + " "
				+ order.getCustomer().getCity() + "\n" + "Antal: " + order.getCoverQuantity() + "\n" + "Spisetid: "
				+ order.getEatingTime() + "\t";

		// Paragraph for general information
		Paragraph generalInfo = header.addParagraph();
		generalInfo.getFormat().setAfterSpacing(1);

		// Body for general information

		// If the menu has been chosen
		String generalInformationComfirmedString = "Hej\n"
				+ "Tak for snakken og for din bestilling. Det vil vi rigtig gerne lave til dig.\n"
				+ "Du får en kopi af mit arbejdspapir, som også er din bekræftelse.\n" + "Her menuen til jeres middag."
				+ "\n" + "\n";

		// If the menu hasent been picked yet no: 1
		String generalInformationNotComfirmedString = "Hej\n"
				+ "Tak for snakken og for din bestilling. Det vil vi rigtig gerne lave til dig.\n"
				+ "Du får en kopi af mit arbejdspapir, som også er din bekræftelse.\n"
				+ "Her er et menuforslag til jeres fest.\n" + "Du kan her linke direkte til vores hjemmeside ";

		// If the menu hasent been picked yet no: 2
		String generalInformationAfterLink = " og se vores menuer.\n"
				+ "Ved forespørgsel er det vigtig vi får besked om I ønsker at benytte vores tilbud.\n" + "\n";

		// Control flow to check what state the order is

		if (order.getDelivery() != null) {
			orderInfoString += " Afgang fra Jebjr.: \n" + "Ankomst: I vil få besked i dagene før jeres fest. \n";
			if (order.getDelivery().getServiceLines() != null) {
				orderInfoString += "Køk: \tmøde i Jebjr. kl.    /    på fest adr. kl\n" + "Serv: \tmøde på festadr.\n";
			}
		} else {
			orderInfoString += "Afhentes kl. \tpå Sdr. Gade 20 Jebjr. 7870 Roslev.";
		}


		if (order.isConfimation()) {
			generalInfo.appendText(generalInformationComfirmedString);
		} else {
			generalInfo.appendText(generalInformationNotComfirmedString);
			generalInfo.appendHyperlink("www.spaendendemad.dk", "www.spaendendemad.dk", HyperlinkType.Web_Link);
			generalInfo.appendText(generalInformationAfterLink);
		}

		// Body for header
		orderInfo.appendText(orderInfoString);

		generalInfo.appendText("Lidt generelt: " + "\n"
				+ "Her er også lidt med vigtige oplysninger og vejledning om at modtage menuen fra os. \n"
				+ "Her vil du kunne finde de retter I har bestilt. En vejledning kan ses her: \n");

		generalInfo.appendHyperlink("www.spændendemad.dk/vejledning ", "www.spændendemad.dk/vejledning",
				HyperlinkType.Web_Link);

		generalInfo.appendText("\n" + "\n"
				+ "Hvis I har ændringer til antal couv., vil jeg gerne I mailer det aktuelle antal 10 dage før jeres \n"
				+ "fest, da vi ønsker at have dokumenter klar til at købe ind mandag morgen. \n"
				+ "Spisetid må også være oplyst her \n"
				+ "ønsker du maden leveret? Ved levering må du oplyse leverings adresse. \n" + "\n");

		generalInfo.appendText(
				"Vedr. betaling: Hvis ikke andet er aftalt, betales der ved levering/afhentning, dette kan gøres med Swipp, Mobilpay eller kontant, i vil modtage en mail med fakturaen senest 2 dage før festen \n"
						+ "\n"
						+ "Vi vil gerne I aflevere fade mandag efter jeres middag. Her holder vi åbent i køkkenet for at modtage udstyr fra kl. 16.00 til 18.00. \n"
						+ "\n"
						+ "Hvis I ønsker det, så kan jeg sende en eller to med i køkkenet. Serveringshjælp kan vi også henvise til jer \n"
						+ "\n" + "Du må endelig henvende, hvis du har spørgsmål. dig \n"
						+ "                                           De bedste hilsner fra Sanne." + "\n \n \n");

		// Body for menu
		Paragraph menu = header.addParagraph();
		menu.appendText("*Menu\n");

		forret = header.addParagraph();
		hovedret = header.addParagraph();
		buffet = header.addParagraph();
		dessert = header.addParagraph();

		forret.appendText("Forret\n");
		hovedret.appendText("Hovedret\n");
		buffet.appendText("Buffet\n");
		dessert.appendText("Dessert\n");
		String menues = "";

		for (OrderLine ol : order.getOrderLines()) {
			Product p = ol.getProduct();
			
			Dish dish = null;
			Menu m = null;
			
			if(p instanceof Dish) {
				dish = (Dish) p;
			}else if(p instanceof Menu){
				m = (Menu) p;
			}
			
			if(dish != null) {
				menues = dish.getDescription() + "\t\n" + dish.getPrice() + " kr,- pr. couv \n";
				checkTypeOnProduct(dish.getCourseType(), menues);
				menues = null;
			}else {
				for(Dish d : m.getDishes()) {
					menues = d.getDescription() + "\t\n" + d.getPrice() + " kr,- pr. couv \n";
					checkTypeOnProduct(d.getCourseType(), menues);
					menues = null;
				}
			}
			
		}
		
		// Body for useful information
		Paragraph usefulInfo = header.addParagraph();
		usefulInfo.getFormat().setAfterSpacing(1);

		usefulInfo.appendText(
				"\nAlt vil komme flot opsat på vore opsatser, træfade, store Italienske porcelæns fade og smukke metalfade "
						+ "\n\n");

		// Style header text
		ParagraphStyle styleHeader = new ParagraphStyle(doc);
		styleHeader.setName("Header");
		styleHeader.getCharacterFormat().setFontName("Times New Roman");
		styleHeader.getCharacterFormat().setFontSize(14);
		doc.getStyles().add(styleHeader);
		orderInfo.applyStyle("Header");
		
		// Style information text
		ParagraphStyle styleInformation = new ParagraphStyle(doc);
		styleInformation.setName("Information");
		styleInformation.getCharacterFormat().setFontName("Times New Roman");
		styleInformation.getCharacterFormat().setFontSize(12);
		styleInformation.getCharacterFormat().setItalic(true);
		doc.getStyles().add(styleInformation);
		generalInfo.applyStyle("Information");
		usefulInfo.applyStyle("Information");

		ParagraphStyle menuInformation = new ParagraphStyle(doc);
		menuInformation.setName("menuInfo");
		menuInformation.getCharacterFormat().setFontName("Times New Roman");
		menuInformation.getCharacterFormat().setFontSize(14);
		menuInformation.getCharacterFormat().setItalic(true);
		doc.getStyles().add(menuInformation);
		menu.applyStyle("menuInfo");

		docname = order.getFulfillmentDateToString() + "-" + order.getCustomer().getfName() + "-"
				+ order.getCustomer().getlName() + ".docx";

		Document mergeDoc = new Document(usefulInformationTemplate);
		Section lastSection = doc.getLastSection();

		for (Section section : (Iterable<Section>) mergeDoc.getSections()) {
			for (DocumentObject obj : (Iterable<DocumentObject>) section.getBody().getChildObjects()) {
				lastSection.getBody().getChildObjects().add(obj.deepClone());
			}
		}

		doc.saveToFile(docname, FileFormat.Docx);
	}
	
	public void openGenerateDocument() {
		try {
			String path = System.getProperty("user.dir") + System.getProperty("file.separator");
			File wordDoc = new File(path+docname);
			if(Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if(wordDoc.exists())
					desktop.open(wordDoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkTypeOnProduct(CourseType courseType, String textOutPut) {
		switch(courseType) {
		case FORRET:
			forret.appendText(textOutPut);
			break;
		case HOVEDRET:
			hovedret.appendText(textOutPut);
			break;
		case BUFFET:
			buffet.appendText(textOutPut);
			break;
		case DESSERT:
			dessert.appendText(textOutPut);
			break;
		default:
			break;
		}
	}
}
