package com.duckbox.pequerisegura;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import veg.mediaplayer.sdk.MediaPlayerConfig;


public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.ViewHolder>  {
    private Context context;
    private String ss;
    private ProgressDialog progressDialog;
    private List<Camera> list;
    public int ii;

    public CameraAdapter(Context context, List<Camera> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ii = R.layout.item;
        View v = LayoutInflater.from(context).inflate(ii, viewGroup, false);
        return new ViewHolder(v, i);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Camera camera = list.get(i);
        Anim_Card ac = new Anim_Card(context);

        ac.animate(viewHolder.surfaceView,i);
        viewHolder.nome.setText(camera.getCamera());
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            Intent ii = new Intent(context, VideoActivity.class);
             ii.putExtra("ITEM",camera);
             context.startActivity(ii);
             }
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements IVLCVout.Callback, LibVLC.HardwareAccelerationError {
        public final static String TAG = "MainActivity";
        private MediaPlayer mMediaPlayer = null;
        private int mVideoWidth;
        private int mVideoHeight;
        private final static int VideoSizeChanged = -1;
        public TextView nome;
        DisplayMetrics dimension = new DisplayMetrics();
        final int height = dimension.heightPixels;
        public CardView card;
        ProgressBar pb;
        MediaPlayer player;
        public VideoView surfaceView;
        private String[] options = new String[]{":fullscreen"};

        public ViewHolder(View itemView, int i) {
            // inside your activity (if you did not enable transitions in your theme)
            super(itemView);
            card = itemView.findViewById(R.id.card_view);
            pb = itemView.findViewById(R.id.pb);
            nome = itemView.findViewById(R.id.nome);
            surfaceView = itemView.findViewById(R.id.videov);

        }



    }





}