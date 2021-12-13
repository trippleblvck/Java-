package db.entity;

public class PersonForView {
    
    private int id;
    private String name;
    private String passport;
    private String communication;
    private String address;
    private String type;
    private User user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
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
    
    public Person castToPerson(){
        Person person = new Person();
        person.setId(getId());
        person.setAddress(getAddress());
        person.setUser(user.getId());
        person.setCommunication(getCommunication());
        person.setPassport(getPassport());
        person.setName(getName());
        person.setType(getType());
        return person;
    }
    
}
