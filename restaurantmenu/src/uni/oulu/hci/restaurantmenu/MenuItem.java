package uni.oulu.hci.restaurantmenu;

public class MenuItem {
    
    private long id;
    private String title;
    private String diets;
    private double price;
    private String category;
    private String description;
    private String spiciness;
    private String image;
    private String ingredients;
    private String ingredientOptions;
    private String dietOptions;
    private String size;
    private int likes;
    
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getSpiciness() {
        return spiciness;
    }
    
    public void setSpiciness(String spiciness) {
        this.spiciness = spiciness;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    public String getIngredientOptions() {
        return ingredientOptions;
    }
    
    public void setIngredientOptions(String ingredientOptions) {
        this.ingredientOptions = ingredientOptions;
    }
    
    public String getDietOptions() {
        return dietOptions;
    }
    
    public void setDietOptions(String dietOptions) {
        this.dietOptions = dietOptions;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public int getLikes() {
        return likes;
    }
    
    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
