package com.jesica.puzzle;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[4][4];
    private int emptyX = 3, emptyY = 3;
    private String[] buttonIDs = {
            "tileA", "tileB", "tileC", "tileD",
            "tileE", "tileF", "tileG", "tileH",
            "tileI", "tileJ", "tileK", "tileL",
            "tileM", "tileN", "tileO", "tile_empty"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int resID = getResources().getIdentifier(buttonIDs[k], "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(buttonClickListener);
                k++;
            }
        }

        shuffleTiles();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_repeat) {
            shuffleTiles();
            return true;
        } else {
            finish();
            return true;
        }

    }


    private void shuffleTiles() {
        List<String> letters = new ArrayList<>();
        for (char c = 'A'; c <= 'O'; c++) {
            letters.add(String.valueOf(c));
        }

        Collections.shuffle(letters);

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    buttons[i][j].setText("");
                    buttons[i][j].setVisibility(View.INVISIBLE);
                } else {
                    buttons[i][j].setText(letters.get(k));
                    buttons[i][j].setVisibility(View.VISIBLE);
                    k++;
                }
            }
        }
    }


    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            int x = -1, y = -1;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (buttons[i][j].getText().toString().equals(buttonText)) {
                        x = i;
                        y = j;
                        break;
                    }
                }
                if (x != -1) break;
            }

            if ((Math.abs(x - emptyX) == 1 && y == emptyY) || (Math.abs(y - emptyY) == 1 && x == emptyX)) {
                Button emptyButton = buttons[emptyX][emptyY];
                emptyButton.setText(buttonText);
                emptyButton.setVisibility(View.VISIBLE);

                button.setText("");
                button.setVisibility(View.INVISIBLE);

                emptyX = x;
                emptyY = y;

                checkForWin();
            }
        }
    };

    private void checkForWin() {
        char expectedChar = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    // Skip the empty space
                    continue;
                }
                String tileText = buttons[i][j].getText().toString();
                Log.d("Puzzle", "Tile  [" + i + "][" + j + "]: " + tileText);
                if (tileText.isEmpty() || tileText.charAt(0) != expectedChar) {
                    Log.d("Puzzle", "Tile not in order: expected " + expectedChar + ", found " + (tileText.isEmpty() ? "empty" : tileText.charAt(0)));
                    return; // Not in the correct order
                }
                expectedChar++;
            }
        }
        Toast.makeText(MainActivity.this, "Kamu menang!", Toast.LENGTH_SHORT).show();
    }

}
