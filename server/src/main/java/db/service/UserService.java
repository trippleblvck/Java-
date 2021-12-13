package db.service;

import com.google.gson.Gson;
import db.entity.Person;
import db.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static app.ClientSocket.sendData;
import static app.Main.sessionFactory;

public class UserService {

    public User getUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User extendUser = (User) session.createQuery("FROM User WHERE id = " + id).uniqueResult();
        transaction.commit();
        session.close();
        return extendUser;
    }

    public void getUser(String userInfo) {
        Gson gson = new Gson();
        User user = gson.fromJson(userInfo, User.class);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User extendUser = (User) session.createQuery("FROM User WHERE login = '" + user.getLogin() + "' and  password = '" + user.getPassword() + "'").uniqueResult();
        transaction.commit();
        session.close();
        if (extendUser != null) {
            if (extendUser.getStatus() == 2) {
                sendData("Пользователь заблокирован");
            } else {
                sendData("Вход успешен");
                sendData(new Gson().toJson(extendUser));
                if ("Клиент".equals(extendUser.getRole())) {
                    new ClientService().getClient(extendUser);
                }
            }
        } else {
            sendData("Неверный логин или пароль");
        }
    }

    public String checkLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User extendUser = (User) session.createQuery("FROM User WHERE login = '" + login + "'").uniqueResult();
        transaction.commit();
        session.close();
        if (extendUser != null) {
            return "Такой логин уже занят";
        } else {
            return "Успешно добавлено";
        }
    }

    public void addUser(User user, Person person) {
        String loginCheckResult = checkLogin(user.getLogin());
        if (loginCheckResult.equals("Успешно добавлено")) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            person.setUser(user.getId());
            new ClientService().addPerson(person);
        }
        sendData(loginCheckResult);
    }

    public void updateUser(User user) {
        String loginCheckResult = checkLogin(user.getLogin());
        if (loginCheckResult.equals("Успешно добавлено")) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        }
    }

    public void updateStatus(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

}
