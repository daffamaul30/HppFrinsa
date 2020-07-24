package frinsa.hpp.data;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ajts.androidmads.library.SQLiteToExcel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class ExportExcel extends AppCompatActivity {
    Context context ;
    public ExportExcel(Context context){
        this.context = context;
        System.out.println("CONSTRUCT");
    }
    public void ExportData() {

        //CHECK IF YOU HAVE WRITE PERMISSIONS OR RETURN
        int permission = ActivityCompat.checkSelfPermission(this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this.context, "Storage permissions not granted", Toast.LENGTH_SHORT).show();
            return;
        }

//get database object

//        DBPanen myDbhelper = new DBPanen(this.context);
//        SQLiteDatabase database = myDbhelper.getWritableDatabase();
//        System.out.println("DB");
//delete all entries in the second table
        //database.delete("Table2",null,null);

//Create a cursor of the main database with your filters and sort order applied

        //Create a cursor of the main database with your filters and sort order applied
//        Cursor cursor = getActivity().getContentResolver().query(
//                uri,
//                projections,
//                selection,
//                args,
//                sortOrder);
//
////loop through cursor and add entries from first table to second table
//        try {
//            while (cursor.moveToNext()) {
//                final String ColumnOneIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_ONE"));
//                final String ColumnTwoIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_TWO"));
//                final String ColumnThreeIndex = cursor.getString(cursor.getColumnIndexOrThrow("COLUMN_THREE"));
//
//                //add entries from table one into the table two
//                ContentValues values = new ContentValues();
//                values.put("TABLE2_COLUMN_1", ColumnOneIndex);
//                values.put("TABLE2_COLUMN_2", ColumnTwoIndex );
//                values.put("TABLE2_COLUMN_3", ColumnThreeIndex);
//
//                database.insert("table2", null, values);
//            }
//        } finally {
//            //close cursor after looping is complete
//            cursor.close();
//        }

        //create a string for where you want to save the excel file
        final String savePath = Environment.getExternalStorageDirectory()+"/exported";

        File file = new File(savePath);
        if (!file.exists()) {
            boolean dirCreated = file.mkdirs();
            System.out.println(dirCreated);
            System.out.println(savePath);
        }
        System.out.println("BARIS 62");
        //create the sqLiteToExcel object
        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(this.context, "MyDB",savePath);
        System.out.println("BARIS 64");
//use sqLiteToExcel object to create the excel file
        sqLiteToExcel.exportAllTables("excelfilename.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }
            @Override
            public void onCompleted(String filePath) {
                //now attach the excel file created and be directed to email activity
                System.out.println("KELAR");
                Toast.makeText(context, "DATA EXPORTED TO"+savePath+"/excelfilename.xls", Toast.LENGTH_LONG).show();
//                Uri newPath = Uri.parse("file://" + savePath + "/" +"excelfilename.xls");
//
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//
//                Intent emailintent = new Intent(Intent.ACTION_SEND);
//
//                emailintent.setType("application/vnd.ms-excel");
//                emailintent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                emailintent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
//                emailintent.putExtra(Intent.EXTRA_STREAM,newPath);
//
//                startActivity(Intent.createChooser(emailintent, "Send Email"));
            }
            @Override
            public void onError(Exception e) {
                System.out.println("Error msg: " + e);
                //Toast.makeText(this.context, "Failed to Export data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}




