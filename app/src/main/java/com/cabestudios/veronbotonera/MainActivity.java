package com.cabestudios.veronbotonera;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cabestudios.veronbotonera.items.CabeMedia;
import com.cabestudios.veronbotonera.items.CabeMediaLibrary;

import java.io.File;
import java.io.FileDescriptor;

public class MainActivity extends AppCompatActivity implements CabeMedia.CabeMediaListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new CabeRecylcerAdapter(new CabeMediaLibrary(), this));
    }

    @Override
    public void play(int id) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, id);
        mediaPlayer.start();
    }

    @Override
    public void play(String nameFile) {

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            mediaPlayer.release();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            AssetFileDescriptor afd = getAssets().openFd(nameFile);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
        }

    }


    public class CabeRecylcerAdapter extends RecyclerView.Adapter {

        private CabeMediaLibrary cabeMediaLibrary;
        private CabeMedia.CabeMediaListener listener;

        public CabeRecylcerAdapter(CabeMediaLibrary cabeMediaLibrary, CabeMedia.CabeMediaListener listener) {
            this.cabeMediaLibrary = cabeMediaLibrary;
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cabe_item, parent, false);
            return new CabeItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final CabeMedia cabeMedia = cabeMediaLibrary.getItem(position);
            ((CabeItemViewHolder) holder).mTextView.setText(cabeMedia.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    listener.play(cabeMedia.getAssetId());
                    listener.play(cabeMedia.getFileName());

                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    Uri url = Uri.parse("android.resource://" + getPackageName() + "/" + cabeMedia.getAssetId());
//                    getAssets().openFd("").getFileDescriptor().C


                    File f = new File("///assets/"+ cabeMedia.getFileName());
                    boolean b = f.exists();
                    Uri uri2 = Uri.fromFile(new File("///assets/"+ cabeMedia.getFileName()));
                    Uri uri = Uri.parse("file:///android_asset/"+cabeMedia.getFileName());


                    Uri url = Uri.parse("android.resource:///" + "com.cabestudios.veronbotonera" + "/" + cabeMedia.getFileName());

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri2);
                    shareIntent.setType("audio/*");
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return cabeMediaLibrary.getSize();
        }
    }


    public class CabeItemViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;


        public CabeItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.card_title);
        }

    }

}
