package cn.hscsec.camera_track;

import cn.hscsec.camera_track.listener.CameraRenderListener;
import cn.hscsec.camera_track.network.NetworkManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(CameraTrackMod.MODID)
@SuppressWarnings("removal")
public class CameraTrackMod {
    public static final String MODID = "camera_track";

    public static NetworkManager nm;

    public CameraTrackMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        SimpleChannel sc = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(MODID, "camera_pos"), () -> "1", (s) -> s.equals("1"), (s) -> s.equals("1"));
        nm = new NetworkManager(sc);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MinecraftForge.EVENT_BUS.register(CameraRenderListener.class);
        }
    }
}
