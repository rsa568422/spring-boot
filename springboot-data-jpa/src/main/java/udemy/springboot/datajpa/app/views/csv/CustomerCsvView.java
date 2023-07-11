package udemy.springboot.datajpa.app.views.csv;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import udemy.springboot.datajpa.app.models.entities.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("list")
public class CustomerCsvView extends AbstractView {

    public CustomerCsvView() {
        setContentType("text/csv");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"customers.csv\"");
        response.setContentType(getContentType());
        Page<Customer> customers = (Page<Customer>) model.get("customers");

        ICsvBeanWriter writer = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"id", "name", "surname", "email", "createAt"};
        writer.writeHeader(header);
        for (Customer customer : customers) {
            writer.write(customer, header);
        }
        writer.close();
    }
}
