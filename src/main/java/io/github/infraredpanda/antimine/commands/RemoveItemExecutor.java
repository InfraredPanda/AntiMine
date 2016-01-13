package io.github.infraredpanda.antimine.commands;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.infraredpanda.antimine.AntiMine;

public class RemoveItemExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		if (src instanceof Player)
		{
			String itemId = ctx.<String> getOne("item id").get();
			Optional<ItemType> optionalItemType = Sponge.getRegistry().getType(ItemType.class, itemId);

			if (optionalItemType.isPresent())
			{
				ItemType itemType = optionalItemType.get();
				AntiMine.blockedItemTypes.remove(itemType);
				src.sendMessage(Text.of(TextColors.GREEN, "[AntiMine] Success! ", TextColors.YELLOW, "Removed item."));
			}
			else
			{
				src.sendMessage(Text.of(TextColors.DARK_RED, "[AntiMine] Error! ", TextColors.RED, "The item id provided is invalid!"));
			}
		}
		else if (src instanceof CommandSource)
		{
			src.sendMessage(Text.of(TextColors.DARK_RED, "[AntiMine] Error! ", TextColors.RED, "Must be an in-game player to use /antimine!"));
		}
		return CommandResult.success();
	}
}
