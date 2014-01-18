package net.boutopia.ToolWorx.MechArchitech;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class ArchitechBlueprint implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5472644705341505937L;
	private Map<String, BlueprintBlock> ToolBlocks ;
	private  String He ; 
	private Boolean Anchored ; 
	private double Length ;
	private double Height ;
	private double Width ;
	private transient Location AnchorLocation ; 
	private transient MechArchitech plugin ;
	ArchitechBlueprint(MechArchitech plugin)
	{
	ToolBlocks = new HashMap<String,BlueprintBlock>()  ; 
	this.plugin = plugin ;
	Anchored = false ; 
	}
	public void CalculateDimensions()
	{
		double Maxx=0;
		double Maxy=0,Maxz=0,Miny=0,Minz = 0 ;
		double Minx=0;
		Iterator<String> iterator = ToolBlocks.keySet().iterator();
		BlueprintBlock blueprintBlock ;
		while (iterator.hasNext()){
			blueprintBlock = ToolBlocks.get(iterator.next()) ;
			 if (blueprintBlock.getXoffset()<Minx) Minx=blueprintBlock.getXoffset() ;
			 if (blueprintBlock.getXoffset()>Maxx) Maxx=blueprintBlock.getXoffset() ;
			
			 if (blueprintBlock.getYoffset()<Miny) Miny=blueprintBlock.getYoffset() ;
			 if (blueprintBlock.getYoffset()>Maxy) Maxy=blueprintBlock.getYoffset() ;
			
			 if (blueprintBlock.getZoffset()<Minz) Minz=blueprintBlock.getZoffset() ;
			 if (blueprintBlock.getZoffset()>Maxz) Maxz=blueprintBlock.getZoffset() ;
			
			Length = Maxx-Minx+1 ;
			Height = Maxy-Miny+1 ;
			Width = Maxz-Minz+1 ;
		}
    
		
		
	}
	public String DimensionString()
	{
		return Double.toString(Length)+"X"+Double.toString(Height)+"X"+Double.toString(Width);
	}
	public void SetBlock(String currentName, Block block) {
		if (!Anchored) {
			currentName = "Anchor";
			Anchored = true ;
			}
		if (currentName.equals("Anchor"))
		{
			AnchorLocation = block.getLocation() ;
			plugin.getLogger().info("Anchor Set");
		}
		int xOffset = block.getX() - AnchorLocation.getBlockX()  ;
		int yOffset = block.getY() - AnchorLocation.getBlockY()  ;;
		int zOffset = block.getZ() - AnchorLocation.getBlockZ()  ;;
		BlueprintBlock BPB = new BlueprintBlock(block,plugin,xOffset,yOffset,zOffset) ;
	ToolBlocks.put(currentName, BPB) ;
		
	}
	/**
	 * @return the he
	 */
	public String getHe() {
		return He;
	}
	/**
	 * @param he the he to set
	 */
	public void setHe(String he) {
		He = he;
	}

	public Set<String> getkeySet() {
		// TODO Auto-generated method stub
		return ToolBlocks.keySet();
	}

	public BlueprintBlock getBlock(String key) {
						return ToolBlocks.get(key) ; 
	
	}
	
	public Location GetAnchorLocation()
	{
	return AnchorLocation ;	
	}
	public void RemoveBlock(String key) {
		ToolBlocks.remove(key) ;
		
	}
}