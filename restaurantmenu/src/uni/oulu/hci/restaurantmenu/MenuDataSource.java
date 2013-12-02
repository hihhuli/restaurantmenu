package uni.oulu.hci.restaurantmenu;

import java.io.Serializable;
import java.util.ArrayList;
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
            MySQLiteHelper.COLUMN_CATEGORY};
    
    public MenuDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    
    public void close() {
        dbHelper.close();
    }
    
    public MenuItem createMenuItem(String name, String diets, double price, String category) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE, name);
        values.put(MySQLiteHelper.COLUMN_DIETS, diets);
        values.put(MySQLiteHelper.COLUMN_PRICE, price);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
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
        menuItem.setPrice(cursor.getInt(3));
        menuItem.setCategory(cursor.getString(4));
        return menuItem;
    }
}
