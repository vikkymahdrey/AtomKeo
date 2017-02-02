package com.agiledge.atom.service.impl;

import java.util.ArrayList;

import javax.servlet.ServletOutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.service.intfc.Export2PDFService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class Export2PDFServiceImpl implements Export2PDFService {
	private static final Logger logger = Logger.getLogger(Export2PDFServiceImpl.class);
	// private static String FILE = "c:/work/FirstPdf.pdf";
		private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,	Font.BOLD);
		private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
		private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
		private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

		private ArrayList<TripDetailsDto> savedTripSheet = null;

		public void export2PDF(ServletOutputStream sos,	ArrayList<TripDetailsDto> tripSheetSaved) throws Exception {
			try {
				this.savedTripSheet = tripSheetSaved;
				Document document = new Document(PageSize.A4.rotate());
				PdfWriter.getInstance(document, sos);
				document.open();
				addMetaData(document);
				addTitlePage(document);
				addContent(document);

				document.close();
			} catch (Exception e) {
				logger.error("Error In Export2PDF",e);
			}

		}

		public void addMetaData(Document document) throws Exception {
			document.addTitle("Logica TripSheet");
			document.addSubject("Employee TripSheet");

			document.addAuthor("Agiledge Solutions");
			document.addCreator("System Generate");

		}

		public void addTitlePage(Document document) throws DocumentException {
			Paragraph preface = new Paragraph();
			// We add one empty line
			try {
				addEmptyLine(preface, 1);
			// Lets write a big header
			preface.add(new Paragraph("Logica TripSheet", catFont));

			addEmptyLine(preface, 1);
			// Start a new page
			document.newPage();
			} catch (Exception e) {
				logger.error("Error in AddTitle",e);
			}
		}

		public void addContent(Document document) throws DocumentException {

			
			int j = 1;
			for (TripDetailsDto tripDetailsDtoObj : savedTripSheet) {
				Chapter catPart = new Chapter(j);

				Section subCatPart = catPart.addSection("Tracking Copy");

				// Add a table
				createTableForTracking(subCatPart, tripDetailsDtoObj);
				subCatPart = catPart.addSection("Driver's Copy");
				createTableForDriver(subCatPart, tripDetailsDtoObj);

				document.add(catPart);
				document.newPage();
				j++;
			}

			
		}

		public void createTableForDriver(Section subCatPart,TripDetailsDto tripDetailsDtoObj) throws BadElementException {
			try {

				// Add a table

				// Now add all this to the document

				PdfPTable table = new PdfPTable(7);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10);
				table.setSpacingAfter(30);
				table.setTotalWidth(new float[] { 15, 125, 150, 150, 200, 100, 75 });

				table.setLockedWidth(true);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				// t.setBorderColor(BaseColor.GRAY);
				// t.setPadding(4);
				// t.setSpacing(4);
				// t.setBorderWidth(1);
				PdfPCell c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Vehicle Type"));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getVehicle_type()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Trip ID"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getTrip_code()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				// table.setHeaderRows(2);
				c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Vehicle No."));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(""));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Date"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(
						OtherFunctions.changeDateFromatToddmmyyyy(tripDetailsDtoObj
								.getTrip_date())));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Escort Trip"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getIsSecurity()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("IN/OUT Time"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getTrip_log() + "  "
						+ tripDetailsDtoObj.getTrip_time()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				// table.setHeaderRows(3);
				c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Escort Clock"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(""));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Actual IN/OUT Time"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(""));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(" # "));

				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Name"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Area"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Place"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Landmark"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Status/Remark"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Signature"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);

				int i = 1;

				for (TripDetailsChildDto tripDetailsChildDto : tripDetailsDtoObj
						.getTripDetailsChildDtoList()) {

					c1 = new PdfPCell(new Phrase(String.valueOf(i)));

					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(
							tripDetailsChildDto.getEmployeeName()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getArea()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);
					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getPlace()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getLandmark()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c1);
					i++;
				}

				while (i < 7) {
					c1 = new PdfPCell(new Phrase(String.valueOf(i)));

					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("  "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);
					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(c1);
					i++;
				}

				c1 = new PdfPCell(new Phrase("Security Seal "));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				c1.setColspan(4);

				c1.setFixedHeight(50);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Admin Seal "));
				c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				c1.setFixedHeight(50);
				c1.setVerticalAlignment(Element.ALIGN_CENTER);
				c1.setColspan(3);

				table.addCell(c1);

				subCatPart.add(table);

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void createTableForTracking(Section subCatPart,	TripDetailsDto tripDetailsDtoObj) throws BadElementException {
			try {

				// Add a table

				// Now add all this to the document

				PdfPTable table = new PdfPTable(7);
				table.setWidthPercentage(100);
				table.setSpacingBefore(10);
				table.setSpacingAfter(30);
				table.setTotalWidth(new float[] { 15, 125, 150, 150, 200, 75, 100 });

				table.setLockedWidth(true);
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				// t.setBorderColor(BaseColor.GRAY);
				// t.setPadding(4);
				// t.setSpacing(4);
				// t.setBorderWidth(1);
				PdfPCell c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Vehicle Type"));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getVehicle_type()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Trip ID"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getTrip_code()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				// table.setHeaderRows(2);
				c1 = new PdfPCell(new Phrase("  "));

				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("IN/OUT Time"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(tripDetailsDtoObj.getTrip_log() + "  "
						+ tripDetailsDtoObj.getTrip_time()));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setColspan(3);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Actual IN/OUT Time"));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(""));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase(" # "));

				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Name"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Area"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Place"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Landmark"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Contact No."));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);

				c1 = new PdfPCell(new Phrase("Status/Remark"));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);

				int i = 1;

				for (TripDetailsChildDto tripDetailsChildDto : tripDetailsDtoObj
						.getTripDetailsChildDtoList()) {

					c1 = new PdfPCell(new Phrase(String.valueOf(i)));

					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(
							tripDetailsChildDto.getEmployeeName()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getArea()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);
					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getPlace()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(tripDetailsChildDto.getLandmark()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(
							tripDetailsChildDto.getContactNumber()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c1);
					i++;
				}

				while (i < 7) {
					c1 = new PdfPCell(new Phrase(String.valueOf(i)));

					c1.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase("  "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);
					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(""));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(c1);

					c1 = new PdfPCell(new Phrase(" "));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					table.addCell(c1);
					i++;
				}

				subCatPart.add(table);

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	public void addEmptyLine(Paragraph paragraph, int number) throws Exception {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));
			}
		}

}
