package udemy.springboot.datajpa.app.views.xlsx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import udemy.springboot.datajpa.app.models.entities.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("invoices/view.xlsx")
public class InvoiceXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        Invoice invoice = (Invoice) model.get("invoice");
        Sheet sheet = workbook.createSheet("Factura");

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
    }
}
