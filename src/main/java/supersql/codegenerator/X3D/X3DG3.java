package supersql.codegenerator.X3D;

import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class X3DG3 extends Grouper{
	   Manager manager;

	    X3DEnv x3d_env;
	    
	    X3DValue value;
	    
	    private String backfile = new String();

	    private int countinstance = 0;

	    public X3DG3(Manager manager, X3DEnv lenv) {
	        this.manager = manager;
	        this.x3d_env = lenv;
	    }

	    @Override
		public String work(ExtList data_info) {
	        Log.out("------- G3 -------");
	        this.setDataList(data_info);
	        
	        int i = 0;
	        value = new X3DValue();

	        X3DEnv.bx=X3DEnv.x - 0.20;
	        X3DEnv.by=X3DEnv.y - 0.10;
	        X3DEnv.bz=X3DEnv.z + 0.30;
	        
            X3DEnv.media = this.decos.getStr("media");
            X3DEnv.object = this.decos.getStr("object");
          
	        X3DEnv.x3d_add = 1;
	        
	        while (this.hasMoreItems()) {
	            x3d_env.glevel++;
	            X3DEnv.tateflag = 1;

	            x3d_env.code.append("Shelf2{position " + X3DEnv.x + " " + X3DEnv.y + " " + X3DEnv.z + " }\n");
	            Log.out("Shelf2{position " + X3DEnv.x + " " + X3DEnv.y + " " + X3DEnv.z + " }");
	                	          
	            this.worknextItem();
	            
	            i++;
	            x3d_env.glevel--;
	            X3DEnv.groupflag = 1;
	            X3DEnv.bookcount = 1;
	            
	            X3DEnv.z += value.z;
	            X3DEnv.x = X3DEnv.xi;
	            X3DEnv.bz = X3DEnv.z-0.2;
	            X3DEnv.length = X3DEnv.zi - X3DEnv.z + 10.0;
	            X3DEnv.lz[X3DEnv.lcount] = X3DEnv.zi-X3DEnv.length/2.0 + 10.0;
	            X3DEnv.rwz = X3DEnv.lz[X3DEnv.lcount];
	            X3DEnv.lwz = X3DEnv.lz[X3DEnv.lcount];
	            X3DEnv.fwz = X3DEnv.lz[X3DEnv.lcount] + X3DEnv.length/2.0 - 0.5;
	            X3DEnv.bwz = X3DEnv.lz[X3DEnv.lcount] - X3DEnv.length/2.0 + 0.5;
	        }
	        	X3DEnv.wid[X3DEnv.lcount] = X3DEnv.width;
	        	X3DEnv.len[X3DEnv.lcount] = X3DEnv.length;
	        	X3DEnv.hei[X3DEnv.lcount] = X3DEnv.height;
	        	X3DEnv.rwxl[X3DEnv.lcount] = X3DEnv.rwx;
	        	X3DEnv.rwyl[X3DEnv.lcount] = X3DEnv.rwy;
	        	X3DEnv.rwzl[X3DEnv.lcount] = X3DEnv.rwz;
	        	X3DEnv.lwxl[X3DEnv.lcount] = X3DEnv.lwx;
	        	X3DEnv.lwyl[X3DEnv.lcount] = X3DEnv.lwy;
	        	X3DEnv.lwzl[X3DEnv.lcount] = X3DEnv.lwz;
	        	X3DEnv.fwxl[X3DEnv.lcount] = X3DEnv.fwx;
	        	X3DEnv.fwyl[X3DEnv.lcount] = X3DEnv.fwy;
	        	X3DEnv.fwzl[X3DEnv.lcount] = X3DEnv.fwz;
	        	X3DEnv.bwxl[X3DEnv.lcount] = X3DEnv.bwx;
	        	X3DEnv.bwyl[X3DEnv.lcount] = X3DEnv.bwy;
	        	X3DEnv.bwzl[X3DEnv.lcount] = X3DEnv.bwz;
		        X3DEnv.xil[X3DEnv.lcount] = X3DEnv.xi;
	        	
	        	X3DEnv.lcount++;
	        	X3DEnv.x3d_add = 0;
	        	X3DEnv.xmax = 0;
	        	x3d_env.append_css_def_td(X3DEnv.getClassID(this), this.decos);
				return null;
	   
	    }

	    @Override
		public String getSymbol() {
	        return "X3DG3";
	    }

}
