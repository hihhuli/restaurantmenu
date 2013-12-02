package uni.oulu.hci.restaurantmenu;

public class MenuItem {
    
    private long id;
    private String title;
    private String diets;
    private double price;
    private String category;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDiets() {
        return diets;
    }
    
    public void setDiets(String diets) {
        this.diets = diets;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
