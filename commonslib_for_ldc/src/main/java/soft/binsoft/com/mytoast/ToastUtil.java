package soft.binsoft.com.mytoast;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.Serializable;

import soft.binsoft.com.mytoast.Util.DeviceUtil;
import soft.binsoft.com.mytoast.Util.StringUtils;

/**
 * Created by LDC on 2017/12/1.
 */

public class ToastUtil implements Serializable {
    private static ToastUtil install = null;
    private Context ctx = null;

    //实例化
    public static ToastUtil Instance(Context ctx) {
        synchronized (ToastUtil.class) {
            if (install == null)
                install = new ToastUtil(ctx);
        }
        return install;
    }

    private ToastUtil(Context ctx) {
        this.ctx = ctx;
    }


    // TODO: 2017/12/1 Android显示
    public void AndroidToast_White(String title, String msg, int LENGTH) {

        try {
            AppCompatTextView mtitle = null;
            AppCompatTextView mmsg = null;
            View mainView = LayoutInflater.from(ctx).inflate(R.layout.android_white_toast, null, false);
            mtitle = mainView.findViewById(R.id.title);
            mmsg = mainView.findViewById(R.id.msg);
            // TODO: 2017/12/1 界面显示隐藏
            if (StringUtils.isBlank(title))
                mtitle.setVisibility(View.GONE);
            else if (StringUtils.isBlank(msg)) {
                mmsg.setVisibility(View.GONE);
                return;
            }
            mainView.setLayoutParams(new WindowManager.LayoutParams(DeviceUtil.getDeviceWidth(ctx) / 3, WindowManager.LayoutParams.WRAP_CONTENT));
            //设置数据
            mtitle.setText(title);
            mmsg.setText(msg);
            Toast toast = new Toast(ctx);
            toast.setView(mainView);
            toast.setDuration(LENGTH);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // TODO: 2017/12/1 Android显示
    public void AndroidToast_Black(String title, String msg, int LENGTH) {

        try {
            AppCompatTextView mtitle = null;
            AppCompatTextView mmsg = null;
            View mainView = LayoutInflater.from(ctx).inflate(R.layout.android_black_toast, null, false);
            mtitle = mainView.findViewById(R.id.title);
            mmsg = mainView.findViewById(R.id.msg);
            // TODO: 2017/12/1 界面显示隐藏
            if (StringUtils.isBlank(title))
                mtitle.setVisibility(View.GONE);
            else if (StringUtils.isBlank(msg)) {
                mmsg.setVisibility(View.GONE);
                return;
            }
            mainView.setLayoutParams(new WindowManager.LayoutParams(DeviceUtil.getDeviceWidth(ctx) / 3, WindowManager.LayoutParams.WRAP_CONTENT));
            //设置数据
            mtitle.setText(title);
            mmsg.setText(msg);
            Toast toast = new Toast(ctx);
            toast.setView(mainView);
            toast.setDuration(LENGTH);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
