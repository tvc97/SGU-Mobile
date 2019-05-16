package com.pqt.cookingebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public static String MESSAGE_URL_DETAIL = "MESSAGE_URL_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> listData = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, listData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Item item = (Item) o;

                Log.e("log", item.getUrl());
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                detailIntent.putExtra(MESSAGE_URL_DETAIL, item.getUrl());

                startActivity(detailIntent);
            }
        });
    }

    private List<Item> getListData() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/5/11/0/CCMIN109_Zucchini-Pear-Soup_s4x3.jpg.rend.hgtvcom.616.462.suffix/1357781209709.jpeg",
                "Zucchini-Pear Soup",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/5/11/0/CCMIN109_Zucchini-Pear-Soup_s4x3.jpg.rend.hgtvcom.616.462.suffix/1357781209709.jpeg",
                "https://www.cookingchanneltv.com/recipes/mark-bittman/zucchini-pear-soup-1961269"));
        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/13/0/CC-ching-he-huang_yangzhou-fried-rice-recipe_s4x3.jpg.rend.hgtvcom.616.462.suffix/1360706912125.jpeg",
                "Yangzhou Fried Rice",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/13/0/CC-ching-he-huang_yangzhou-fried-rice-recipe_s4x3.jpg.rend.hgtvcom.616.462.suffix/1360706912125.jpeg",
                "https://www.cookingchanneltv.com/recipes/ching-he-huang/yangzhou-fried-rice-1961585"));
        items.add(new Item("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2017/2/8/0/CCTIF306H_Cauliflower-Fried-Rice_s4x3.jpg.rend.hgtvcom.616.462.suffix/1486567665432.jpeg",
                "Cauliflower Fried Rice",
                "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2017/2/8/0/CCTIF306H_Cauliflower-Fried-Rice_s4x3.jpg.rend.hgtvcom.616.462.suffix/1486567665432.jpeg",
                "https://www.cookingchanneltv.com/recipes/tiffani-thiessen/cauliflower-fried-rice-3629531"));


        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/7/0/CCECC111_Steamed-Sea-Bass-with-Ginger-and-Chinese-Mushrooms-Recipe-01_s4x3.jpg.rend.hgtvcom.616.462.suffix/1351632819905.jpeg",
                "Steamed Sea Bass with Ginger and Chinese Mushrooms\n",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/7/0/CCECC111_Steamed-Sea-Bass-with-Ginger-and-Chinese-Mushrooms-Recipe-01_s4x3.jpg.rend.hgtvcom.616.462.suffix/1351632819905.jpeg",
                "https://www.cookingchanneltv.com/recipes/ching-he-huang/steamed-sea-bass-with-ginger-and-chinese-mushrooms-2042296"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/1/12/0/CCBGF109_baked-chinese-rice-with-peas-and-ginger_s4x3.jpg.rend.hgtvcom.616.462.suffix/1357777272338.jpeg",
                "Baked Chinese Rice with Peas and Ginger",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/1/12/0/CCBGF109_baked-chinese-rice-with-peas-and-ginger_s4x3.jpg.rend.hgtvcom.616.462.suffix/1357777272338.jpeg",
                "https://www.cookingchanneltv.com/recipes/bill-granger/baked-chinese-rice-with-peas-and-ginger-1959076"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/8/16/0/latin_arroz_pollo.jpg.rend.hgtvcom.616.462.suffix/1360188610754.jpeg",
                "Arroz con Pollo",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/8/16/0/latin_arroz_pollo.jpg.rend.hgtvcom.616.462.suffix/1360188610754.jpeg",
                "https://www.cookingchanneltv.com/recipes/ingrid-hoffmann/arroz-con-pollo-1952724"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/7/4/0/VD0106-1_Chicken-with-Rice_s4x3.jpg.rend.hgtvcom.616.462.suffix/1359277785786.jpeg",
                "Chicken with Rice (Arroz con Pollo)",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/7/4/0/VD0106-1_Chicken-with-Rice_s4x3.jpg.rend.hgtvcom.616.462.suffix/1359277785786.jpeg",
                "https://www.cookingchanneltv.com/recipes/daisy-martinez/chicken-with-rice-arroz-con-pollo-1945235"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/23/0/CCUTG210_Chicken-Cacciatore_s4x3.jpg.rend.hgtvcom.616.462.suffix/1416866881530.jpeg",
                "Chicken Cacciatore",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2011/12/23/0/CCUTG210_Chicken-Cacciatore_s4x3.jpg.rend.hgtvcom.616.462.suffix/1416866881530.jpeg",
                "https://www.cookingchanneltv.com/recipes/debi-mazar-and-gabriele-corcos/chicken-cacciatore-2106913"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/6/3/0/CCEDE105_trinidadian-style-chicken_s4x3.jpg.rend.hgtvcom.616.462.suffix/1351614279174.jpeg",
                "Trinidadian-Style Chicken",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/6/3/0/CCEDE105_trinidadian-style-chicken_s4x3.jpg.rend.hgtvcom.616.462.suffix/1351614279174.jpeg",
                "https://www.cookingchanneltv.com/recipes/roger-mooking/trinidadian-style-chicken-1959070"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/6/21/0/CCSPG109_South-Indian-Chicken_s4x3.jpg.rend.hgtvcom.616.462.suffix/1405528626860.jpeg",
                "Chicken South Indian Style",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/6/21/0/CCSPG109_South-Indian-Chicken_s4x3.jpg.rend.hgtvcom.616.462.suffix/1405528626860.jpeg",
                "https://www.cookingchanneltv.com/recipes/bal-arneson/chicken-south-indian-style-2010126"));

        items.add(new Item("https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/7/7/0/CCCBB102_Martinique-Coconut-Chicken-Curry_s4x3.jpg.rend.hgtvcom.616.462.suffix/1353954306243.jpeg",
                "Martinique Coconut Chicken Curry",
                "https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2010/7/7/0/CCCBB102_Martinique-Coconut-Chicken-Curry_s4x3.jpg.rend.hgtvcom.616.462.suffix/1353954306243.jpeg",
                "https://www.cookingchanneltv.com/recipes/levi-roots/martinique-coconut-chicken-curry-2010043"));


        return items;
    }
}
