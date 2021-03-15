package com.magnolia.mdc.models.toolModels;

public enum ToolType {


    DRILLS("Power Drills"),
    HANDTOOLS("Hand Tools"),
    POWERSAWS("Power Saws"),
    MEASURING("Measuring Devices"),
    FISHINGTOOLS("Fishing Tools"),
    SAFETYEQUIPMENT("Saftey & Protection"),
    OTHER("Random Toolss");

    private final String toolType;

    ToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolType() {
        return toolType;
    }
}
