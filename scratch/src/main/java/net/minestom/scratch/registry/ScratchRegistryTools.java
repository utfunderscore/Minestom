package net.minestom.scratch.registry;

import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.entity.metadata.animal.tameable.WolfMeta;
import net.minestom.server.entity.metadata.other.PaintingMeta;
import net.minestom.server.gamedata.tags.TagManager;
import net.minestom.server.instance.block.banner.BannerPattern;
import net.minestom.server.instance.block.jukebox.JukeboxSong;
import net.minestom.server.item.armor.TrimMaterial;
import net.minestom.server.item.armor.TrimPattern;
import net.minestom.server.item.enchant.*;
import net.minestom.server.message.ChatType;
import net.minestom.server.network.packet.server.CachedPacket;
import net.minestom.server.network.packet.server.SendablePacket;
import net.minestom.server.network.packet.server.common.TagsPacket;
import net.minestom.server.registry.DynamicRegistry;
import net.minestom.server.registry.Registries;
import net.minestom.server.utils.nbt.BinaryTagSerializer;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class ScratchRegistryTools implements Registries {

    private final @NotNull DynamicRegistry<BinaryTagSerializer<? extends LevelBasedValue>> enchantmentLevelBasedValues;
    private final @NotNull DynamicRegistry<BinaryTagSerializer<? extends ValueEffect>> enchantmentValueEffects;
    private final @NotNull DynamicRegistry<BinaryTagSerializer<? extends EntityEffect>> enchantmentEntityEffects;
    private final @NotNull DynamicRegistry<BinaryTagSerializer<? extends LocationEffect>> enchantmentLocationEffects;

    private final @NotNull DynamicRegistry<ChatType> chatType;
    private final @NotNull DynamicRegistry<DimensionType> dimensionType;
    private final @NotNull DynamicRegistry<Biome> biome;
    private final @NotNull DynamicRegistry<DamageType> damageType;
    private final @NotNull DynamicRegistry<TrimMaterial> trimMaterial;
    private final @NotNull DynamicRegistry<TrimPattern> trimPattern;
    private final @NotNull DynamicRegistry<BannerPattern> bannerPattern;
    private final @NotNull DynamicRegistry<WolfMeta.Variant> wolfVariant;
    private final @NotNull DynamicRegistry<Enchantment> enchantment;
    private final @NotNull DynamicRegistry<PaintingMeta.Variant> paintingVariant;
    private final @NotNull DynamicRegistry<JukeboxSong> jukeboxSong;

    private final TagManager tagManager = new TagManager();

    private CachedPacket defaultTags;

    public ScratchRegistryTools() {
        this.enchantmentLevelBasedValues = LevelBasedValue.createDefaultRegistry();
        this.enchantmentValueEffects = ValueEffect.createDefaultRegistry();
        this.enchantmentEntityEffects = EntityEffect.createDefaultRegistry();
        this.enchantmentLocationEffects = LocationEffect.createDefaultRegistry();

        this.chatType = ChatType.createDefaultRegistry();
        this.dimensionType = DimensionType.createDefaultRegistry();
        this.biome = Biome.createDefaultRegistry();
        this.damageType = DamageType.createDefaultRegistry();
        this.trimMaterial = TrimMaterial.createDefaultRegistry();
        this.trimPattern = TrimPattern.createDefaultRegistry();
        this.bannerPattern = BannerPattern.createDefaultRegistry();
        this.wolfVariant = WolfMeta.Variant.createDefaultRegistry();
        this.enchantment = Enchantment.createDefaultRegistry(this);
        this.paintingVariant = PaintingMeta.Variant.createDefaultRegistry();
        this.jukeboxSong = JukeboxSong.createDefaultRegistry();
    }

    @Override
    public @NotNull DynamicRegistry<ChatType> chatType() {
        return chatType;
    }

    @Override
    public @NotNull DynamicRegistry<DimensionType> dimensionType() {
        return dimensionType;
    }

    @Override
    public @NotNull DynamicRegistry<Biome> biome() {
        return biome;
    }

    public @NotNull DynamicRegistry<DamageType> damageType() {
        return damageType;
    }

    @Override
    public @NotNull DynamicRegistry<TrimMaterial> trimMaterial() {
        return trimMaterial;
    }

    @Override
    public @NotNull DynamicRegistry<TrimPattern> trimPattern() {
        return trimPattern;
    }

    @Override
    public @NotNull DynamicRegistry<BannerPattern> bannerPattern() {
        return bannerPattern;
    }

    @Override
    public @NotNull DynamicRegistry<WolfMeta.Variant> wolfVariant() {
        return wolfVariant;
    }

    @Override
    public @NotNull DynamicRegistry<Enchantment> enchantment() {
        return enchantment;
    }

    @Override
    public @NotNull DynamicRegistry<PaintingMeta.Variant> paintingVariant() {
        return paintingVariant;
    }

    @Override
    public @NotNull DynamicRegistry<JukeboxSong> jukeboxSong() {
        return jukeboxSong;
    }

    @Override
    public @NotNull DynamicRegistry<BinaryTagSerializer<? extends LevelBasedValue>> enchantmentLevelBasedValues() {
        return enchantmentLevelBasedValues;
    }

    @Override
    public @NotNull DynamicRegistry<BinaryTagSerializer<? extends ValueEffect>> enchantmentValueEffects() {
        return enchantmentValueEffects;
    }

    @Override
    public @NotNull DynamicRegistry<BinaryTagSerializer<? extends EntityEffect>> enchantmentEntityEffects() {
        return enchantmentEntityEffects;
    }

    @Override
    public @NotNull DynamicRegistry<BinaryTagSerializer<? extends LocationEffect>> enchantmentLocationEffects() {
        return enchantmentLocationEffects;
    }

    private CachedPacket getDefaultTags() {
        var defaultTags = this.defaultTags;
        if (defaultTags == null) {
            final TagsPacket packet = tagManager.packet(this);
            this.defaultTags = defaultTags = new CachedPacket(packet);
        }
        return defaultTags;
    }


    public static final ScratchRegistryTools instance = new ScratchRegistryTools();

    public List<SendablePacket> getRegistryPackets() {
        return List.of(
                chatType().registryDataPacket(this, false),
                dimensionType().registryDataPacket(this, false),
                biome().registryDataPacket(this, false),
                damageType().registryDataPacket(this, false),
                trimMaterial().registryDataPacket(this, false),
                trimPattern().registryDataPacket(this, false),
                bannerPattern().registryDataPacket(this, false),
                wolfVariant().registryDataPacket(this, false),
                enchantment().registryDataPacket(this, false),
                paintingVariant().registryDataPacket(this, false),
                jukeboxSong().registryDataPacket(this, false),
                getDefaultTags()
        );
    }


}
