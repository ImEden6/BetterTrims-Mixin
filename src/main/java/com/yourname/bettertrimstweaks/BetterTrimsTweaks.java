package com.yourname.bettertrimstweaks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterTrimsTweaks implements ModInitializer {
    public static final String MOD_ID = "bettertrims-tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("BetterTrims Tweaks loaded");
    }
}
