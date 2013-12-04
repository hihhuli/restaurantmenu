package uni.oulu.hci.restaurantmenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    
    public static final String TABLE_MENU                = "menu";
    public static final String COLUMN_ID                 = "_id";
    public static final String COLUMN_TITLE              = "title";
    public static final String COLUMN_DIETS              = "diets";
    public static final String COLUMN_PRICE              = "price";
    public static final String COLUMN_CATEGORY           = "category";
    public static final String COLUMN_DESCRIPTION        = "description";
    public static final String COLUMN_SPICINESS          = "spiciness";
    public static final String COLUMN_IMAGE              = "image";
    public static final String COLUMN_INGREDIENTS        = "ingredients";
    public static final String COLUMN_INGREDIENT_OPTIONS = "ingredient_options";
    public static final String COLUMN_DIET_OPTIONS       = "diet_options";
    public static final String COLUMN_SIZE               = "size";
    private static final String DATABASE_NAME            = "menu.db";
    private static final int DATABASE_VERSION            = 2;
    
    private static final String DATABASE_CREATE = "create table " + TABLE_MENU
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_DIETS + " text not null, "
            + COLUMN_PRICE + " real, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DESCRIPTION + " text not null, "
            + COLUMN_SPICINESS + " text not null, "
            + COLUMN_IMAGE + " text not null, "
            + COLUMN_INGREDIENTS + " text not null, "
            + COLUMN_INGREDIENT_OPTIONS + " text not null, "
            + COLUMN_DIET_OPTIONS + " text not null, "
            + COLUMN_SIZE + " text not null"
            + ");";
    
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(database);
    }
}
