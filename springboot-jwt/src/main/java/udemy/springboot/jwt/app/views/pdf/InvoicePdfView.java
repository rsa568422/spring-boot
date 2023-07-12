package udemy.springboot.jwt.app.views.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import udemy.springboot.jwt.app.models.entities.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Locale;
import java.util.Map;

import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Element.ALIGN_RIGHT;

@Component("invoices/view")
public class InvoicePdfView extends AbstractPdfView {

    private final MessageSource messageSource;

    private final LocaleResolver localeResolver;

    @Autowired
    public InvoicePdfView(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Invoice invoice = (Invoice) model.get("invoice");
        Locale locale = localeResolver.resolveLocale(request);

        MessageSourceAccessor messages = getMessageSourceAccessor();

        PdfPCell cell = new PdfPCell(new Phrase(messageSource.getMessage("text.pdf.customer.title", null, locale)));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);

        PdfPTable customerTable = new PdfPTable(1);
        customerTable.setSpacingAfter(20);
        customerTable.addCell(cell);
        customerTable.addCell(String.format("%s %s", invoice.getCustomer().getName(), invoice.getCustomer().getSurname()));
        customerTable.addCell(invoice.getCustomer().getEmail());

        cell = new PdfPCell(new Phrase(messageSource.getMessage("text.pdf.invoice.title", null, locale)));
        cell.setBackgroundColor(new Color(195, 230, 203));
        cell.setPadding(8f);

        PdfPTable invoiceTable = new PdfPTable(1);
        invoiceTable.setSpacingAfter(20);
        invoiceTable.addCell(cell);
        invoiceTable.addCell(String.format("%s: %s", messages.getMessage("text.pdf.invoice.id"), invoice.getId()));
        invoiceTable.addCell(String.format("%s: %s", messages.getMessage("text.pdf.invoice.description"), invoice.getDescription()));
        invoiceTable.addCell(String.format("%s: %s", messages.getMessage("text.pdf.invoice.date"), invoice.getCreateAt()));

        PdfPTable invoiceLinesTable = new PdfPTable(4);
        invoiceLinesTable.setWidths(new float[] {3.5f, 1, 1, 1});
        invoiceLinesTable.addCell(messages.getMessage("text.pdf.invoice-line.product"));
        invoiceLinesTable.addCell(messages.getMessage("text.pdf.invoice-line.price"));
        invoiceLinesTable.addCell(messages.getMessage("text.pdf.invoice-line.quantity"));
        invoiceLinesTable.addCell(messages.getMessage("text.pdf.invoice-line.total"));

        invoice.getItems().forEach(item -> {
            PdfPCell quantity = new PdfPCell(new Phrase(item.getQuantity().toString()));
            quantity.setHorizontalAlignment(ALIGN_CENTER);
            invoiceLinesTable.addCell(item.getProduct().getName());
            invoiceLinesTable.addCell(item.getProduct().getPrice().toPlainString());
            invoiceLinesTable.addCell(quantity);
            invoiceLinesTable.addCell(item.getTotal().toPlainString());
        });

        cell = new PdfPCell(new Phrase(messages.getMessage("text.pdf.invoice.total")));
        cell.setColspan(3);
        cell.setHorizontalAlignment(ALIGN_RIGHT);

        invoiceLinesTable.addCell(cell);
        invoiceLinesTable.addCell(invoice.getTotal().toPlainString());

        document.add(customerTable);
        document.add(invoiceTable);
        document.add(invoiceLinesTable);
    }
}
