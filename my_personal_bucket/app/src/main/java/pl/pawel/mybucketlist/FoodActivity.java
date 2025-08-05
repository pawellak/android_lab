package pl.pawel.mybucketlist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foods);
        setupList();
    }

    private void setupList() {
        BucketListEntry[] foodEntry = {
                new BucketListEntry("Beans", "Beans are the seeds", R.drawable.beans, 5.0f),
                new BucketListEntry("Cucumbers", "Cucumbers, botanically a fruit", R.drawable.cucumbers, 4.5f),
                new BucketListEntry("Lettuce", "Lactuca, commonly known as lettuce,", R.drawable.lettuce, 4.0f),
                new BucketListEntry("Carrot", "Carrots are a versatile, nutritious root vegetable", R.drawable.carrots, 4.0f),
                new BucketListEntry("Tomatoes", "Solanum lycopersicum, is a plant whose fruit is an edible", R.drawable.tomatoes, 5.0f),
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mountains);
        BucketListEntryAdapter adapter = new BucketListEntryAdapter(foodEntry);
        recyclerView.setAdapter(adapter);
    }
}
