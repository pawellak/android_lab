package pl.pawel.mybucketlist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MountainsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mountains);
        setupList();
    }

    private void setupList() {
        BucketListEntry[] mountainsEntry = {
                new BucketListEntry("Rysy", "Polish and Slovak name Rysy, meaning \"scratches\".", R.drawable.rysy, 4.5f),
                new BucketListEntry("Babia Góra", "Gentle from the south, steep from the north.", R.drawable.babia, 5.0f),
                new BucketListEntry("Śnieżka", "The first recorded German name was Riseberg", R.drawable.sniezka, 4.0f),
                new BucketListEntry("Śnieżnik", "Śnieżnik derives from the word for \"snow\"", R.drawable.snieznik, 5.0f),
                new BucketListEntry("Tarnica", "Tarnica is a peak in the Bieszczady Mountains", R.drawable.tarnica, 4.5f),
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mountains);
        BucketListEntryAdapter adapter = new BucketListEntryAdapter(mountainsEntry);
        recyclerView.setAdapter(adapter);
    }
}
