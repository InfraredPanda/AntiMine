package io.github.infraredpanda.antimine.listeners;

import java.util.Optional;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import io.github.infraredpanda.antimine.AntiMine;

public class PickupEventListener
{
	@SuppressWarnings("rawtypes")
	@Listener
	public void onPickup(ChangeInventoryEvent.Pickup event)
	{
		//TODO: Yell at blood to fix
		if (event.getTargetInventory() instanceof CarriedInventory && ((CarriedInventory) event.getTargetInventory()).getCarrier().isPresent() && 
			((CarriedInventory) event.getTargetInventory()).getCarrier().get() instanceof Player)
		{
			Player holder = (Player) ((CarriedInventory) (event.getTargetInventory())).getCarrier().get();
			
			for (Inventory slot : holder.getInventory().slots())
			{
				Optional<ItemStack> optionalItemStackInSlot = slot.poll();
				
				if(optionalItemStackInSlot.isPresent())
				{
					ItemStack stack = optionalItemStackInSlot.get();
					
					if(AntiMine.blockedItemTypes.contains(stack.getItem()))
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
