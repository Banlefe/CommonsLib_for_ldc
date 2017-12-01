package soft.binsoft.com.toastlib;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import soft.binsoft.com.mytoast.ToastUtil;

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.Instance(ctx).AndroidToast_White("数字递归", "显示文本吧", Toast.LENGTH_SHORT);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.Instance(ctx).AndroidToast_Black("数字递归", "显示文本吧", Toast.LENGTH_SHORT);
            }
        });
    }
}
