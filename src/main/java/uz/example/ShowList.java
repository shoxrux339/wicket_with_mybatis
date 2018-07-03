package uz.example;


import org.apache.ibatis.session.SqlSession;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import uz.example.config.Constants;
import uz.example.domain.Person;
import uz.example.mapper.MyBatisConnectionFactory;
import uz.example.mapper.PersonMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowList extends Header {


    private static final long serialVersionUID = 1L;

    public ShowList(PageParameters pageParameters) {
        super(pageParameters);
        List<Person> personList = new ArrayList<>();
        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            personList = personMapper.getPersons();

            if (personList == null) personList = new ArrayList<>();
        } catch (Exception io) {
            System.out.println(io.toString());
            personList = new ArrayList<>();
        }
        PageableListView<?> listViev = new PageableListView<Person>("persons", personList, personList.size()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem item) {
                Person person = (Person) item.getModelObject();
                item.add(new Label("id", person.getId()));
                item.add(new Label("firstname", person.getFirstname()));
                item.add(new Label("lastname", person.getLastname()));
                item.add(new Label("email", person.getEmail()));
                item.add(new Label("age", person.getAge()));
                item.add(new Link<Person>("delete", item.getModel()) {
                    @Override
                    public void onClick() {
                        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
                            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
                            personMapper.deletePerson(person.getId());
                            sqlSession.commit();
                            this.setResponsePage(getPage());
                        } catch (Exception io) {
                            System.out.println(io.toString());

                        }

                    }
                });
                item.add(new Link<Person>("edit", item.getModel()) {
                    @Override
                    public void onClick() {
                        pageParameters.add("personId", person.getId());

                        EditPage editPage = new EditPage(pageParameters);
                        setResponsePage(editPage);
                    }
                });
            }
        };

        add(listViev);
    }
}
