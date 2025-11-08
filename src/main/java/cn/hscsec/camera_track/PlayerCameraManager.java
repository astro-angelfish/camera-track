package cn.hscsec.camera_track;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class PlayerCameraManager {
    private static final Map<Player, Vec3> cameraPosMapping = new HashMap<>();

    public static void updateCameraPos(Player player, Vec3 cameraPos) {
        cameraPosMapping.put(player, cameraPos);
    }

    public static Vec3 getCameraPos(Player player) {
        return cameraPosMapping.get(player);
    }
}
