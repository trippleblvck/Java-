package db.service;

import com.google.gson.Gson;
import db.entity.Person;
import db.entity.PersonForView;
import db.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static app.ClientSocket.sendData;
import static app.Main.sessionFactory;

public class ClientService {

    public void getClient(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Person person = (Person) session.createQuery("FROM Person WHERE user = " + user.getId()).uniqueResult();
        transaction.commit();
        session.close();
        PersonForView personForView = person.castToPersonForView(user);
        sendData(new Gson().toJson(personForView));
    }

    public void selectAllClients() {
        UserService userService = new UserService();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List clients = session.createQuery("FROM Person").list();
        transaction.commit();
        session.close();
        List<PersonForView> personForViewList = new ArrayList<>();
        for (Object client : clients) {
            Person person = (Person) client;
            User user = userService.getUser(person.getUser());
            if (user.getRole().equals("Клиент")) {
                personForViewList.add(person.castToPersonForView(user));
            }
        }
        sendData(new Gson().toJson(personForViewList));
    }
    
    public void selectAllWorkers() {
        UserService userService = new UserService();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List clients = session.createQuery("FROM Person").list();
        transaction.commit();
        session.close();
        List<PersonForView> personForViewList = new ArrayList<>();
        for (Object client : clients) {
            Person person = (Person) client;
            User user = userService.getUser(person.getUser());
            if (user.getRole().equals("Работник")) {
                personForViewList.add(person.castToPersonForView(user));
            }
        }
        sendData(new Gson().toJson(personForViewList));
    }
    
    public void addPerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
        session.close();
    }

    public void updatePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
        session.close();
    }
}
