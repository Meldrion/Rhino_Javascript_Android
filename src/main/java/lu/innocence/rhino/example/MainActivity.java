package lu.innocence.rhino.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

public class MainActivity extends Activity {

    private Context context;
    private Scriptable scope;
    private RhinoAndroidHelper rhinoAndroidHelper;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Intent intent = getIntent();

        final TextView textView = new TextView(this);
        textView.setText("Person: {}");


        Button btn= new Button(this);
        btn.setText("Submit");
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String testScript = "var person = Packages.lu.innocence.rhino.example.Person('Max Mustermann',25);";
/*                testScript += "person.setName('John Doe');";
                testScript += "person.setAge(45);";*/
                testScript += "person.printDetails();";
                context.evaluateString(global, testScript, "test.js", 1, null);

                Object personObject = global.get("person",global);
                if (personObject != Scriptable.NOT_FOUND) {

                    NativeJavaObject jsObjHandle = (NativeJavaObject) personObject;
                    Person personHandle = (Person) jsObjHandle.unwrap();
                    textView.setText(String.format("Person: { name:%s , age:%s }",
                            personHandle.getName(),personHandle.getAge()));
                } else {
                    Log.d("Not found","The Person variable is not defined");
                    textView.setText("Person: {}");
                    String script2 = "print('Person: ' + person.getName()  + ' ' + person.getAge() );";
                    context.evaluateString(global, script2, "test.js", 1, null);
                }

            }
        });


        layout.addView(textView);
        layout.addView(btn);



        //setContentView(R.layout.content_main);

        Log.d("JS: ","Starting up");

        rhinoAndroidHelper = new RhinoAndroidHelper(this);
        context = rhinoAndroidHelper.enterContext();
        context.setOptimizationLevel(1);
        scope = context.initSafeStandardObjects(); //new ImporterTopLevel(context);
        this.global = new Global(context);


    }

}