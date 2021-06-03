package supersql.codegenerator.X3D;

import java.util.Vector;

import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class X3DG2 extends Grouper{
    Manager manager;

    X3DEnv x3d_env;
    
    X3DValue value;

    public X3DG2(Manager manager, X3DEnv lenv) {
        this.manager = manager;
        this.x3d_env = lenv;
    }

    @Override
	public String work(ExtList data_info) {

        Vector vector_local = new Vector();

        Log.out("------- G2 -------");
        this.setDataList(data_info);

        int i = 0;
        int shelfnum = 0;
        boolean add = true;
        value = new X3DValue();
        X3DEnv.bx=X3DEnv.x - 0.2;
        X3DEnv.by=X3DEnv.y - 0.1;
        X3DEnv.bz=X3DEnv.z + 0.3;
        
        String xs, ys, zs;

        String bxs, bys, bzs;
        
        String vxs, vys, vzs;
        
        while (this.hasMoreItems()) {
            x3d_env.glevel++;

            /*if (add == true){
            	x3d_env.code.append("Shelf2 {position " + xs + " " + ys + " " + zs + " }\n");
            	Log.out("Shelf2 {position " + xs + " " + ys + " " + zs + " }");
            	shelfnum++;
            	add = false;
            }
            */        
            
            this.worknextItem();
            
            i++;
            x3d_env.glevel--;
            
            /*if ((i%4) == 0){
            	if (shelfnum % 6 == 0) {
            		x3d_env.x += value.x;
            		x3d_env.bx = x3d_env.x - 0.2;
            		x3d_env.y = value.y_ini;
            		x3d_env.by = x3d_env.y - 0.1;
            		add = true;
            	}
            	else {
            		x3d_env.y += value.y;
            		x3d_env.bx = x3d_env.x;
            		x3d_env.by = x3d_env.y - 0.1;
            		add = true;
            	}
            }
            */
        }
		return null;
    }

    @Override
	public String getSymbol() {
        return "X3DG2";
    }

}
