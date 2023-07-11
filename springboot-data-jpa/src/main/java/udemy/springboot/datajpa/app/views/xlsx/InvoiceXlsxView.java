package udemy.springboot.datajpa.app.views.xlsx;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import udemy.springboot.datajpa.app.models.entities.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component("invoices/view.xlsx")
public class InvoiceXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        Invoice invoice = (Invoice) model.get("invoice");
        Sheet sheet = workbook.createSheet("Factura");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"invoice_%s.xlsx\"", invoice.getId()));

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Datos del cliente");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(String.format("%s %s", invoice.getCustomer().getName(), invoice.getCustomer().getSurname()));

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(invoice.getCustomer().getEmail());

        sheet.createRow(4).createCell(0).setCellValue("Datos de la factura");
        sheet.createRow(5).createCell(0).setCellValue(String.format("Factura: %s", invoice.getId()));
        sheet.createRow(6).createCell(0).setCellValue(String.format("Descripción: %s", invoice.getDescription()));
        sheet.createRow(7).createCell(0).setCellValue(String.format("Fecha: %s", invoice.getCreateAt()));

        CellStyle tHeadStyle = workbook.createCellStyle();
        tHeadStyle.setBorderBottom(BorderStyle.MEDIUM);
        tHeadStyle.setBorderTop(BorderStyle.MEDIUM);
        tHeadStyle.setBorderRight(BorderStyle.MEDIUM);
        tHeadStyle.setBorderLeft(BorderStyle.MEDIUM);
        tHeadStyle.setFillBackgroundColor(IndexedColors.GOLD.index);
        tHeadStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tBodyStyle = workbook.createCellStyle();
        tBodyStyle.setBorderBottom(BorderStyle.THIN);
        tBodyStyle.setBorderTop(BorderStyle.THIN);
        tBodyStyle.setBorderRight(BorderStyle.THIN);
        tBodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(9);
        header.createCell(0).setCellValue("Producto");
        header.createCell(1).setCellValue("Precio");
        header.createCell(2).setCellValue("Cantidad");
        header.createCell(3).setCellValue("Total");
        header.getCell(0).setCellStyle(tHeadStyle);
        header.getCell(1).setCellStyle(tHeadStyle);
        header.getCell(2).setCellStyle(tHeadStyle);
        header.getCell(3).setCellStyle(tHeadStyle);

        AtomicInteger integer = new AtomicInteger(10);
        invoice.getItems().forEach(item -> {
            Row itemRow = sheet.createRow(integer.getAndIncrement());
            Cell itemCell = itemRow.createCell(0);
            itemCell.setCellValue(item.getProduct().getName());
            itemCell.setCellStyle(tBodyStyle);

            itemCell = itemRow.createCell(1);
            itemCell.setCellValue(item.getProduct().getPrice().toPlainString());
            itemCell.setCellStyle(tBodyStyle);

            itemCell = itemRow.createCell(2);
            itemCell.setCellValue(item.getQuantity());
            itemCell.setCellStyle(tBodyStyle);

            itemCell = itemRow.createCell(3);
            itemCell.setCellValue(item.getTotal().toPlainString());
            itemCell.setCellStyle(tBodyStyle);
        });

        Row totalRow = sheet.createRow(integer.get());

        cell = totalRow.createCell(2);
        cell.setCellValue("Total: ");
        cell.setCellStyle(tBodyStyle);

        cell = totalRow.createCell(3);
        cell.setCellValue(invoice.getTotal().toPlainString());
        cell.setCellStyle(tBodyStyle);
    }
}
