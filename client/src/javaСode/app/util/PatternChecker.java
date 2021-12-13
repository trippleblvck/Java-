package javaСode.app.util;

import javaСode.entity.DocumentForView;
import javaСode.entity.Person;
import javaСode.entity.PersonForView;
import javaСode.entity.StateBody;
import javaСode.entity.Template;
import javaСode.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternChecker {
    
    public static String documentCheck(DocumentForView document){
        String result = "";
        
        if(document.getName().equals("") || document.getName() == null)
            result += "'Наименование документа'\n";
        
        if(document.getTemplateNameCast() == null)
            result += "'Шаблон'\n";
    
        if(document.getCompiler().equals("") || document.getCompiler() == null)
            result += "'Составитель'\n";
        
        if(document.getPrice() == 0)
            result+="'Стоимость'\n";
    
        if(document.getCount() == 0)
            result+="'Экземпляров'\n";
        
        return result;
    }
    
    public static String personCheck(Person person){
        String result = "";
        if(person.getName().equals("") || person.getName() == null)
            result += "'ФИО'\n";
        if(person.getPassport().equals("") || person.getPassport() == null)
            result += "'Паспорт'\n";
        if(person.getAddress().equals("") || person.getAddress() == null)
            result += "'Адрес'\n";
        if(person.getCommunication().equals("") || person.getCommunication() == null)
            result += "'Способ связи'\n";
        return result;
    }
    
    public static String userCheck(User user){
        String result = "";
        if(user.getLogin().equals("") || user.getLogin() == null)
            result += "'Логин'\n";
        if(user.getPassword().equals("") || user.getPassword() == null)
            result += "'Пароль'\n";
        return result;
    }
    
    public static String personForViewCheck(PersonForView personForView){
        return personCheck(personForView.castToPerson()) + userCheck(personForView.getUser());
    }
    
    public static String templateCheck(Template template){
        String result = "";
        if(template.getName().equals("") || template.getName() == null)
            result+="'Наименование документа'\n";
        if(template.getTerm() == 0)
            result+="'Время обработки'\n";
        return result;
    }
    
    public static String stateBodyCheck(StateBody stateBody){
        String result = "";
        if(stateBody.getName().equals("") || stateBody.getName() == null)
            result+="'Наименование'\n";
        if(stateBody.getTerm() == 0)
            result+="'Время обработки'\n";
        if(stateBody.getCommunication().equals("") || stateBody.getCommunication() == null)
            result+="'Способ связи'\n";
        return result;
    }
    
}
