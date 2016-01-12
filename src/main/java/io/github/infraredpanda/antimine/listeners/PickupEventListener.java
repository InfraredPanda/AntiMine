package io.github.infraredpanda.antimine.listeners;

import java.util.Optional;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;

import io.github.infraredpanda.antimine.AntiMine;

public class PickupEventListener
{
	@Listener
	public void onPickup(ChangeInventoryEvent.Pickup event)
	{
		if (event.getTargetInventory() instanceof Player)
		{
			Player holder = (Player) event.getTargetInventory();

			for (Inventory slot : holder.getInventory().slots())
			{
				Optional<ItemStack> optionalItemStackInSlot = slot.poll();
				
				if(optionalItemStackInSlot.isPresent())
				{
					ItemStack stack = optionalItemStackInSlot.get();
					
					if(AntiMine.blockedItemTypes.contains(stack) || AntiMine.blockedBlockTypes.contains(stack))
					{
						event.setCancelled(true);
					}
				}
			}
		}

	}

}
