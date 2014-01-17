/**
 * 
 */
package net.boutopia.ToolWorx.MechArchitech;



//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;


import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;

/**
 * @author user
 *
 */
public class BlueprintBlock implements  java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private int Xoffset;
	private int Yoffset;
	private int Zoffset;
	private String material ;
	private Map<String,String> Params ;
	
	
//	private Map<String,String> Blockdata ;
	
	/**
	 * 
	 */
	
BlueprintBlock(Block block,MechArchitech plugin,int x,int y,int z) {
		setMaterial(block.getType().toString());
		setXoffset(x) ;
		setYoffset(y) ;
		setZoffset(z) ;
		Params = new HashMap<String,String>();
		}

	/**
	 * @return the zoffset
	 */
	public int getZoffset() {
		return Zoffset;
	}

	/**
	 * @param zoffset the zoffset to set
	 */
	public void setZoffset(int zoffset) {
		Zoffset = zoffset;
	}

	/**
	 * @return the yoffset
	 */
	public int getYoffset() {
		return Yoffset;
	}

	/**
	 * @param yoffset the yoffset to set
	 */
	public void setYoffset(int yoffset) {
		Yoffset = yoffset;
	}

	/**
	 * @return the xoffset
	 */
	public int getXoffset() {
		return Xoffset;
	}

	/**
	 * @param xoffset the xoffset to set
	 */
	public void setXoffset(int xoffset) {
		Xoffset = xoffset;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}


}

