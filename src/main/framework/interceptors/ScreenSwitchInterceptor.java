package main.framework.interceptors;

import main.framework.contexts.ScreenSwitchContext;

public class ScreenSwitchInterceptor implements Interceptor {

    static ScreenSwitchInterceptor instance;

    public static ScreenSwitchInterceptor getInstance() {
        if (instance == null) {
            instance = new ScreenSwitchInterceptor();
        }
        return instance;
    }

    public void onScreenSwitch(ScreenSwitchContext context)
    {
        System.out.println("Log: " + context.getMessage());
    }
}
