package com.example.stu.library;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TJshujiActivity extends AppCompatActivity {
    EditText edtTxtnumber,edtTxtname,edtTxtzuozhe,edtTxtchubanshe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjshuji);

        edtTxtnumber=findViewById(R.id.edt_TJ_number);
        edtTxtname=findViewById(R.id.edt_TJ_name);
        edtTxtzuozhe=findViewById(R.id.edt_TJ_zuozhe);
        edtTxtchubanshe=findViewById(R.id.edt_TJ_chubanshe);

        findViewById(R.id.btn_TJ_tianjia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long number = null;
               if (edtTxtnumber.getText().toString().length()>0){
                  number=Long.valueOf(edtTxtnumber.getText().toString().trim());
               }

                String name=edtTxtname.getText().toString().trim();
                String zuozhe=edtTxtzuozhe.getText().toString().trim();
                String chubanshe=edtTxtchubanshe.getText().toString().trim();
                Infor infor=new Infor();
                infor.setNumber(number);
                infor.setName(name);
                infor.setZuozhe(zuozhe);
                infor.setChubanshe(chubanshe);
                Adapter adapter=new Adapter(getApplicationContext());
                adapter.insert(infor);

            }
        });
        findViewById(R.id.btn_TJ_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Myhelper myHelper=new Myhelper(getApplicationContext(),"database.db",null,1);
        SQLiteDatabase db=myHelper.getWritableDatabase();

    }
}
