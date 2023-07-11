package udemy.springboot.datajpa.app.views.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import udemy.springboot.datajpa.app.models.entities.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("invoices/view")
public class InvoicePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Invoice invoice = (Invoice) model.get("invoice");

        PdfPTable customerTable = new PdfPTable(1);
        customerTable.setSpacingAfter(20);
        customerTable.addCell("Datos del cliente");
        customerTable.addCell(String.format("%s %s", invoice.getCustomer().getName(), invoice.getCustomer().getSurname()));
        customerTable.addCell(invoice.getCustomer().getEmail());

        PdfPTable invoiceTable = new PdfPTable(1);
        invoiceTable.setSpacingAfter(20);
        customerTable.addCell("Datos de la factura");
        customerTable.addCell(String.format("Factura: %s", invoice.getId()));
        customerTable.addCell(String.format("Descripción: %s", invoice.getDescription()));
        customerTable.addCell(String.format("Fecha: %s", invoice.getCreateAt()));

        document.add(customerTable);
        document.add(invoiceTable);
    }
}
