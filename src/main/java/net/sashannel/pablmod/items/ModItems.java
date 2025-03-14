package net.sashannel.pablmod.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.sashannel.pablmod.PablMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sashannel.pablmod.items.custom.GithubItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PablMod.MOD_ID);

    public static final RegistryObject<Item> LUNETTES = ITEMS.register("lunettes",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PABLO_TAB).stacksTo(1)));
    public static final RegistryObject<Item> PABLO = ITEMS.register("pablo",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PABLO_TAB)));
    public static final RegistryObject<Item> GITHUB = ITEMS.register("github",
            () -> new GithubItem(new Item.Properties().tab(ModCreativeModeTab.PABLO_TAB).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}