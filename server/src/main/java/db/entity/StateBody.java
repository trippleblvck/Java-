package db.entity;

public class StateBody {
    
    private int id;
    private String name;
    private String communication;
    private float term;
    
    public float getTerm() {
        return term;
    }
    
    public void setTerm(float term) {
        this.term = term;
    }
    
    public String getCommunication() {
        return communication;
    }
    
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
}
