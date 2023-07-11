package udemy.springboot.datajpa.app.views.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import udemy.springboot.datajpa.app.models.entities.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.lowagie.text.Element.ALIGN_RIGHT;

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
        invoiceTable.addCell("Datos de la factura");
        invoiceTable.addCell(String.format("Factura: %s", invoice.getId()));
        invoiceTable.addCell(String.format("Descripción: %s", invoice.getDescription()));
        invoiceTable.addCell(String.format("Fecha: %s", invoice.getCreateAt()));

        PdfPTable invoiceLinesTable = new PdfPTable(4);
        invoiceLinesTable.addCell("Producto");
        invoiceLinesTable.addCell("Precio");
        invoiceLinesTable.addCell("Cantidad");
        invoiceLinesTable.addCell("Total");

        invoice.getItems().forEach(item -> {
            invoiceLinesTable.addCell(item.getProduct().getName());
            invoiceLinesTable.addCell(item.getProduct().getPrice().toPlainString());
            invoiceLinesTable.addCell(item.getQuantity().toString());
            invoiceLinesTable.addCell(item.getTotal().toPlainString());
        });

        PdfPCell cell = new PdfPCell(new Phrase("Total: "));
        cell.setColspan(3);
        cell.setHorizontalAlignment(ALIGN_RIGHT);

        invoiceLinesTable.addCell(cell);
        invoiceLinesTable.addCell(invoice.getTotal().toPlainString());

        document.add(customerTable);
        document.add(invoiceTable);
        document.add(invoiceLinesTable);
    }
}
