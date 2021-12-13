package javaСode.entity;

public class Document {
    
    private int id;
    private String name;
    private int template;
    private String compiler;
    private int count;
    private float price;
    private float term;
    private int client;
    
    public int getClient() {
        return client;
    }
    
    public void setClient(int client) {
        this.client = client;
    }
    
    public float getTerm() {
        return term;
    }
    
    public void setTerm(float term) {
        this.term = term;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public String getCompiler() {
        return compiler;
    }
    
    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }
    
    public int getTemplate() {
        return template;
    }
    
    public void setTemplate(int template) {
        this.template = template;
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
