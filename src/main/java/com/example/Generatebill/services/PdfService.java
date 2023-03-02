package com.example.Generatebill.services;

import com.example.Generatebill.entity.Form;
import com.example.Generatebill.entity.Item;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    Logger logger = LoggerFactory.getLogger(PdfService.class)  ;


        public ByteArrayInputStream createPdf(Form form) {
            logger.info("Inside pdf service");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document,out);
            document.open();

            PdfPTable table = new PdfPTable(2);

            PdfPCell seller = new PdfPCell(new Paragraph(String.format("Seller :  \n Company Name : %s \n Address  : %s \n Gstin No: %s",form.getSeller(),form.getSellerAddress(),form.getSellerGstin())));
            seller.setFixedHeight(120);
            PdfPCell buyer = new PdfPCell(new Paragraph(String.format("Buyer :  \n Company Name : %s \n Address  : %s \n Gstin No: %s",form.getBuyer(),form.getBuyerAddress(),form.getBuyerGstin())));
            buyer.setFixedHeight(120);

            table.addCell(seller);
            table.addCell(buyer);

            PdfPTable table2 = new PdfPTable(4);
            PdfPCell item = new PdfPCell(new Paragraph("Item"));
            item.setFixedHeight(50);
            PdfPCell quantity = new PdfPCell(new Paragraph("Quantity"));
            quantity.setFixedHeight(50);
            PdfPCell rate = new PdfPCell(new Paragraph("Rate"));
            rate.setFixedHeight(50);
            PdfPCell amount = new PdfPCell(new Paragraph("Amount"));
            amount.setFixedHeight(50);

            table2.addCell(item);
            table2.addCell(quantity);
            table2.addCell(rate);
            table2.addCell(amount);


            for (Item item1 : form.getItems() ) {
                PdfPCell itemData = new PdfPCell(new Paragraph(item1.getName()));
                itemData.setFixedHeight(50);
                PdfPCell quantityData = new PdfPCell(new Paragraph(item1.getQuantity()));
                quantityData.setFixedHeight(50);
                PdfPCell rateData = new PdfPCell(new Paragraph(String.valueOf(item1.getRate())));
                rateData.setFixedHeight(50);
                PdfPCell amountData = new PdfPCell(new Paragraph(String.valueOf(item1.getAmount())));
                amountData.setFixedHeight(50);

                table2.addCell(itemData);
                table2.addCell(quantityData);
                table2.addCell(rateData);
                table2.addCell(amountData);
            }

            PdfPTable table3 = new PdfPTable(1);
            PdfPCell bottomCell = new PdfPCell(new Paragraph(""));
            bottomCell.setFixedHeight(50);
            table3.addCell(bottomCell);



            document.add(table);
            document.add(table2);
            document.add(table3);
            document.close();

            return new ByteArrayInputStream(out.toByteArray()) ;


        }

}
