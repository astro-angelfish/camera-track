package cn.hscsec.camera_track.network;

import cn.hscsec.camera_track.PlayerCameraManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CameraPosPacket {
    private final Vec3 pos;
    private final Vec2 rot;

    public CameraPosPacket(double x, double y, double z, float rotX, float rotY) {
        this.pos = new Vec3(x, y, z);
        this.rot = new Vec2(rotX, rotY);
    }

    public CameraPosPacket(Vec3 pos, float rotX, float rotY) {
        this.pos = pos;
        this.rot = new Vec2(rotX, rotY);
    }

    public static void encode(CameraPosPacket packet, FriendlyByteBuf buf) {
        buf.writeDouble(packet.getPos().x);
        buf.writeDouble(packet.getPos().y);
        buf.writeDouble(packet.getPos().z);
        buf.writeFloat(packet.rot.x);
        buf.writeFloat(packet.rot.y);
    }

    public static CameraPosPacket decode(FriendlyByteBuf buf) {
        return new CameraPosPacket(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readFloat(), buf.readFloat());
    }

    public Vec3 getPos() {
        return pos;
    }

    public Vec2 getRot() {
        return rot;
    }

    public static void handle(CameraPosPacket packet, Supplier<NetworkEvent.Context> ctx) {
        PlayerCameraManager.updateCameraPos(ctx.get().getSender(), packet.getPos(), packet.getRot());
    }
}
