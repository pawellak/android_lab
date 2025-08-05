package pl.pawel.mybucketlist;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupClickListeners();
    }

    private void setupClickListeners() {

        findViewById(R.id.card_view_food).setOnClickListener(v -> {
            Intent inset = new Intent(this, FoodActivity.class);
            startActivity(inset);
        });

        findViewById(R.id.card_view_mountain).setOnClickListener(v -> {
            Intent inset = new Intent(this, MountainsActivity.class);
            startActivity(inset);
        });
    }
}
