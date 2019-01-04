package com.example.stu.library;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChaxunActivity extends AppCompatActivity {
    EditText edttiaojian;
    ListView lvdisplay;
    List<Infor> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaxun);
        edttiaojian = findViewById(R.id.edt_cx_tiaojian);
        lvdisplay = findViewById(R.id.lv_cx_display);
        final EditText edt_tiaojian = findViewById(R.id.edt_cx_tiaojian);


        findViewById(R.id.btn_cx_shanchu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edttiaojian.getText().toString().trim();
                String number=edttiaojian.getText().toString().trim();
                Adapter adapter=new Adapter(getApplicationContext());
                int num= 0;
                num=adapter.delete(name);

                Toast.makeText(getApplicationContext(),"删除记录条数："+String.valueOf(num),
                        Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.btn_cx_chaxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tiaojian = edt_tiaojian.getText().toString().trim();
                Adapter adapter = new Adapter(getApplicationContext());
                List<Infor> infors = adapter.queryAll(tiaojian);
                lvdisplay.setAdapter(new MyAdpater(infors));

            }
        });

        findViewById(R.id.btn_cx_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Myhelper myhelper=new Myhelper(getApplicationContext(),"database.db",null,1);
        SQLiteDatabase db=myhelper.getWritableDatabase();




    }



    class MyAdpater extends BaseAdapter {
        List<Infor> list;

        public MyAdpater(List<Infor> infors) {
            this.list = infors;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.list_item, null);
            TextView tvnumber = view.findViewById(R.id.tv_listitem_number);
            TextView tvname = view.findViewById(R.id.tv_listitem_name);
            TextView tvzuozhe = view.findViewById(R.id.tv_listitem_zuozhe);
            TextView tvchubanshe = view.findViewById(R.id.tv_listitem_chubanshe);

            Infor infor = (Infor) getItem(position);
            tvnumber.setText(String.valueOf(infor.getNumber()));
            tvname.setText(infor.getName());
            tvzuozhe.setText(infor.getZuozhe());
            tvchubanshe.setText(infor.getChubanshe());

            return view;
        }
    }
}
