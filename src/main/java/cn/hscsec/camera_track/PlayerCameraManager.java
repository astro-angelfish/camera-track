package cn.hscsec.camera_track;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class PlayerCameraManager {
    private static final Map<Player, Vec3> cameraPosMapping = new HashMap<>();
    private static final Map<Player, Vec2> cameraRotationMapping = new HashMap<>();

    public static void updateCameraPos(Player player, Vec3 cameraPos, Vec2 cameraRotation) {
        cameraPosMapping.put(player, cameraPos);
        cameraRotationMapping.put(player, cameraRotation);
    }

    public static Vec3 getCameraPos(Player player) {
        return cameraPosMapping.get(player);
    }

    public static Vec2 getCameraRotation(Player player) {
        return cameraRotationMapping.get(player);
    }
}
