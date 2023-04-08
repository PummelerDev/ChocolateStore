package com.chocolatestore.utils;

import com.chocolatestore.domain.DTO.OrderDTOResponseByNumber;
import com.chocolatestore.domain.DTO.ProductDTOResponseByNumber;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.ListItem;
import com.lowagie.text.Chunk;
import com.lowagie.text.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfCreator {
    public File createPdfFromOrderDtoResponse(OrderDTOResponseByNumber orderDTOResponseByNumber) {
        Document document = new Document();
        byte[] result = new byte[0];
        try {
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream("order.pdf"));
            instance.setViewerPreferences(PdfWriter.PageModeUseOutlines);
            document.setPageSize(PageSize.A4);
            document.setMargins(15f, 15f, 24f, 20f);
            Font font = new Font(BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false), 14.0f, 1, new Color(0, 0, 0));
            HeaderFooter header = new HeaderFooter(new Phrase("Welcome to the Chocolate Store!", font), false);
            HeaderFooter footer = new HeaderFooter(new Phrase("With love to chocolate and to you!\n", font), new Phrase(" page."));
            footer.setAlignment(3);
            document.setHeader(header);
            document.setFooter(footer);
            document.open();
            document.add(new Paragraph("Order number: " + orderDTOResponseByNumber.getOrderNumber() + ".\n"));
            PdfPTable customerTable = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Phrase("Customer:", font));
            cell.setColspan(2);
            customerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Name", font));
            cell.setRowspan(1);
            customerTable.addCell(cell);
            cell = new PdfPCell(new Phrase(orderDTOResponseByNumber.getCustomer().getFirstName(), font));
            cell.setRowspan(1);
            customerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Lastname", font));
            cell.setRowspan(1);
            customerTable.addCell(cell);
            cell = new PdfPCell(new Phrase(orderDTOResponseByNumber.getCustomer().getLastName(), font));
            cell.setRowspan(1);
            customerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Address", font));
            cell.setRowspan(1);
            customerTable.addCell(cell);
            cell = new PdfPCell(new Phrase(orderDTOResponseByNumber.getCustomer().getAddress(), font));
            cell.setRowspan(1);
            customerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Email", font));
            cell.setRowspan(1);
            customerTable.addCell(cell);
            cell = new PdfPCell(new Phrase(orderDTOResponseByNumber.getCustomer().getEmail(), font));
            cell.setRowspan(1);
            customerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Phone", font));
            cell.setRowspan(1);
            customerTable.addCell(cell);
            cell = new PdfPCell(new Phrase(orderDTOResponseByNumber.getCustomer().getPhone(), font));
            cell.setRowspan(1);
            customerTable.addCell(cell);

            document.add(customerTable);

            document.add(new Paragraph("Products:"));

            for (ProductDTOResponseByNumber p :
                    orderDTOResponseByNumber.getProducts()) {
                List product = new List();
                product.setPreSymbol("*");
                product.add(new ListItem(new Chunk("Kind:  .  .  .  .  .  . " + p.getKind().toString()).setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Topping:  .  .  .  . " + p.getTopping().toString()).setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Manufacturer:  .  " + p.getManufacturerName()).setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Name:  .  .  .  .  . " + p.getName()).setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Description:  .  .  " + p.getDescription()).setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Weight:  .  .  .  .  " + p.getWeight() + "").setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Price:  .  .  .  .  .  " + p.getPrice() + "").setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("Quantity:  .  .  .   " + p.getQuantity() + "").setLocalDestination("dest1")));
                product.add(new ListItem(new Chunk("-   -   -   -   -   -   -   -   -   -   -").setLocalDestination("dest1")));
                document.add(product);
            }

            document.add(new Paragraph("Total price: " + orderDTOResponseByNumber.getTotalPrice() + "."));

            document.close();
            instance.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("order.pdf");
    }
}