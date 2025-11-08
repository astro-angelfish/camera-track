package cn.hscsec.camera_track.network;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class NetworkManager {
    private final SimpleChannel channel;

    public NetworkManager(SimpleChannel channel) {
        this.channel = channel;

        this.channel.registerMessage(0, CameraPosPacket.class, CameraPosPacket::encode, CameraPosPacket::decode, CameraPosPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public void sendPos(Vec3 pos, float rotX, float rotY) {
        this.channel.sendToServer(new CameraPosPacket(pos, rotX, rotY));
    }
}
