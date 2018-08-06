package pazhamalai.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.malai_pt1882.taskwithalllayouts.R;

import App.constant.Constant;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CheckBox checkBox;
        Intent intent = getIntent();
        if(intent.getStringExtra(Constant.LAYOUT_TYPE).equals(Constant.LINEAR_LAYOUT)){
            checkBox = getCheckBoxFromLayout(R.layout.activity_linear_layout,R.id.linearLayoutRoot,R.id.linearCheckBox);
        }
        else if(intent.getStringExtra(Constant.LAYOUT_TYPE).equals(Constant.RELATIVE_LAYOUT)){
            checkBox = getCheckBoxFromLayout(R.layout.activity_relative_layout,R.id.relativeLayoutRoot,R.id.relativeCheckBox);
        }
        else {
            checkBox = getCheckBoxFromLayout(R.layout.activity_frame_layout,R.id.frameLayoutRoot,R.id.frameCheckBox);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private Toast toast = null;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toast !=null){
                    toast.cancel();
                }
                if(isChecked){
                    toast = Toast.makeText(LayoutActivity.this,"Checked",Toast.LENGTH_SHORT);
                    toast.show();
                } else{
                    toast = Toast.makeText(LayoutActivity.this,"Unchecked",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    private CheckBox getCheckBoxFromLayout(int resourceID,int rootID,int checkBoxID){
        LayoutInflater layoutInflater = getLayoutInflater();
        View layoutView = layoutInflater.inflate(resourceID,(ViewGroup)findViewById(rootID));
        CheckBox checkBox = (CheckBox)layoutView.findViewById(checkBoxID);
        setContentView(layoutView);
        return checkBox;
    }
}
