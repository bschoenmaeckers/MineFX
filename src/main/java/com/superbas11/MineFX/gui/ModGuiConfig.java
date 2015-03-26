package com.superbas11.MineFX.gui;

import java.util.ArrayList;
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
				getConfigElements(),
				Reference.MOD_ID,
				false,
				true,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString())
			);
	}
	
	private static List getConfigElements()
	{
		List configElements = new ArrayList();
		
		configElements.addAll(new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements());
		configElements.addAll(new ConfigElement(ConfigurationHandler.configuration.getCategory("lightfx proxy")).getChildElements());
		
		return configElements;
	}
	
}
