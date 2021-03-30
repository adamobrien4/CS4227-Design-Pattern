package main.framework.contexts;

public class ScreenSwitchContext extends Context {
    private String screenType;

    public ScreenSwitchContext()
    {
        this(null, null);
    }

    public ScreenSwitchContext(String message, String screenType)
    {
        this.message = message;
        this.screenType = screenType;
    }

    public String getScreenType()
    {
        return screenType;
    }

    public void setScreenType(String screenType)
    {
        this.screenType = screenType;
    }
}
