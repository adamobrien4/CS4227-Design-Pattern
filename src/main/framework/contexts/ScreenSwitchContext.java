package main.framework.contexts;

import main.presentation_layer.presentation.ScreenType;

public class ScreenSwitchContext extends Context {
    private ScreenType screenType;

    public ScreenSwitchContext()
    {
        this(null, null);
    }

    public ScreenSwitchContext(String message, ScreenType screenType)
    {
        this.message = message;
        this.screenType = screenType;
    }

    public ScreenType getScreenType()
    {
        return screenType;
    }

    public void setScreenType(ScreenType screenType)
    {
        this.screenType = screenType;
    }
}
