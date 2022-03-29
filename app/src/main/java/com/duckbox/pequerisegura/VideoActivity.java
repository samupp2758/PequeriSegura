package com.duckbox.pequerisegura;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.Arrays;

public  class VideoActivity extends AppCompatActivity  {

    private Camera camer;
    private VideoView surfaceView;
    private Button bStartStop;
    private String[] options = new String[]{":fullscreen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.content_video);
        surfaceView = (VideoView) findViewById(R.id.vv);
        if(savedInstanceState != null){

            camer = savedInstanceState.getParcelable("ITEM");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("ITEM") != null) {
                camer = getIntent().getExtras().getParcelable("ITEM");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
            Toolbar  mToolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(mToolbar);
       title();
       }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
         //       Intent intent =new Intent(this, MainActivity.class);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//O efeito ao ser pressionado do botão (no caso abre a activity)
          //     this.startActivity(intent);
         //       finishAffinity();
                finish();
                // Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:
                break;
        }
        return true;
    }

    public void title(){
        //Titulo para ser exibido na sua Action Bar em frente à seta
        final ActionBar abar = getSupportActionBar();
        abar.setBackgroundDrawable(getResources().getDrawable(R.color.tranp));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.abs_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.mytextview);
        textviewTitle.setText(camer.getLink_());
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        abar.setHomeButtonEnabled(true);//Ativar o botão

    }

}
