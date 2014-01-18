/**
 * 
 */
package net.boutopia.ToolWorx.TWxCore;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.boutopia.ToolWorx.MechArchitech.ArchitechBlueprint;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;




/**
 * @author bulshavik
 *
 */
public class TWxCore extends JavaPlugin {

	
	static Server server ;
	private List <ArchitechBlueprint>  blueprints;
	/**
	 * 
	 */
	public TWxCore() {
		// TODO Auto-generated constructor stub
	}
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	    	if(cmd.getName().equalsIgnoreCase("tw")){ // If the player typed /basic then do the following...
	    		if(args.length < 1) return false ;
	    		getLogger().info("Command Called\n" + args[0]);
	    		
	    		if(args[0].equalsIgnoreCase("list")){
	    			ArchitechBlueprint bprint ;
	    			Iterator<ArchitechBlueprint> iterator = blueprints.iterator();
	    			while (iterator.hasNext()){
	    				bprint = iterator.next() ;
	    				getLogger().info(bprint.DimensionString());
	    				
	    				
	    			}
	    		}
	    			
	    			
	    	
	    }return true;
	    	}
	 
	 public ArchitechBlueprint rotateBP(Location loc,ArchitechBlueprint Blueprint) {
		
			Block Anchor = loc.getWorld().getBlockAt(loc);
			int holding ;
			Anchor.setType(Material.getMaterial(Blueprint.getBlock("Anchor").getMaterial()));
			Set<String> keys = Blueprint.getkeySet() ;
			Iterator<String> iterator = keys.iterator() ;
			String key ;
			while (iterator.hasNext()){
				   key = iterator.next() ;
				   holding = Blueprint.getBlock(key).getXoffset() ;
				   Blueprint.getBlock(key).setXoffset(-Blueprint.getBlock(key).getZoffset());
				   //if (holding < 0) Blueprint.getBlock(key).setZoffset(-holding); 
				   //else
				   Blueprint.getBlock(key).setZoffset(holding); 
			}		  
	   return Blueprint ;
	 					
			}
	public Boolean detect(Location loc,ArchitechBlueprint Blueprint){
		Block Anchor = loc.getWorld().getBlockAt(loc);
		Block B ;
		if (!Anchor.getType().toString().equals(Blueprint.getBlock("Anchor").getMaterial())) return false ;
		Set<String> keys = Blueprint.getkeySet() ;
		Iterator<String> iterator = keys.iterator() ;
		String key ;
		while (iterator.hasNext()){
			   key = iterator.next() ;
			   B = Anchor.getRelative(Blueprint.getBlock(key).getXoffset(),
					   					Blueprint.getBlock(key).getYoffset(),
					   					Blueprint.getBlock(key).getZoffset());
			   if (!B.getType().toString().equals(Blueprint.getBlock(key).getMaterial())) return false ;
							
		} 
		getLogger().info("Print Detected!!!");
		return true ;
	}
	@Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("onEnable has been invoked!");
		server = this.getServer() ;
	 getServer().getPluginManager().registerEvents(new TWxListener(this), this);
		getLogger().info("TWxListener registered!");
	blueprints = new ArrayList <ArchitechBlueprint>(); 
	{ getLogger().info("load called");
	try{
			       FileInputStream fin = new FileInputStream("F:\\a.txt");
				   ObjectInputStream ois = new ObjectInputStream(fin);
				   blueprints.add((ArchitechBlueprint) ois.readObject());
				   ois.close();
				   getLogger().info(" Core load done");
				 
		 
			   }catch(Exception ex){
				   getLogger().info("load fail");
				   ex.printStackTrace();
				 
			   } 
		}

	
	

	}
 
    @Override
    public void onDisable() {
    	
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("onDisable has been invoked!");
    }
	public void onLever(Player player, Block attachedTo,
			BlockFace oppositeFace, ItemStack item) {
		getLogger().info("onLever Called");
		ArchitechBlueprint bprint ;
		Iterator<ArchitechBlueprint> iterator = blueprints.iterator();
		while (iterator.hasNext()){
			bprint = iterator.next() ;
			getLogger().info(bprint.DimensionString());
			if (detect(attachedTo.getLocation(),bprint))return;
			rotateBP(attachedTo.getLocation(),bprint);
			if (detect(attachedTo.getLocation(),bprint))return;
			rotateBP(attachedTo.getLocation(),bprint);
			if (detect(attachedTo.getLocation(),bprint))return;
			rotateBP(attachedTo.getLocation(),bprint);
			if (detect(attachedTo.getLocation(),bprint))return;
			
			
		}
		getLogger().info(blueprints.get(0).DimensionString());
		// TODO Auto-generated method stub
		
	}

}
