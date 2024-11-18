package luckytntlib.network;

import java.util.function.Supplier;

import luckytntlib.client.ClientAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class ClientboundUpdateSkyLightSourcesPacket {

	private final ChunkPos pos;
	private final int[] data;
	
	public ClientboundUpdateSkyLightSourcesPacket(ChunkPos pos, int[] data) {
		this.pos = pos;
		this.data = data;
	}
	
	public ClientboundUpdateSkyLightSourcesPacket(FriendlyByteBuf buffer) {
		pos = new ChunkPos(buffer.readInt(), buffer.readInt());
		data = buffer.readVarIntArray();
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(pos.x);
		buffer.writeInt(pos.z);
		buffer.writeVarIntArray(data);
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientAccess.updateChunkSkyLightSources(pos, data)));
		ctx.get().setPacketHandled(true);
	}
}
