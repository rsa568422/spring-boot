package udemy.springboot.jwt.app.views.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;
import udemy.springboot.jwt.app.models.entities.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("list.xml")
public class CustomerListXmlView extends MarshallingView {

    @Autowired
    public CustomerListXmlView(Jaxb2Marshaller marshaller) {
        super(marshaller);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        Page<Customer> customers = (Page<Customer>) model.get("customers");
        model.remove("title");
        model.remove("page");
        model.remove("customers");
        model.put("customerList", new CustomerList(customers.getContent()));
        super.renderMergedOutputModel(model, request, response);
    }
}
