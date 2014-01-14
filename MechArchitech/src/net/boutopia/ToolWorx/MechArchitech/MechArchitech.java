/**
 * 
 */
/**
 * @author bulshavik
 *
 */
package net.boutopia.ToolWorx.MechArchitech ;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector ;
public final class MechArchitech extends JavaPlugin {
	static Server server ;
	@Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("onEnable has been invoked!");
		server = this.getServer() ;
	}
 
    @Override
    public void onDisable() {
    	
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("onDisable has been invoked!");
    }
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("basic")){ // If the player typed /basic then do the following...
    		getLogger().info("Command Called");
    		Vector East = new Vector(1,0,0) ;
    		Vector North = new Vector(0,0,1) ;
    		Block block = null ;
    		int x = 0 ;
    		Player player = server.getPlayer(sender.getName());
    		Vector start = player.getLocation().toVector() ;
    		while (x < 50) {
    			
    		
    		BlockIterator iterator  = new BlockIterator(player.getWorld(),start,East,1,50) ;
    		while (iterator.hasNext())
    		{
    			block = iterator.next() ;
    			block.setType(Material.BEDROCK);
    		}
    		x = x+1  ;
    		start = start.add(North) ;
    		}
    		player.teleport(player.getLocation().subtract(block.getLocation())) ;
    		
    		return true;
    	} //If this has happened the function will return true. 
            // If this hasn't happened the a value of false will be returned.
    	return false; 
    }
}