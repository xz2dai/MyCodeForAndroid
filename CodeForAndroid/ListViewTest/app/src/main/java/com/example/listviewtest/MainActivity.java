package com.example.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] data = {
            "A","B","C","D","E","F","G","H","I","J","K","A","B","C","D","E","F","G","H","I","J","K"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}

class Fruit{
    private String name;
    private int imageID;
    public Fruit(String name,int imageID)
    {
        this.name = name;
        this.imageID = imageID;
    }
    public String getName(){
        return name;
    }
    public int getImageID(){
        return imageID;
    }

}

class FruitAdapter extends ArrayAdapter<Fruit>{
    private
}
