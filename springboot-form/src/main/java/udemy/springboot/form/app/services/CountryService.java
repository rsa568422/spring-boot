package udemy.springboot.form.app.services;

import udemy.springboot.form.app.models.entities.Country;

import java.util.List;

public interface CountryService {

    List<Country> list();

    Country findById(Integer id);
}
