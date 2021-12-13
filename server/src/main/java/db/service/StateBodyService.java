package db.service;

import com.google.gson.Gson;
import db.entity.StateBody;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static app.ClientSocket.sendData;
import static app.Main.sessionFactory;

public class StateBodyService {

    public void selectAllStateBodies() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List stateBodies = session.createQuery("FROM StateBody ").list();
        transaction.commit();
        session.close();
        sendData(new Gson().toJson(stateBodies));
    }
    
    public void addStateBody(StateBody stateBody) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(stateBody);
        transaction.commit();
        session.close();
    }

    public void updateStateBody(StateBody stateBody) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(stateBody);
        transaction.commit();
        session.close();
    }
    
    public void deleteStateBody(StateBody stateBody) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(stateBody);
        transaction.commit();
        session.close();
    }
}
