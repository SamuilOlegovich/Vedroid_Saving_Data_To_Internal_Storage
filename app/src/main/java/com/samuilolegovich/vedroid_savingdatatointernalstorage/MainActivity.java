package com.samuilolegovich.vedroid_savingdatatointernalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView ) findViewById(R.id.textView);
    }

    public void read(View view) {
        // позволит читать какие либо файлы во внутреннем хранилище
        // открываем файл
        // читаем файл
        //
        try {
            FileInputStream fileOutputStream = openFileInput("example.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileOutputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;

            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines);
                stringBuffer.append("\n");
            }

            textView.setText(stringBuffer.toString());



            inputStreamReader.close();
            fileOutputStream.close();
            bufferedReader.close();
            // для того чтобы очистить после записи поле с текстом
            editText.setText("");
            Toast.makeText(MainActivity.this, "Текст успешно сохранет", Toast.LENGTH_LONG).show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void write(View view) {
        String myText = editText.getText().toString();
        // позволит помещать какие либо файлы во внутренне хранилище
        // файл куда писать, модификатор файла - в данном случаи обозначаед,
        // что ни одно другое приложение не имеет доступ к данному файлу
        try {
            FileOutputStream fileOutputStream = openFileOutput("example.txt", MODE_PRIVATE);
            fileOutputStream.write(myText.getBytes());
            fileOutputStream.close();
            // для того чтобы очистить после записи поле с текстом
            editText.setText("");
            Toast.makeText(MainActivity.this, "Текст успешно сохранет", Toast.LENGTH_LONG).show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}