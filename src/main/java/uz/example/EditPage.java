package uz.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import uz.example.domain.Person;
import uz.example.mapper.MyBatisConnectionFactory;
import uz.example.mapper.PersonMapper;

public class EditPage extends Header {

    @SpringBean
    private PersonMapper personMapper;

    public EditPage(PageParameters parameters) {
        super(parameters);
        Person person;
        Integer perId = null;
        if (!parameters.isEmpty() && !parameters.get("personId").isNull() && !parameters.get("personId").isEmpty()) {
            Integer personId = parameters.get("personId").toInt();
            if (personId != null) {
                try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
                    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
                    person = personMapper.getPersonById(personId);
                    perId = personId;

                } catch (Exception io) {
                    System.out.println(io.toString());
                    person = new Person();
                }

            } else person = new Person();
        } else person = new Person();

        Form<Void> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                System.out.println("Form submitted.");
            }

        };
        TextField<Integer> pId = new TextField<Integer>("personId", new PropertyModel<Integer>(person, "id"));
        TextField<String> firstNameTxt = new TextField<String>("firstname", new PropertyModel<String>(person, "firstname"));
        TextField<String> lastNameTxt = new TextField<String>("lastname", new PropertyModel<String>(person, "lastname"));
        TextField<String> emailTxt = new TextField<String>("email", new PropertyModel<String>(person, "email"));
        TextField<Integer> ageTxt = new TextField<Integer>("age", new PropertyModel<Integer>(person, "age"));
        form.add(pId);
        form.add(firstNameTxt);
        form.add(lastNameTxt);
        form.add(ageTxt);
        form.add(emailTxt);
        Person finalPerson = person;
        Integer finalPerId = perId;
        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                System.out.println("description : " + finalPerson.toString());
                try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
                    PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
                    if (finalPerId != null) {
                        personMapper.updatePerson(finalPerson);
                    }else {
                        personMapper.insertPerson(finalPerson);
                    }
                    sqlSession.commit();
                    ShowList showList = new ShowList(parameters);
                    setResponsePage(showList);

                } catch (Exception io) {
                    io.printStackTrace();
                }
            }
        });

        add(form);
    }


}
