package com.vatsal.lavpn.lavpn_example;

import io.flutter.embedding.android.FlutterActivity;
import android.content.Intent;
import com.vatsal.lavpn.lavpn.LavpnPlugin;

public class MainActivity extends FlutterActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                LavpnPlugin.setPermission(true);
            } else {
                LavpnPlugin.setPermission(false);
            }
        }
    }
}
