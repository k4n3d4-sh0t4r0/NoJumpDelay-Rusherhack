package org.k4n3d4;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.k4n3d4.mixins.LivingEntityAccessor;
import org.rusherhack.client.api.events.client.EventUpdate;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.client.api.utils.ChatUtils;
import org.rusherhack.core.event.subscribe.Subscribe;
import org.rusherhack.core.setting.NumberSetting;

public class NoJumpDelayModule extends ToggleableModule{
    public static boolean IsEnable = false;
    public static final NumberSetting<Integer> delay = new NumberSetting<>("Delay", "Delay in ticks between touching ground and jumping",0, 0, 20);

    public NoJumpDelayModule() {
        super("NoJumpDelay", "Set a custom delay between jumps when holding space key", ModuleCategory.CLIENT);

        this.registerSettings(
                this.delay
        );
    }

    private int jumpCooldown = 0;

    @Subscribe
    private void onUpdate(EventUpdate event) {
        LocalPlayer player = Minecraft.getInstance().player;
        LivingEntityAccessor accessor = (LivingEntityAccessor) player;

        accessor.setNoJumpDelay(100);

        if (jumpCooldown > 0) {
            if (!player.input.keyPresses.jump()) {
                jumpCooldown = 0;
                accessor.setNoJumpDelay(0);
            }
            else {
                jumpCooldown--;
            }
        }

        if (player.input.keyPresses.jump() && player.onGround() && jumpCooldown == 0) {
            accessor.setNoJumpDelay(0);
            jumpCooldown = delay.getValue();
        }
    }


    @Override
    public void onEnable() {
        IsEnable = true;
    }

    @Override
    public void onDisable() {
        IsEnable = false;
    }
}
