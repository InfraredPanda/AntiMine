package io.github.infraredpanda.antimine.commands;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.infraredpanda.antimine.AntiMine;

public class RemoveBlockExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		if (src instanceof Player)
		{
			String blockId = ctx.<String> getOne("block id").get();
			Optional<BlockType> optionalBlockType = Sponge.getRegistry().getType(BlockType.class, blockId);

			if (optionalBlockType.isPresent())
			{
				BlockType blockType = optionalBlockType.get();
				AntiMine.blockedBlockTypes.remove(blockType);
				src.sendMessage(Text.of(TextColors.GREEN, "[AntiMine] Success! ", TextColors.YELLOW, "Removed block."));
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
