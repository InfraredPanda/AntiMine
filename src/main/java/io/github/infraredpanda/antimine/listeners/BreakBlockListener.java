package io.github.infraredpanda.antimine.listeners;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

import io.github.infraredpanda.antimine.AntiMine;

public class BreakBlockListener
{
	@Listener
	public void onPlayerBreakBlock(ChangeBlockEvent.Break event)
	{
		if(event.getCause().first(Player.class).isPresent())
		{
			for(Transaction<BlockSnapshot> transaction : event.getTransactions())
			{
				if(AntiMine.blockedBlockTypes.contains(transaction.getOriginal().getState().getType()))
				{
					event.setCancelled(true);
				}
			}
		}
	}
}
