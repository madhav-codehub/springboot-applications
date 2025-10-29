package com.codehub.springconfigurationproperties.service;

import com.codehub.springconfigurationproperties.model.Invoice;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PDFService {
    public String generateInvoicePdf(Invoice invoice) throws DocumentException, IOException {
        String filePath = "./uploads/invoice_" + invoice.getInvoiceNumber() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        document.add(new Paragraph("Invoice"));
        document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
        document.add(new Paragraph("Customer Name: " + invoice.getCustomerName()));
        document.add(new Paragraph("Amount: $" + invoice.getAmount()));
        document.add(new Paragraph("Date: " + invoice.getDate()));
        document.close();
        return filePath;
    }
}
