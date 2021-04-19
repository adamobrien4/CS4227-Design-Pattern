package main.framework.interceptors;

import main.adapters.AdapterType;
import main.adapters.ConsoleAdapter;
import main.adapters.LogAdapter;
import main.framework.contexts.Context;
import main.framework.contexts.ErrorContext;

import java.util.logging.Level;

public class LoggingInterceptor implements Interceptor {
    private LogAdapter logAdapter;

    private static LoggingInterceptor instance;

    private LoggingInterceptor(LogAdapter logAdapter)
    {
        this.logAdapter = logAdapter;
    }

    public static LoggingInterceptor getInstance(AdapterType adapterType)
    {
        if (instance == null)
        {
            switch (adapterType)
            {
                case CONSOLE:   instance = new LoggingInterceptor(new ConsoleAdapter());    break;
            }
        }
        return instance;
    }

    public Context onLogEvent(Context context)
    {
        logAdapter.info(context.getMessage());
        if (context instanceof ErrorContext)
        {
            logAdapter.log(Level.SEVERE, ((ErrorContext)context).getException().getMessage());
        }
        return context;
    }
}
