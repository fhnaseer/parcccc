package parccc.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SafetyInstructionsActivity extends NavigationItemActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SafetyInstructionsActivity.class);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_safety_instructions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnButton();
        images = new int[7];
        images[0] = R.drawable.safety_devices;
        images[1] = R.drawable.safety_style;
        images[2] = R.drawable.safety_lighter;
        images[3] = R.drawable.safety_husky;
        images[4] = R.drawable.safety_mask;
        images[5] = R.drawable.safety_jumping;
        images[6] = R.drawable.safety_seat;
    }

    Button button;
    ImageView image;
    int[] images;
    int index = 0;
    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.imageView1);
        button = (Button) findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                index++;
                if (index == 7) index = 1;
                image.setImageResource(images[index]);
            }
        });
    }
}
