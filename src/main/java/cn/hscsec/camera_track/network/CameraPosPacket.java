package cn.hscsec.camera_track.network;

import cn.hscsec.camera_track.PlayerCameraManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CameraPosPacket {
    private final Vec3 pos;

    public CameraPosPacket(double x, double y, double z) {
        this.pos = new Vec3(x, y, z);
    }

    public CameraPosPacket(Vec3 pos) {
        this.pos = pos;
    }

    public static void encode(CameraPosPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.getPos().x);
        buf.writeDouble(packet.getPos().y);
        buf.writeDouble(packet.getPos().z);
    }

    public static CameraPosPacket decode(FriendlyByteBuf buf) {
        return new CameraPosPacket(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public Vec3 getPos() {
        return pos;
    }

    public static void handle(CameraPosPacket packet, Supplier<NetworkEvent.Context> ctx) {
        PlayerCameraManager.updateCameraPos(ctx.get().getSender(), packet.getPos());
    }
}
