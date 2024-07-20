package net.sashannel.pablmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashannel.pablmod.PablMod;
import net.sashannel.pablmod.items.ModCreativeModeTab;
import net.sashannel.pablmod.items.ModItems;

import java.util.function.Supplier;

import static net.sashannel.pablmod.items.ModCreativeModeTab.PABLO_TAB;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PablMod.MOD_ID);

    public static final RegistryObject<Block> PABLOCK = registerBlock("pablock",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()), PABLO_TAB);
    public static final RegistryObject<Block> PABLORE = registerBlock("pablore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(6, 9)),
                    PABLO_TAB);
    public static final RegistryObject<Block> PABLOREDEEP = registerBlock("pabloredeep",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(), UniformInt.of(6, 9)),
            PABLO_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    };

    private static <T extends Block> RegistryObject<Item> registerBlockItem(final String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    };

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
