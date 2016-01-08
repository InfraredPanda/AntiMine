package io.github.infraredpanda.antimine;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;

import io.github.infraredpanda.antimine.commands.AntiMineExecutor;

@Plugin(id = "AntiMine", name = "AntiMine", version = "0.1")
public class AntiMine
{
	public static Game game;
	
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
		game = Sponge.getGame();
		
		CommandSpec AntiMineCommandSpec = CommandSpec.builder()
			.description(Text.of("AntiMine GUI"))
			.permission("AntiMine.use")
			.executor(new AntiMineExecutor())
			.build();

		game.getCommandManager().register(this, AntiMineCommandSpec, "am", "antimine");
	}

	@Listener
	public void onServerPostInit(GamePostInitializationEvent event)
	{
		getLogger().info("FindTheCenter loaded!");
	}
}
