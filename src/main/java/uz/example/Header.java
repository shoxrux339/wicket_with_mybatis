package uz.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class Header extends WebPage {

    public Header(PageParameters parameters) {
        super(parameters);
    }
}
