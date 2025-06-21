package org.k4n3d4;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

public class NoJumpDelayPlugin extends Plugin {
	
	public static long minecraftInitTime = 0L;
	public static NoJumpDelayPlugin INSTANCE;
	
	@Override
	public void onLoad() {
		INSTANCE = this;

		final NoJumpDelayModule noJumpDelayModule = new NoJumpDelayModule();
		RusherHackAPI.getModuleManager().registerFeature(noJumpDelayModule);

		this.getLogger().info("Loading NoJumpDelay plugin");
	}

	@Override
	public void onUnload() {
		this.getLogger().info("NoJumpDelay plugin unloaded!");
	}
	
}