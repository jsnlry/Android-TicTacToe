package game.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

public class TicTacToe extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/* TODO */
	public void startSingle(View view){
		Toast.makeText(this, "Single-Modus gestartet", Toast.LENGTH_SHORT).show();
	}

	public void startMulti(View view){
		Toast.makeText(this, "Multi-Modus gestartet", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Spielfeld.class);
		startActivity(intent);
	}

	public void startBluetooth(View view){
		Toast.makeText(this, "Bluetooth-Modus gestartet", Toast.LENGTH_SHORT).show();
	}
}
