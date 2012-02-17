package game.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

public class Spielfeld extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielfeld);

	buttons[0] = (Button) findViewById(R.id.button0);
	buttons[1] = (Button) findViewById(R.id.button1);
	buttons[2] = (Button) findViewById(R.id.button2);
	buttons[3] = (Button) findViewById(R.id.button3);
	buttons[4] = (Button) findViewById(R.id.button4);
	buttons[5] = (Button) findViewById(R.id.button5);
	buttons[6] = (Button) findViewById(R.id.button6);
	buttons[7] = (Button) findViewById(R.id.button7);
	buttons[8] = (Button) findViewById(R.id.button8);
    }

	Spiellogik s = new Spiellogik();
	Button[] buttons = new Button[9];

	public void spielzug(View view){

		//ID des gedrueckten Buttons wird ausgelesen
		String buttonS = (String) view.getTag();
		int button = -1;
		try{
			button = Integer.parseInt(buttonS);
		}catch(NumberFormatException e){
			Log.e(Spielfeld.class.getName(), "Keine Zahl", e);		
		}
	
		//Ist der Button bereits belegt?
		try{	
			s.tick(button);
		}catch(IllegalStateException e){
			Toast.makeText(this, "Dieses Feld ist bereits belegt!", Toast.LENGTH_SHORT).show();
		}

		//Messagebox zeigen, wenn das Spiel zu Ende ist
		if(s.getWinner() != null || s.isFull()){

			String winner;
			if(s.getWinner() == null){
				winner = "Niemand";
			}else{
				winner = "Spieler " + s.getWinner();
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(winner + " hat gewonnen.")
				.setCancelable(false)
				.setPositiveButton("Wiederholen", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){					
						s.reset();
						refreshLabels();
					}
				})
				.setNegativeButton("Men\u00FC", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						Intent intent = new Intent(Spielfeld.this, TicTacToe.class);
						startActivity(intent);
					}
				});
			builder.create().show();
		}
		refreshLabels();
	}

	public void refreshLabels(){
		Spiellogik.Player[] board = s.getBoard();
		for(int i = 0; i < board.length; i++){
			buttons[i].setText(board[i] == null ? "" : "" + board[i]);
		}
	}
}
