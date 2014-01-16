/**
 * 
 */
/**
 * @author bulshavik
 *
 */
package net.boutopia.ToolWorx.MechArchitech ;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;












//import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.*;

//import org.bukkit.entity.Player;
//import org.bukkit.util.BlockIterator;
//import org.bukkit.util.Vector ;
public final class MechArchitech extends JavaPlugin {
	ArchitechBlueprint blueprint ;
	static String CurrentName ; 
	static Block CurrentBlock ;
//	static Map<String, Block> ToolBlocks ;
	static Server server ;
	public void BlockClicked(Block block){
		 getLogger().info(block.toString());
		 getLogger().info(CurrentName);
		 blueprint.SetBlock(CurrentName,block);
	}
	@Override
    public void onEnable(){
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("onEnable has been invoked!");
		server = this.getServer() ;
	 getServer().getPluginManager().registerEvents(new ArchitechListener(this), this);
	 CurrentName = "test" ;
	// ToolBlocks = new HashMap<String ,Block>() ;
	 blueprint = new ArchitechBlueprint() ;
	}
 
    @Override
    public void onDisable() {
    	
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("onDisable has been invoked!");
    }
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("basic")){ // If the player typed /basic then do the following...
    		if(args.length < 1) return false ;
    		getLogger().info("Command Called\n" + args[0]);
    		if(args[0].equalsIgnoreCase("setblock"))
    				{
    			CurrentName = args[1] ;
    			    				}
    		if(args[0].equalsIgnoreCase("list"))
			{
    //			Set<String> keys = blueprint.getkeySet() ;
    	//		Iterator<String> iterator = keys.iterator() ;
    	//		String key ;
    	//		while (iterator.hasNext()){
    	//			   key = iterator.next() ;
    	//			   getLogger().info(key + " "+ blueprint.get(key).getType().toString());
    	//		}
		    
		    				}
    		if(args[0].equalsIgnoreCase("sethe"))
			{
    			blueprint.setHe(args[1]);
    			System.out.println("sethe to "+ blueprint.getHe());
			}
    		
			if(args[0].equalsIgnoreCase("save"))
			{
				
    			ArchitechBlueprint blueprint = new ArchitechBlueprint() ;
		 	FileOutputStream fout;
				try {
					fout = new FileOutputStream("F:\\a.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fout);   
	    			oos.writeObject(blueprint);
	    			oos.close();
	    			System.out.println("Done");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				if(args[0].equalsIgnoreCase("load"))
				{ getLogger().info("load called");
				 getLogger().info("He "+ blueprint.getHe());
					blueprint = null ;
						 try{
						       FileInputStream fin = new FileInputStream("F:\\a.txt");
							   ObjectInputStream ois = new ObjectInputStream(fin);
							   blueprint=(ArchitechBlueprint) ois.readObject();
							   ois.close();
							   getLogger().info("load done");
							 
					 
						   }catch(Exception ex){
							   getLogger().info("load fail");
							   ex.printStackTrace();
							 
						   } 
						 getLogger().info("He "+ blueprint.getHe());
					}

				

			
	
 //   		Vector East = new Vector(1,0,0) ;
   // 		Vector North = new Vector(0,0,1) ;
 //   		Block block = null ;
  //  		int x = 0 ;
  //  		Player player = server.getPlayer(sender.getName());
  //  		Vector start = player.getLocation().toVector() ;
    	//	while (x < 50) {
    			
    		
 //   		BlockIterator iterator  = new BlockIterator(player.getWorld(),start,East,1,50) ;
   /* 		while (iterator.hasNext())
    		{
    			block = iterator.next() ;
    			block.setType(Material.BEDROCK);
    		}
    		x = x+1  ;
    		
    		start = start.add(North) ;
    		} */
    	//	player.teleport(player.getLocation().subtract(block.getLocation())) ;
    		
    		return true;
    	}
           //If this has happened the function will return true. 
            // If this hasn't happened the a value of false will be returned.
    	return false; 
    }

	}
