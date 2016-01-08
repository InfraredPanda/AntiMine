package io.github.infraredpanda.antimine.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class AntiMineExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		if (src instanceof Player)
		{
			Player player = (Player) src;

		}
		else if(src instanceof CommandSource)
		{
			src.sendMessage(Text.of(TextColors.DARK_RED, "[AntiMine] Error! ", TextColors.RED, "Must be an in-game player to use /antimine!"));
		}
		return CommandResult.success();
	}
}
