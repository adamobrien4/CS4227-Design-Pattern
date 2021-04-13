package main.framework.dispatchers;

import main.framework.contexts.Context;
import main.framework.contexts.ScreenSwitchContext;
import main.framework.interceptors.ScreenSwitchInterceptor;

import java.util.ArrayList;

public class ScreenSwitchDispatcher {
    private ArrayList<ScreenSwitchInterceptor> interceptors;

    private static ScreenSwitchDispatcher instance;

    private ScreenSwitchDispatcher()
    {
        interceptors = new ArrayList<>();
    }

    public static ScreenSwitchDispatcher getInstance()
    {
        if (instance == null)
            instance = new ScreenSwitchDispatcher();
        return instance;
    }

    public boolean register(ScreenSwitchInterceptor interceptor)
    {
        return interceptors.add(interceptor);
    }

    public boolean remove(ScreenSwitchInterceptor interceptor)
    {
        return interceptors.remove(interceptor);
    }

    public void onScreenSwitch(Context context)
    {
        for (ScreenSwitchInterceptor interceptor : interceptors)
            interceptor.onScreenSwitch((ScreenSwitchContext) context);
    }
}
