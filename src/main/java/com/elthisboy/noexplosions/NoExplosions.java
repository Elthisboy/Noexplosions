package com.elthisboy.noexplosions;

import com.elthisboy.noexplosions.command.ExplosionCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoExplosions implements ModInitializer {
	public static final String MOD_ID = "noexplosions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Estado global: si las explosiones están bloqueadas o no
	public static boolean explosionsBlocked = false;

	@Override
	public void onInitialize() {
		LOGGER.info("[NoExplosions] Mod loaded. Use /explosions to control explosions.");

		// Registrar el comando /explosiones
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			ExplosionCommand.register(dispatcher);
		});
	}
}
