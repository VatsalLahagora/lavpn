package com.vatsal.lavpn.lavpn.openvpn;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.vatsal.lavpn.lavpn.R;
import com.vatsal.lavpn.lavpn.openvpn.core.ConfigParser;
import com.vatsal.lavpn.lavpn.openvpn.core.ProfileManager;
import com.vatsal.lavpn.lavpn.openvpn.core.VPNLaunchHelper;

import android.src.main.java.com.vatsal.lavpn.lavpn.openvpn.VpnProfile;

import java.io.IOException;
import java.io.StringReader;


public class OpenVpnApi {

    private static final String  TAG = "OpenVpnApi";
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public static void startVpn(Context context, String inlineConfig, String sCountry,String expireAt , String userName, String pw, String profileId, int timeOutInSeconds) throws RemoteException {
        if (TextUtils.isEmpty(inlineConfig)) throw new RemoteException("config is empty");
            startVpnInternal(context, inlineConfig, sCountry,expireAt, userName, pw, profileId , timeOutInSeconds);
    }

    static void startVpnInternal(Context context, String inlineConfig, String sCountry,String expireAt ,  String userName, String pw, String profileId, int timeOutInSeconds) throws RemoteException {
        ConfigParser cp = new ConfigParser();
        try {
            cp.parseConfig(new StringReader(inlineConfig));
            VpnProfile vp = cp.convertProfile();// Analysis.ovpn
            Log.d(TAG, "startVpnInternal: =============="+cp+"\n" +
                    vp);
            vp.mName = sCountry;
            vp.mProdileId =profileId;
            vp.timeOutInSeconds = timeOutInSeconds;
            if (vp.checkProfile(context) != R.string.no_error_found){
                throw new RemoteException(context.getString(vp.checkProfile(context)));
            }
            vp.mProfileCreator = context.getPackageName();
            vp.mUsername = userName;
            vp.mPassword = pw;
            vp.mExpireAt = expireAt;
            ProfileManager.setTemporaryProfile(context, vp);
            VPNLaunchHelper.startOpenVpn(vp, context);
        } catch (IOException | ConfigParser.ConfigParseError e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
