package app;

import com.google.gson.Gson;
import db.entity.Document;
import db.entity.DocumentForView;
import db.entity.Person;
import db.entity.PersonForView;
import db.entity.StateBody;
import db.entity.Template;
import db.entity.User;
import db.service.*;

import java.io.IOException;
import java.sql.SQLException;

public class RequestHandler {
    
    public void accept(String command)
        throws SQLException, IOException, ClassNotFoundException {
        Gson gson = new Gson();
        UserService userService = new UserService();
        ClientService clientService = new ClientService();
        TemplateService templateService = new TemplateService();
        StateBodyService stateBodyService = new StateBodyService();
        DocumentService documentService = new DocumentService();
        
        String userJson, personJson, personForViewJson, templateJson, stateBodyJson, documentJson, documentForViewJson;
        User user;
        Person person;
        PersonForView personForView;
        Template template;
        StateBody stateBody;
        Document document;
        DocumentForView documentForView;
        
        switch (command) {
            case "Авторизация":
                userJson = (String) ClientSocket.in.readObject();
                userService.getUser(userJson);
                break;
            
            case "Все клиенты":
                clientService.selectAllClients();
                break;
            case "Все работники":
                clientService.selectAllWorkers();
                break;
            case "Add person":
                userJson = (String) ClientSocket.in.readObject();
                user = gson.fromJson(userJson, User.class);
    
                personJson = (String) ClientSocket.in.readObject();
                person = gson.fromJson(personJson, Person.class);
                
                userService.addUser(user, person);
                break;
            case "Edit person":
                personForViewJson = (String) ClientSocket.in.readObject();
                personForView = gson.fromJson(personForViewJson, PersonForView.class);
                
                userService.updateUser(personForView.getUser());
                clientService.updatePerson(personForView.castToPerson());
                break;
            case "Delete person":
                userJson = (String) ClientSocket.in.readObject();
                user = gson.fromJson(userJson, User.class);
                userService.deleteUser(user);
                break;
            case "Lock/unlock":
                userJson = (String) ClientSocket.in.readObject();
                user = gson.fromJson(userJson, User.class);
                userService.updateStatus(user);
                break;
            
            case "Все шаблоны":
                templateService.selectAllTemplates();
                break;
            case "Add template":
                templateJson = (String) ClientSocket.in.readObject();
                template = gson.fromJson(templateJson, Template.class);
                templateService.addTemplate(template);
                break;
            case "Edit template":
                templateJson = (String) ClientSocket.in.readObject();
                template = gson.fromJson(templateJson, Template.class);
                templateService.updateTemplate(template);
                break;
            case "Delete template":
                templateJson = (String) ClientSocket.in.readObject();
                template = gson.fromJson(templateJson, Template.class);
                templateService.deleteTemplate(template);
                break;
            
            case "Все органы":
                stateBodyService.selectAllStateBodies();
                break;
            case "Add body":
                stateBodyJson = (String) ClientSocket.in.readObject();
                stateBody = gson.fromJson(stateBodyJson, StateBody.class);
                stateBodyService.addStateBody(stateBody);
                break;
            case "Edit body":
                stateBodyJson = (String) ClientSocket.in.readObject();
                stateBody = gson.fromJson(stateBodyJson, StateBody.class);
                stateBodyService.updateStateBody(stateBody);
                break;
            case "Delete body":
                stateBodyJson = (String) ClientSocket.in.readObject();
                stateBody = gson.fromJson(stateBodyJson, StateBody.class);
                stateBodyService.deleteStateBody(stateBody);
                break;
            
            case "Мои документы":
                personForViewJson = (String) ClientSocket.in.readObject();
                personForView = gson.fromJson(personForViewJson, PersonForView.class);
                documentService.selectClientDocuments(personForView.getId());
                break;
            case "Add document":
                documentForViewJson = (String) ClientSocket.in.readObject();
                documentForView = gson.fromJson(documentForViewJson, DocumentForView.class);
                
                documentForView.setTemplate(templateService.getTemplateByName(documentForView));
                documentService.addDocument(documentForView.castToDocument());
                break;
            case "Edit document":
                documentForViewJson = (String) ClientSocket.in.readObject();
                documentForView = gson.fromJson(documentForViewJson, DocumentForView.class);
                
                documentForView.setTemplate(templateService.getTemplateByName(documentForView));
                documentService.updateDocument(documentForView.castToDocument());
                break;
            case "Delete document":
                documentJson = (String) ClientSocket.in.readObject();
                document = gson.fromJson(documentJson, Document.class);
                documentService.deleteDocument(document);
                break;
            
            default:
                System.out.print("Неизвестная команда: ");
                System.err.println(command);
                break;
        }
    }
    
}