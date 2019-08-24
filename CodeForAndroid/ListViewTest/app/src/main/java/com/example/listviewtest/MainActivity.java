package com.example.listviewtest;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {
            "A","B","C","D","E","F","G","H","I","J","K","A","B","C","D","E","F","G","H","I","J","K"
    };
    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initFruits(){
        for(int i = 0;i < 2;i++){
            Fruit fruit1 = new Fruit("A",R.drawable.pic1);
            fruitList.add(fruit1);
            Fruit fruit2 = new Fruit("B",R.drawable.pic2);
            fruitList.add(fruit2);
            Fruit fruit3 = new Fruit("C",R.drawable.pic3);
            fruitList.add(fruit3);
            Fruit fruit4 = new Fruit("D",R.drawable.pic4);
            fruitList.add(fruit4);
            Fruit fruit5 = new Fruit("E",R.drawable.pic5);
            fruitList.add(fruit5);
            Fruit fruit6 = new Fruit("F",R.drawable.pic6);
            fruitList.add(fruit6);
            Fruit fruit7 = new Fruit("G",R.drawable.pic7);
            fruitList.add(fruit7);
            Fruit fruit8 = new Fruit("H",R.drawable.pic8);
            fruitList.add(fruit8);
            Fruit fruit9 = new Fruit("I",R.drawable.pic9);
            fruitList.add(fruit9);
            Fruit fruit10 = new Fruit("J",R.drawable.pic10);
            fruitList.add(fruit10);
            Fruit fruit11 = new Fruit("K",R.drawable.pic11);
            fruitList.add(fruit11);

        }
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
    private int resourceID;
    public FruitAdapter(Context context, int textViewResourceID, List<Fruit> objectes)
    {
        super(context,textViewResourceID,objectes);
        resourceID = textViewResourceID;
    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageID());
        fruitName.setText(fruit.getName());
        return view;
    }
}
