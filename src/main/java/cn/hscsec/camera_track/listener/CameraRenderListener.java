package cn.hscsec.camera_track.listener;

import cn.hscsec.camera_track.CameraTrackMod;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CameraRenderListener {
    @SubscribeEvent
    static void onCameraUpdate(ViewportEvent.ComputeCameraAngles event) {
        CameraTrackMod.nm.sendPos(event.getCamera().getPosition(), event.getCamera().getXRot(), event.getCamera().getYRot());
    }
}
