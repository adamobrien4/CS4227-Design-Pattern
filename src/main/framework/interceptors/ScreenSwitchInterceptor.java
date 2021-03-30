package main.framework.interceptors;

import main.framework.contexts.ScreenSwitchContext;

public class ScreenSwitchInterceptor {
    public void onScreenSwitch(ScreenSwitchContext context)
    {
        System.out.println("Log: " + context.getMessage());
    }
}
