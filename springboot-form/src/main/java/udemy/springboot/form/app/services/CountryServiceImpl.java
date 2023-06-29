package udemy.springboot.form.app.services;

import org.springframework.stereotype.Service;
import udemy.springboot.form.app.models.entities.Country;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final List<Country> countries;

    public CountryServiceImpl() {
        this.countries = Arrays.asList(
                new Country(1, "ES", "España"),
                new Country(2, "MX", "México"),
                new Country(3, "CL", "Chile"),
                new Country(4, "AR", "Argentina"),
                new Country(5, "PE", "Perú"),
                new Country(6, "CO", "Colombia"),
                new Country(7, "VE",  "Venezuela")
        );
    }

    @Override
    public List<Country> list() {
        return countries;
    }

    @Override
    public Country findById(Integer id) {
        return countries.stream().filter(country -> country.getId().equals(id)).findFirst().orElse(null);
    }
}
