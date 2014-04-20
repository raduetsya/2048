package com.lanus.game2048;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	protected GameView getGameView() {
		return (GameView)(findViewById(R.id.game_view));
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getGameView().invalidate();
        
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        Map<String, String> stringStoreData = new HashMap<String, String>();

        Map<String, ?> storeData = preferences.getAll();
        for (Map.Entry<String, ?> anyEntry : storeData.entrySet()) {
        	if (anyEntry.getValue() instanceof String)
        		stringStoreData.put(anyEntry.getKey(), (String)anyEntry.getValue());
        }
        
        getGameView().loadState(stringStoreData);
        
        if (preferences.getBoolean("NEW_GAME", true) == true)
            getGameView().onUserAction("NEW_GAME");

    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        Map<String, String> storeData = new HashMap<String, String>();
        
        Map<String, String> viewMap = getGameView().saveState();
        if (viewMap != null)
        	storeData.putAll(viewMap);
        
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        
        editor.putBoolean("NEW_GAME", false);

        for (Map.Entry<String, String> entry : storeData.entrySet()) {
        	editor.putString(entry.getKey(), entry.getValue());
        }
        
        editor.commit();
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.action_newgame) {
            //List<GameGrid.Action> animList = new ArrayList<GameGrid.Action>();
            //gameGridModel.doNewGame(animList);
            //view.startAnim(animList);
        }
        if (id == R.id.action_cheat) {
            //gameGridModel.doCheat();
            //view.invalidate();
        }
        return super.onOptionsItemSelected(item);
    }

}
