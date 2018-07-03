package uz.example.mapper;

import uz.example.domain.Person;

import java.util.List;

public interface PersonMapper {

    Integer insertPerson(Person person);
    Person getPersonById(Integer id);
    List getPersons();
    void updatePerson(Person person);
    void deletePerson(Integer id);
}
