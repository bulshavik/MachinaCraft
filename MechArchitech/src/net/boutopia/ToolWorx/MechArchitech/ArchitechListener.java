package net.boutopia.ToolWorx.MechArchitech;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArchitechListener implements Listener {

	MechArchitech plugin ;
	public ArchitechListener(MechArchitech mechArchitech) {
		this.plugin = mechArchitech ; 
		
		
		
	}

	 @EventHandler
	    public void onClick(PlayerInteractEvent event) {
		if (plugin.isPickBlock()){
			if (event.getPlayer().getItemInHand().getType().equals(Material.STICK)){
				 plugin.getLogger().info("Pick Block fired ") ;
				 Block block = event.getClickedBlock() ;
				 plugin.BlockClicked(block);
			}
		}
		else{
		 if(event.isBlockInHand()){
		  plugin.getLogger().info("Event Fired ") ;
	      Block block = event.getClickedBlock().getRelative(event.getBlockFace()) ;
	      block.setType(event.getPlayer().getItemInHand().getType());
	      plugin.getLogger().info("Event Fired " + block.getType().toString());
	      plugin.BlockClicked(block);
	     
	    }
	 }}

	
}
