package com.android2ee.formation.init.firsttoulouseproject;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.android2ee.formation.init.firsttoulouseproject.arrayadapter.HumanAdapter;
import com.android2ee.formation.init.firsttoulouseproject.model.Human;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String EDT_VALUE = "EDT_VALUE";
    public static final String TXV_RESULT = "TXV_RESULT";
    /*******************************************************************/
    /*******************************************************************/
    /***********Attribute                    *****************/
    /*******************************************************************/
    /**
     *The Add Button
     */
    Button btnAdd;
    /**
     * The EditText to add String
     */
    EditText edtMessage;
    /**
     * The ListView Result
     */
//    ListView lsvResult;

    /**
     * The ListView Result
     */
    GridView gdvResult;
    /**
     * The ArrayAdaptyer of the listView
     */
    HumanAdapter arrayAdapter;
    /**
     * The items displayed by the listView
     */
    ArrayList<Human> items;
    /*******************************************************************/
    /*******************************************************************/
    /***********Managing Life cycle                    *****************/
    /*******************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instanciate the view
        setContentView(R.layout.activity_main);
        //instanciate the components
        btnAdd= (Button) findViewById(R.id.btnAdd);
        edtMessage= (EditText) findViewById(R.id.edtMessage);
        //instanciate the listView
//        lsvResult = (ListView) findViewById(R.id.lsvResult);
        gdvResult = (GridView) findViewById(R.id.lsvResult);
        items=new ArrayList<Human>();
        Human pipo;
        for(int i=0;i<1000;i++){
            pipo=new Human("I am the "+i+"° hum",i);
            items.add(pipo);
        }
        arrayAdapter=new HumanAdapter(this,items);
        gdvResult.setAdapter(arrayAdapter);
        //add listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
            }
        });

    }

    public MainActivity() {
        super();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edtMessage.setText(savedInstanceState.getString(EDT_VALUE));
        items.clear();
        for(Parcelable toto :savedInstanceState.getParcelableArrayList(TXV_RESULT)){
            items.add((Human) toto);
        }
        arrayAdapter.notifyDataSetChanged();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDT_VALUE,edtMessage.getText().toString());
        outState.putParcelableArrayList(TXV_RESULT, items);
    }
/*******************************************************************/
    /*******************************************************************/
    /***********Managing Menu                          *****************/
    /*******************************************************************/
    /**
     * A temp var to avoid creating unecessary objects
     */
    String messageTemp;
    Human humanTemp;
    /**
     * The method called when adding the Edt content in the result
     */
    private void addMessage(){
        messageTemp=edtMessage.getText().toString();
        humanTemp=new Human(messageTemp,items.size());
        //1°case
//        arrayAdapter.add(messageTemp);
        //2° case
        items.add(humanTemp);
        arrayAdapter.notifyDataSetChanged();
        edtMessage.setText("");
    }


    /*******************************************************************/
    /*******************************************************************/
    /***********Managing Menu                          *****************/
    /*******************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
