package com.shubham.tictacarena.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shubham.tictacarena.R;
import com.shubham.tictacarena.adapters.PatternAdapter;
import com.shubham.tictacarena.models.Pattern;
import com.shubham.tictacarena.utils.PatternGenerator;
import java.util.List;

public class PatternsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patterns);

        boolean isAI = getIntent().getBooleanExtra("isAI", false);
        RecyclerView patternsRecycler = findViewById(R.id.patternsRecycler);
        patternsRecycler.setLayoutManager(new GridLayoutManager(this, 2));

        List<Pattern> patterns = PatternGenerator.getAllPatterns();
        PatternAdapter adapter = new PatternAdapter(patterns, pattern -> {
            Intent intent = new Intent(PatternsActivity.this, GameActivity.class);
            intent.putExtra("pattern", pattern.getName());
            intent.putExtra("isAI", isAI);
            startActivity(intent);
        });
        patternsRecycler.setAdapter(adapter);
    }
}