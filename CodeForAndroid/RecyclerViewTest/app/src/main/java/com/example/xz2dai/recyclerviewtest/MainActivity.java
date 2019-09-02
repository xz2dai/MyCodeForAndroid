 package com.example.xz2dai.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {
     private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
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
