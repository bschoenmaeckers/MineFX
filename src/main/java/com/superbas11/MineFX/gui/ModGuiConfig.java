package com.superbas11.MineFX.gui;

import java.util.List;

import com.superbas11.MineFX.ConfigurationHandler;
import com.superbas11.MineFX.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig{

	public ModGuiConfig(GuiScreen guiscreen)
	{
		super(
				guiscreen,
				new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Reference.MOD_ID,
				false,
				true,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString())
			);
	}
	
}
