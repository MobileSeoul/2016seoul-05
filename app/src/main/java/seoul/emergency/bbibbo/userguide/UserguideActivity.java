package seoul.emergency.bbibbo.userguide;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.R;

public class UserguideActivity extends AppCompatActivity {
    ViewPager pager;
    UserguideAdapter adapter;
    ArrayList<Integer> userImages;
    ArrayList<Activity> actList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userguide);



        pager = (ViewPager)findViewById(R.id.pager);


        userImages = new ArrayList<Integer>();

        userImages.add(R.drawable.user1);
        userImages.add(R.drawable.user2);
        userImages.add(R.drawable.user3);
        userImages.add(R.drawable.user4);
        userImages.add(R.drawable.user5);

        adapter = new UserguideAdapter(this,userImages,clickEvent);
        pager.setAdapter(adapter);
    }
    public View.OnClickListener clickEvent = new View.OnClickListener() {
        public void onClick(View v) {
            int itemPosition = pager.getCurrentItem();
            if(itemPosition == 4){
                if(MainActivity.getInstance() ==null) {
                    Intent intent = new Intent(UserguideActivity.this, MainActivity.class);
                    startActivity(intent);finish();
                }
                else{
                    onBackPressed();
                    finish();
                }

            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
