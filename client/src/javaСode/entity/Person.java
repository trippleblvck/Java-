package javaСode.entity;

public class Person {
    
    private int id;
    private String name;
    private String passport;
    private String communication;
    private String address;
    private String type;
    private int user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUser() {
        return user;
    }
    
    public void setUser(int user) {
        this.user = user;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCommunication() {
        return communication;
    }
    
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    
    public String getPassport() {
        return passport;
    }
    
    public void setPassport(String passport) {
        this.passport = passport;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public PersonForView castToPersonForView(User user){
        PersonForView personForView = new PersonForView();
        personForView.setId(getId());
        personForView.setAddress(getAddress());
        personForView.setUser(user);
        personForView.setCommunication(getCommunication());
        personForView.setPassport(getPassport());
        personForView.setName(getName());
        personForView.setType(getType());
        return personForView;
    }
    
}
