package supersql.codegenerator.X3D;

import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class X3DG1 extends Grouper{
    Manager manager;

    X3DEnv x3d_env;
    
    X3DValue value;


    public X3DG1(Manager manager, X3DEnv lenv) {
        this.manager = manager;
        this.x3d_env = lenv;
    }


    @Override
	public String work(ExtList data_info) {
        Log.out("------- G1 -------");
        this.setDataList(data_info);

        int i = 0;
        boolean add = true;
        value = new X3DValue();

        String xs, ys, zs;
        X3DEnv.bx=X3DEnv.x - 0.2;
        X3DEnv.by=X3DEnv.y - 0.1;
        X3DEnv.bz=X3DEnv.z + 0.3;
        
        String bxs, bys, bzs;
        
        String vxs, vys, vzs;
        
        while (this.hasMoreItems()) {
            x3d_env.glevel++;
            
            if (add == true) {
            x3d_env.code.append("Shelf2 {position " + X3DEnv.x + " " + X3DEnv.y + " " + X3DEnv.z + " }\n");
            Log.out("Shelf2 {position " + X3DEnv.x + " " + X3DEnv.y + " " + X3DEnv.z + " }");
            add = false;
            }
            
            this.worknextItem();

            i++;
            x3d_env.glevel--;
            X3DEnv.groupflag = 1;
            
            if (i%4 == 0) {
            X3DEnv.x += 4;
            X3DEnv.bx = X3DEnv.x-0.20;
            add = true;
            }
        }
		return null;
    }

    @Override
	public String getSymbol() {
        return "X3DG1";
    }

}
