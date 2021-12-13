package db.entity;

public class DocumentForView {
    
    private int id;
    private String name;
    private Template template;
    private String templateNameCast;
    private String compiler;
    private int count;
    private float price;
    private float term;
    private int client;
    
    public String getTemplateNameCast() {
        return templateNameCast;
    }
    
    public void setTemplateNameCast(String templateNameCast) {
        this.templateNameCast = templateNameCast;
    }
    
    public String getTemplateName(){
        return template != null ? template.getName() : "";
    }
    
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
    
    public Template getTemplate() {
        return template;
    }
    
    public void setTemplate(Template template) {
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
    
    public Document castToDocument(){
        Document documentForView = new Document();
        documentForView.setId(id);
        documentForView.setName(name);
        documentForView.setTemplate(template.getId());
        documentForView.setCompiler(compiler);
        documentForView.setCount(count);
        documentForView.setPrice(price);
        documentForView.setTerm(term);
        documentForView.setClient(client);
        return documentForView;
    }
    
}
