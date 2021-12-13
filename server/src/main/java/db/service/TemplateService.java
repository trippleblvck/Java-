package db.service;

import com.google.gson.Gson;
import db.entity.DocumentForView;
import db.entity.Template;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static app.ClientSocket.sendData;
import static app.Main.sessionFactory;

public class TemplateService {
    
    public Template getTemplate(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Template template = (Template) session.createQuery("FROM Template WHERE id = " + id).uniqueResult();
        transaction.commit();
        session.close();
        return template;
    }
    
    public Template getTemplateByName(DocumentForView document) {
        if(document.getTemplateNameCast() == null)
            return document.getTemplate();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Template template = (Template) session.createQuery("FROM Template WHERE name = '" + document.getTemplateNameCast() + "'").uniqueResult();
        transaction.commit();
        session.close();
        return template;
    }
    
    public void selectAllTemplates() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List templates = session.createQuery("FROM Template ").list();
        transaction.commit();
        session.close();
        sendData(new Gson().toJson(templates));
    }
    
    public void addTemplate(Template template) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(template);
        transaction.commit();
        session.close();
    }

    public void updateTemplate(Template template) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(template);
        transaction.commit();
        session.close();
    }
    
    public void deleteTemplate(Template template) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(template);
        transaction.commit();
        session.close();
    }
}
