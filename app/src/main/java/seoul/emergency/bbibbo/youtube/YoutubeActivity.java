package seoul.emergency.bbibbo.youtube;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason;
import com.google.android.youtube.player.YouTubeThumbnailView;

import android.app.ActivityManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.notice.NoticeActivity;
import seoul.emergency.bbibbo.questionnaire.QuestionnaireActivity;
import seoul.emergency.bbibbo.questionnaire.ScreenService;
import seoul.emergency.bbibbo.userguide.UserguideActivity;


public class YoutubeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /** The duration of the animation sliding up the video in portrait. */
    private static final int ANIMATION_DURATION_MILLIS = 300;
    /** The padding between the video list and the video in landscape orientation. */
    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;

    /** The request code when calling startActivityForResult to recover from an API service error. */
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private VideoListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listFragment = (VideoListFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        checkYouTubeApi();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {


            }

            @Override
            public void onDrawerOpened(View drawerView) {

                Switch switch1 = (Switch) findViewById(R.id.switch_lock);

                if(isServiceRunning("seoul.emergency.bbibbo.questionnaire.ScreenService") == true)
                    switch1.setChecked(true);
                else
                    switch1.setChecked(false);

                switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                            startService(intent);
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                            stopService(intent);
                        }
                    }
                });

                final Button btn_drawer1 = (Button)findViewById(R.id.btn_drawer1);
                final Button btn_drawer2 = (Button)findViewById(R.id.btn_drawer2);
                final Button btn_drawer3 = (Button)findViewById(R.id.btn_drawer3);
                final Button btn_drawer4 = (Button)findViewById(R.id.btn_drawer4);
                final Typeface helR = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Helvetica.ttf");
                Typeface helL = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Helvetica Light.ttf");

                TextView appbar = (TextView)findViewById(R.id.appbar_video_text);
                appbar.setTypeface(helL);
                btn_drawer1.setTypeface(helR);
                btn_drawer2.setTypeface(helR);
                btn_drawer3.setTypeface(helR);
                btn_drawer4.setTypeface(helR);

                btn_drawer1.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.d("df","123");
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Log.d("df","124");

                            Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });


                btn_drawer2.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                btn_drawer3.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                btn_drawer4.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer4.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), UserguideActivity.class);
                            startActivity(intent);
                            finish();

                            btn_drawer4.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public Boolean isServiceRunning(String serviceName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {

            if (serviceName.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void checkYouTubeApi() {
        YouTubeInitializationResult errorReason =
                YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format(getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Recreate the activity if user performed a recovery action
            recreate();
        }
    }
    public static final class VideoListFragment extends ListFragment {

        private static final List<VideoEntry> VIDEO_LIST;
        static {
            List<VideoEntry> list = new ArrayList<VideoEntry>();
            list.add(new VideoEntry("(Eng) How to CPR", "cosVBV96E2g"));
            list.add(new VideoEntry("(Eng) How to use AED", "YpJsL22xfKA"));
            list.add(new VideoEntry("(Eng) How to Heimlich", "7CgtIgSyAiU"));
            list.add(new VideoEntry("(Jpn) How to AED", "KXBiMO71ZB0"));
            list.add(new VideoEntry("(Jpn) How to Heimlich", "gfRRL9SaN8k"));
            list.add(new VideoEntry("(Chn) How to CPR", "FavgWRAYGNI"));
            list.add(new VideoEntry("(Chn) How to Heimlich", "PGfqy2ndrqc"));
            VIDEO_LIST = Collections.unmodifiableList(list);
        }

        private PageAdapter adapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            adapter = new PageAdapter(getActivity(), VIDEO_LIST);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
/*
            videoBox = getActivity().findViewById(R.id.video_box);*/
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            setListAdapter(adapter);
        }



        @Override
        public void onDestroyView() {
            super.onDestroyView();

            adapter.releaseLoaders();
        }

        public void setLabelVisibility(boolean visible) {
            adapter.setLabelVisibility(visible);
        }

    }
    private static final class PageAdapter extends BaseAdapter {

        private final List<VideoEntry> entries;
        private final List<View> entryViews;
        private final Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
        private final LayoutInflater inflater;
        private final ThumbnailListener thumbnailListener;


        private boolean labelsVisible;

        public PageAdapter(Context context, List<VideoEntry> entries) {
            this.entries = entries;

            entryViews = new ArrayList<View>();
            thumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
            inflater = LayoutInflater.from(context);
            thumbnailListener = new ThumbnailListener();


            labelsVisible = true;
        }

        public void releaseLoaders() {
            for (YouTubeThumbnailLoader loader : thumbnailViewToLoaderMap.values()) {
                loader.release();
            }
        }

        public void setLabelVisibility(boolean visible) {
            labelsVisible = visible;
            for (View view : entryViews) {
                view.findViewById(R.id.text).setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }

        @Override
        public int getCount() {
            return entries.size();
        }

        @Override
        public VideoEntry getItem(int position) {
            return entries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final VideoEntry entry = entries.get(position);

            // There are three cases here
            if (view == null) {
                // 1) The view has not yet been created - we need to initialize the YouTubeThumbnailView.
                view = inflater.inflate(R.layout.youtube_listview_item, parent, false);

                YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);

                thumbnail.setTag(entry.videoId);
                thumbnail.initialize(DeveloperKey.DEVELOPER_KEY, thumbnailListener);


            } else {
                YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);
                YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(thumbnail);
                if (loader == null) {
                    // 2) The view is already created, and is currently being initialized. We store the
                    //    current videoId in the tag.
                    thumbnail.setTag(entry.videoId);
                } else {
                    // 3) The view is already created and already initialized. Simply set the right videoId
                    //    on the loader.
                    thumbnail.setImageResource(R.drawable.loading_thumbnail);
                    loader.setVideo(entry.videoId);
                }
            }

            TextView label = ((TextView) view.findViewById(R.id.text));
            Typeface face = Typeface.createFromAsset(view.getContext().getAssets(),"fonts/Helvetica Light.ttf");
            label.setTypeface(face);
            ImageButton button = (ImageButton)view.findViewById(R.id.imgBtn);

            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    switch (v.getId()){
                        case R.id.imgBtn:
                            Intent intent = new Intent(v.getContext(),YoutubePlayActivity.class);
                            intent.putExtra("videoId",entry.videoId);
                            v.getContext().startActivity(intent);
                            break;

                    }
                }
            });
            label.setText(entry.text);
            label.setVisibility(labelsVisible ? View.VISIBLE : View.GONE);

            return view;
        }
        private final class ThumbnailListener implements
                YouTubeThumbnailView.OnInitializedListener,
                YouTubeThumbnailLoader.OnThumbnailLoadedListener {
            @Override
            public void onInitializationSuccess(
                    YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
                loader.setOnThumbnailLoadedListener(this);

                thumbnailViewToLoaderMap.put(view, loader);
                view.setImageResource(R.drawable.loading_thumbnail);
                String videoId = (String) view.getTag();

                loader.setVideo(videoId);

            }
            @Override
            public void onInitializationFailure(
                    YouTubeThumbnailView view, YouTubeInitializationResult loader) {
                view.setImageResource(R.drawable.no_thumbnail);
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView view, String videoId) {
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView view, ErrorReason errorReason) {
                view.setImageResource(R.drawable.no_thumbnail);
            }

        }

    }
    private static final class VideoEntry {
        private final String text;
        private final String videoId;

        public VideoEntry(String text, String videoId) {
            this.text = text;
            this.videoId = videoId;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.youtube, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent2 = new Intent(YoutubeActivity.this,YoutubeActivity.class);
            startActivity(intent2);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
