package com.arvin.pmenu;

import cn.arvin.framework.application.FrameApplication;
import cn.arvin.framework.constants.Config;
import cn.arvin.framework.core.net.RequestSender;
import cn.arvin.framework.core.net.impl.VolleyNetwork;


public class SoftApplication extends FrameApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        RequestSender.init(VolleyNetwork.getInstance());
    }
}
