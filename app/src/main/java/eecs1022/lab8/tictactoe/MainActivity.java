package eecs1022.lab8.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab8.tictactoe.model.Game;

public class MainActivity extends AppCompatActivity {

    /* Hint: How do you share the same game object between button clicks
     * (attached with controller methods) of the app?
     */
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint: How do you display the initial status to the output textview
         * when the app is first launched?
         */

    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void startButton(View view){
        String x_name = getInputOfTextField(R.id.playerX_plainText);
        String o_name = getInputOfTextField(R.id.playerO_plainText);

        String firstPlayer = getItemSelected(R.id.firstPlayer_spinner);

        String display = "";

        Game g = new Game(x_name, o_name);

        if(firstPlayer == "Player X"){
            g.setWhoPlaysFirst('x');
        }else{
            g.setWhoPlaysFirst('o');
        }

        this.game = g;

        char[][] boardInChar = this.game.getBoard();

        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                display += boardInChar[i][j] + "    ";
            }
            display += "\n";
        }

        display += "\n" + this.game.getStatus();

        setContentsOfTextView(R.id.result_display, display);
    }

    public void moveButton(View view){
        int moveRow = Integer.parseInt(getInputOfTextField(R.id.rowNumber_Input));
        int moveCol = Integer.parseInt(getInputOfTextField(R.id.colNumber_Input));

        game.move(moveRow , moveCol);

        String display = "";
        char[][] boardInChar = this.game.getBoard();

        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                display += boardInChar[i][j] + "    ";
            }
            display += "\n";
        }

        display += "\n" + this.game.getStatus();

        setContentsOfTextView(R.id.result_display, display);
    }

    /* Hints on controller methods:
     *
     * Declare two controller methods, each of which declared with a parameter with type View (see Week 9 Tutorials).
     *  - One method (say cm1) should be attached to the "START/RESTART" button,
     *      whereas the other method (say cm2) should be attached to the "MOVE" button.
     *
     *  - Controller method cm1 should:
     *    + Retrieve the names of the two players from the corresponding textfields.
     *    + Retrieve the player (who will play first) from the spinner.
     *    + Then, re-create the shared game object and invoke the relevant method(s).
     *    + Finally, display the expected output of the game (i.e., board and status) to the output textview.
     *
     * - Controller method cm2 should:
     *    + Retrieve the row and column numbers (as strings) from the corresponding textfields.
     *      You may need to convert the retrieved text, e.g., "1" to an integer value using Double.parseInt.
     *    + Then, invoke the relevant method(s) on the shared game object.
     *    + Finally, display the expected output of the game (i.e., board and status) to the ouptut textview.
     */
}