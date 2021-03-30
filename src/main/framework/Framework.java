package main.framework;

import main.framework.contexts.Context;
import main.framework.contexts.ScreenSwitchContext;
import main.framework.dispatchers.LoggingDispatcher;
import main.framework.dispatchers.ScreenSwitchDispatcher;
import main.framework.interceptors.LoggingInterceptor;
import main.framework.interceptors.ScreenSwitchInterceptor;

public class Framework {
    // Dispatchers
    private LoggingDispatcher loggingDispatcher;
    private ScreenSwitchDispatcher screenSwitchDispatcher;

    // Instance
    private static Framework instance;

    private Framework()
    {
        loggingDispatcher = LoggingDispatcher.getInstance();
        screenSwitchDispatcher = ScreenSwitchDispatcher.getInstance();
    }

    public static Framework getInstance()
    {
        if (instance == null)
            instance = new Framework();
        return instance;
    }

    /**
     *  Logging
     */
    public boolean registerLoggingInterceptor(LoggingInterceptor interceptor)
    {
        return loggingDispatcher.register(interceptor);
    }

    public boolean removeLoggingInterceptor(LoggingInterceptor interceptor)
    {
        return loggingDispatcher.remove(interceptor);
    }

    public void onLogEvent(Context context)
    {
        loggingDispatcher.onLogEvent(context);
    }

    /**
     *  Screen Switch
     */
    public boolean registerScreenSwitchInterceptor(ScreenSwitchInterceptor interceptor)
    {
        return screenSwitchDispatcher.register(interceptor);
    }

    public boolean removeScreenSwitchInterceptor(ScreenSwitchInterceptor interceptor)
    {
        return screenSwitchDispatcher.remove(interceptor);
    }

    public void onScreenSwitch(ScreenSwitchContext context)
    {
        screenSwitchDispatcher.onScreenSwitch(context);
    }
}
