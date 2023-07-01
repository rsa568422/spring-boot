package udemy.springboot.datajpa.app.utils.paginators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageItem {

    private final int number;

    private final boolean actual;
}
