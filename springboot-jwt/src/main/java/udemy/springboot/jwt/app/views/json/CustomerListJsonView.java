package udemy.springboot.jwt.app.views.json;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import udemy.springboot.jwt.app.models.entities.Customer;

import java.util.Map;

@Component("list.json")
public class CustomerListJsonView extends MappingJackson2JsonView {

    @Override
    @SuppressWarnings("unchecked")
    protected Object filterModel(Map<String, Object> model) {
        Page<Customer> customers = (Page<Customer>) model.get("customers");
        model.remove("title");
        model.remove("page");
        model.remove("customers");
        model.put("customers", customers.getContent());
        return super.filterModel(model);
    }
}
