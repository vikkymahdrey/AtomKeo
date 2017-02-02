package com.agiledge.atom.service.intfc;

import java.util.ArrayList;

import javax.servlet.ServletOutputStream;

import com.agiledge.atom.dto.TripDetailsDto;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;

public interface Export2PDFService {
	 void export2PDF(ServletOutputStream sos,ArrayList<TripDetailsDto> tripSheetSaved) throws Exception;
	 void addMetaData(Document document) throws Exception;
	 void addTitlePage(Document document) throws DocumentException;
	 void addContent(Document document) throws DocumentException;
	 void createTableForDriver(Section subCatPart,TripDetailsDto tripDetailsDtoObj) throws BadElementException;
	 void createTableForTracking(Section subCatPart,TripDetailsDto tripDetailsDtoObj) throws BadElementException;
	 void addEmptyLine(Paragraph paragraph, int number) throws Exception;
}
