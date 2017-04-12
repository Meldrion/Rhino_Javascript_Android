package lu.innocence.rhino.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

public class MainActivity extends Activity {

    private Context context;
    private Scriptable scope;
    private RhinoAndroidHelper rhinoAndroidHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_main);

        Log.d("JS: ","Starting up");

        rhinoAndroidHelper = new RhinoAndroidHelper(this);
        context = rhinoAndroidHelper.enterContext();
        context.setOptimizationLevel(1);
        scope = context.initSafeStandardObjects(); //new ImporterTopLevel(context);

        Global global = new Global(context);

        String textScript = "var person = Packages.lu.innocence.rhino.example.Person();";
        textScript += "person.setName('John Doe');";
        textScript += "person.setAge(45);";
        textScript += "person.printDetails();";

        context.evaluateString(global, textScript, "test.js", 1, null);

/*        context.evaluateString(global, "var a = 10;", "test.js", 1, null);
        context.evaluateString(global, "var b = 20;", "test.js", 1, null);
        context.evaluateString(global, "print('Value is: ' +  (a+b));", "test.js", 1, null);*/


    }

}