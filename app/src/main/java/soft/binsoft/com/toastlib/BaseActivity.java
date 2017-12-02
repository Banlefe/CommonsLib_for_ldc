package soft.binsoft.com.commonlibs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by LDC on 2017/12/1.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context ctx = null;
    protected Activity act = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ctx = this;
        this.act = this;
    }
}
