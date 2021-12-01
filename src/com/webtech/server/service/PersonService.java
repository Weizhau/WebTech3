package com.webtech.server.service;

import com.webtech.server.dao.DaoFactory;
import com.webtech.server.model.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    private PersonService() {
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }

    public Person createPerson(NodeList nodes) {
        int id = 0;
        String first = "";
        String last = "";

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String text = nodes.item(i).getTextContent();
                switch (nodes.item(i).getNodeName()) {
                    case "id" -> id = Integer.parseInt(text);
                    case "firstName" -> first = text;
                    case "lastName" -> last = text;
                    default -> throw new IllegalArgumentException("Person doesn't exist");
                }
            }
        }

        return new Person(id, first, last);
    }

    public Element createNode(Document doc, Person person) {
        Element e = doc.createElement("case");
        Element id = doc.createElement("id");
        Element first = doc.createElement("firstName");
        Element last = doc.createElement("lastName");

        id.appendChild(doc.createTextNode(String.valueOf(person.getId())));
        first.appendChild(doc.createTextNode(person.getFirstName()));
        last.appendChild(doc.createTextNode(person.getLastName()));

        e.appendChild(id);
        e.appendChild(first);
        e.appendChild(last);

        return e;
    }

    public List<Person> getAll() {
        return DaoFactory.getInstance().getCaseDao().getAll();
    }

    public boolean containsPerson(int id) {
        return DaoFactory.getInstance().getCaseDao().contains(id);
    }

    public void editPerson(int id, String firstName, String lastName) {
        DaoFactory.getInstance().getCaseDao().setById(id, new Person(0, firstName, lastName));
    }

    public void addPerson(String firstName, String lastName) {
        DaoFactory.getInstance().getCaseDao().add(new Person(0, firstName, lastName));
    }
}
