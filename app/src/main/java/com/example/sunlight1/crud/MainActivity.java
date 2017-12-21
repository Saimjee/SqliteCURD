package com.example.sunlight1.crud;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText txtName, txtSurName, txtMarks,idtxt;
    Button btninst,btnread,btndel,btnup;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);
        txtName=(EditText)findViewById(R.id.editText);
        idtxt=(EditText)findViewById(R.id.eidtId);
        txtSurName=(EditText)findViewById(R.id.editText2);
        txtMarks=(EditText)findViewById(R.id.editText3);
        txtResult=(TextView)findViewById(R.id.textView) ;
        btninst=(Button) findViewById(R.id.btninst);
        btnread=(Button) findViewById(R.id.btnrd);
        btndel=(Button) findViewById(R.id.btndel);
        btnup=(Button) findViewById(R.id.btnp);
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe4();

            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe3();

            }
        });

        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe2();

            }
        });
        btninst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

    }
    private void ClickMe() {
        String name = txtName.getText().toString();
        String surname = txtSurName.getText().toString();
        String marks = txtMarks.getText().toString();
        Boolean result = myDb.insertData(name, surname, marks);
        if (result == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void ClickMe2() {
        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if(res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0)+"\n");
                stringBuffer.append("Name: "+res.getString(1)+"\n");
                stringBuffer.append("Surname: "+res.getString(2)+"\n");
                stringBuffer.append("Marks: "+res.getString(3)+"\n"+"\n");
            }
            txtResult.setText(stringBuffer.toString());
            Toast.makeText(this,"Data Retrieved Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No Data to Retrieve",Toast.LENGTH_SHORT).show();
        }
}
    private void ClickMe3() {
        String id =idtxt .getText().toString();
        int result =myDb.deleteData(id);
        Toast.makeText(this,result+" :Rows Affected",Toast.LENGTH_SHORT).show();
    }
    private void ClickMe4() {
        String id = idtxt.getText().toString();
        String name = txtName.getText().toString();
        String surname = txtSurName.getText().toString();
        String marks = txtMarks.getText().toString();
        Boolean result = myDb.updateData(id,name,surname,marks);
        if(result==true){
            Toast.makeText(this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No Rows Affected",Toast.LENGTH_SHORT).show();
        }

    }

}
