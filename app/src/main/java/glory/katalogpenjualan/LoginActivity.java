package glory.katalogpenjualan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Adapter.DataHelper;

public class LoginActivity extends AppCompatActivity {

    Intent i;
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText nama, pass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.etuser);
        pass = (EditText) findViewById(R.id.etpass);

    }

    public void klikLogin(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_sales",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);


            if(cursor.getString(1).toString().equals(nama.getText().toString()) && cursor.getString(1).toString().equals(pass.getText().toString())){
                i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(), "Username / Password anda salah!!", Toast.LENGTH_LONG).show();
            }

        }



    }


}
