package com.agiledge.atom.dao.intfc;

import com.agiledge.atom.dto.SettingsDTO;
import com.agiledge.atom.entities.Setting;

public interface SettingsDao {

	public Setting getSettings() throws Exception;

	public SettingsDTO getSettingValue(SettingsDTO settingsDto) throws Exception;

	public void getSettforceUpdateSettings(SettingsDTO settingsDto) throws Exception;

	}
