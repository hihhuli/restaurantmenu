package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

@SuppressWarnings("serial")
public class MenuDataSource implements Serializable {
    
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE,
            MySQLiteHelper.COLUMN_DIETS,
            MySQLiteHelper.COLUMN_PRICE,
            MySQLiteHelper.COLUMN_CATEGORY,
            MySQLiteHelper.COLUMN_DESCRIPTION,
            MySQLiteHelper.COLUMN_SPICINESS,
            MySQLiteHelper.COLUMN_IMAGE,
            MySQLiteHelper.COLUMN_INGREDIENTS,
            MySQLiteHelper.COLUMN_INGREDIENT_OPTIONS,
            MySQLiteHelper.COLUMN_DIET_OPTIONS,
            MySQLiteHelper.COLUMN_SIZE,
            MySQLiteHelper.COLUMN_LIKES};
    
    public MenuDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    
    public void close() {
        dbHelper.close();
    }
    
    public MenuItem createMenuItem(String name,
            String diets,
            double price,
            String category,
            String description,
            String spiciness,
            String image,
            String ingredients,
            String ingredientOptions,
            String dietOptions,
            String size,
            int likes) {
        ContentValues values = new ContentValues();
        
        values.put(MySQLiteHelper.COLUMN_TITLE, name);
        values.put(MySQLiteHelper.COLUMN_DIETS, diets);
        values.put(MySQLiteHelper.COLUMN_PRICE, price);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_SPICINESS, spiciness);
        values.put(MySQLiteHelper.COLUMN_IMAGE, image);
        values.put(MySQLiteHelper.COLUMN_INGREDIENTS, ingredients);
        values.put(MySQLiteHelper.COLUMN_INGREDIENT_OPTIONS, ingredientOptions);
        values.put(MySQLiteHelper.COLUMN_DIET_OPTIONS, dietOptions);
        values.put(MySQLiteHelper.COLUMN_SIZE, size);
        values.put(MySQLiteHelper.COLUMN_LIKES, likes);
        
        long insertId = database.insert(MySQLiteHelper.TABLE_MENU, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MENU,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        MenuItem newMenuItem = cursorToMenuItem(cursor);
        cursor.close();
        return newMenuItem;
    }
    
    public void deleteMenuItem(MenuItem menuItem) {
        long id = menuItem.getId();
        System.out.println("MenuItem deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MENU, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }
    
    public void deleteAllMenuItems() {
        database.delete(MySQLiteHelper.TABLE_MENU, null, null);
    }
    
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MENU,
                allColumns, null, null, null, null, null);
        
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MenuItem menuItem = cursorToMenuItem(cursor);
            menuItems.add(menuItem);
            cursor.moveToNext();
        }
        cursor.close();
        return menuItems;
    }
    
    public HashMap<String, List<MenuItem>> getMenuItemMap() {
        HashMap<String, List<MenuItem>> menuItems = new HashMap<String, List<MenuItem>>();
        
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MENU,
                allColumns, null, null, null, null, null);
        
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MenuItem menuItem = cursorToMenuItem(cursor);
            String category = menuItem.getCategory();
            if (!menuItems.containsKey(category)) {
                menuItems.put(category, new ArrayList<MenuItem>());
            }
            menuItems.get(category).add(menuItem);
            cursor.moveToNext();
        }
        cursor.close();
        return menuItems;
    }
    
    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MENU,
                allColumns, null, null, null, null, null);
        
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MenuItem menuItem = cursorToMenuItem(cursor);
            if(category.equals(menuItem.getCategory())) {
                menuItems.add(menuItem);
            }
            cursor.moveToNext();
        }
        cursor.close();
        return menuItems;
    }
    
    private MenuItem cursorToMenuItem(Cursor cursor) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(cursor.getLong(0));
        menuItem.setTitle(cursor.getString(1));
        menuItem.setDiets(cursor.getString(2));
        menuItem.setPrice(cursor.getDouble(3));
        menuItem.setCategory(cursor.getString(4));
        menuItem.setDescription(cursor.getString(5));
        menuItem.setSpiciness(cursor.getString(6));
        menuItem.setImage(cursor.getString(7));
        menuItem.setIngredients(cursor.getString(8));
        menuItem.setIngredientOptions(cursor.getString(9));
        menuItem.setDietOptions(cursor.getString(10));
        menuItem.setSize(cursor.getString(11));
        menuItem.setLikes(cursor.getInt(12));
        return menuItem;
    }
}
