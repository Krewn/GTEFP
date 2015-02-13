package gtefpBlocks;

public interface Ysizeable {
	/* So here are the basics
	 *  > The height of a line of text is 4.
	 *  > This allows for contextual polygons to be drawn with
	 *  > A reasonable resolution.
	 *  
	 *  A socket looks like this
	 *  _OGxs = new int[]{2,4,2,0,2,2,1,2,3,2};
		_OGys = new int[]{0,2,4,2,0,1,2,3,2,1};		
	        #1  
	      //#\\   as such Each line of text will make for a height of 4
	     //   \\
	     ##   ##  public class <> extends <> implements <>{
	     \\   //
	      \\#//
	        #	   			#1   _________________________________________________
					      //#\\                                                   |
					     //   \\                                                  |
					     ##   ##        for(int k = <> ; <> ; <>){                |
					     \\   //         ________________________________________ |
					      \\#//          |    //\\
										     //  \\
										     \\  //
								______________\\//    }
								 //\\
						        //  \\
								\\  //
								_\\// 
						}					        	    
		 
	 * 
	 * */
	public int ysize();
	}
