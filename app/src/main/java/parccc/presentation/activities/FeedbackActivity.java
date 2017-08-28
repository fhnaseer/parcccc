package parccc.presentation.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Feedback;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class FeedbackActivity extends de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.NavigationItemActivity {

    private Feedback _feedback = new Feedback();

    public static Intent newIntent(Context context) {
        return new Intent(context, FeedbackActivity.class);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_feedback_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void sendFeedback(View button) {
        Spinner spinner = (Spinner) findViewById(R.id.feedback_overall_experience);
        _feedback.setOverallExperience(spinner.getSelectedItem().toString());
        spinner = (Spinner) findViewById(R.id.feedback_customer_service);
        _feedback.setCustomerService(spinner.getSelectedItem().toString());
        spinner = (Spinner) findViewById(R.id.feedback_meals);
        _feedback.setMealsAndBeverages(spinner.getSelectedItem().toString());
        spinner = (Spinner) findViewById(R.id.feedback_app_usability);
        _feedback.setAppUsability(spinner.getSelectedItem().toString());
        spinner = (Spinner) findViewById(R.id.feedback_entertainment);
        _feedback.setEntertainment(spinner.getSelectedItem().toString());
        EditText editText = (EditText) findViewById(R.id.any_other_feedback);
        _feedback.setAnyOtherFeedback(editText.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.feedback_submitted);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
