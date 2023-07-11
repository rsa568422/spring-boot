package udemy.springboot.datajpa.app.views.csv;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

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
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"customers.csv\"");
        response.setContentType(getContentType());
    }
}
