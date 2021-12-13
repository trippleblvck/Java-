package db.service;

import com.google.gson.Gson;
import db.entity.Document;
import db.entity.DocumentForView;
import db.entity.Person;
import db.entity.Template;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static app.ClientSocket.sendData;
import static app.Main.sessionFactory;

public class DocumentService {
    
    public void getClient(int userId) {
        Gson gson = new Gson();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Person client = (Person) session.createQuery("FROM Person WHERE user = " + userId).uniqueResult();
        transaction.commit();
        session.close();
        sendData(gson.toJson(client));
    }
    
    public void selectClientDocuments(int clientId) {
        TemplateService templateService = new TemplateService();
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List documents = session.createQuery("FROM Document WHERE client = " + clientId).list();
        transaction.commit();
        session.close();
        
        List<DocumentForView> documentForViewList = new ArrayList<>();
        for (Object document : documents) {
            Document doc = (Document) document;
            Template template = templateService.getTemplate(doc.getTemplate());
            documentForViewList.add(doc.castToDocumentForView(template));
        }
        
        sendData(new Gson().toJson(documentForViewList));
    }
    
    public void addDocument(Document document) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(document);
        transaction.commit();
        session.close();
    }
    
    public void updateDocument(Document document) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(document);
        transaction.commit();
        session.close();
    }
    
    public void deleteDocument(Document document) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(document);
        transaction.commit();
        session.close();
    }
    
}
