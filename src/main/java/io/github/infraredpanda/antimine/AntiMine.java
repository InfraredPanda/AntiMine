package io.github.infraredpanda.antimine;

import java.util.Set;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

import io.github.infraredpanda.antimine.commands.AddBlockExecutor;
import io.github.infraredpanda.antimine.commands.AddItemExecutor;
import io.github.infraredpanda.antimine.commands.RemoveBlockExecutor;
import io.github.infraredpanda.antimine.commands.RemoveItemExecutor;
import io.github.infraredpanda.antimine.listeners.BreakBlockListener;
import io.github.infraredpanda.antimine.listeners.PickupEventListener;

@Plugin(id = "AntiMine", name = "AntiMine", version = "0.1")
public class AntiMine
{
	public static Set<ItemType> blockedItemTypes = Sets.newHashSet();
	public static Set<BlockType> blockedBlockTypes = Sets.newHashSet();

	@Inject
	private Logger logger;

	public Logger getLogger()
	{
		return logger;
	}

	@Listener
	public void onServerPreInit(GamePreInitializationEvent event)
	{
		getLogger().info("AntiMine loading...");
	}

	@Listener
	public void onServerInit(GameInitializationEvent event)
	{
		CommandSpec addItemCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine List Add Item"))
			.permission("antimine.use.add")
			.arguments(GenericArguments.onlyOne(GenericArguments.remainingJoinedStrings(Text.of("item id"))))
			.executor(new AddItemExecutor())
			.build();

		CommandSpec removeItemCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine List Remove Item"))
			.permission("antimine.use.remove")
			.arguments(GenericArguments.remainingJoinedStrings(Text.of("item id")))
			.executor(new RemoveItemExecutor())
			.build();

		CommandSpec addBlockCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine List Add Block"))
			.permission("antimine.use.add")
			.arguments(GenericArguments.onlyOne(GenericArguments.remainingJoinedStrings(Text.of("block id"))))
			.executor(new AddBlockExecutor())
			.build();

		CommandSpec removeBlockCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine List Remove Block"))
			.permission("antimine.use.remove")
			.arguments(GenericArguments.remainingJoinedStrings(Text.of("block id")))
			.executor(new RemoveBlockExecutor())
			.build();

		CommandSpec antiMineCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine List Add or Remove"))
			.permission("antimine.use")
			.child(addItemCommandSpec, "additem")
			.child(removeItemCommandSpec, "removeitem")
			.child(addBlockCommandSpec, "addblock")
			.child(removeBlockCommandSpec, "removeblock")
			.build();

		Sponge.getGame().getCommandManager().register(this, antiMineCommandSpec, "am", "antimine");

		Sponge.getEventManager().registerListeners(this, new PickupEventListener());
		Sponge.getEventManager().registerListeners(this, new BreakBlockListener());
	}

	@Listener
	public void onServerPostInit(GamePostInitializationEvent event)
	{
		getLogger().info("AntiMine loaded!");
	}
}
