package supersql.codegenerator.X3D;

import java.io.PrintWriter;
import java.util.Vector;

import supersql.codegenerator.DecorateList;
import supersql.codegenerator.ITFE;
import supersql.codegenerator.LocalEnv;
import supersql.common.Log;

public class X3DEnv extends LocalEnv{

    String data;

    String pre_operator;

    Vector written_classid;

    int total_element = 0;

    int glevel = 0;

    String filename;

    String outfile;

    String linkoutfile;

    String nextbackfile = new String();

    String outdir;

    int countfile;

    PrintWriter writer;

    StringBuffer code;

    StringBuffer css;

    StringBuffer header;

    StringBuffer footer;

    boolean sinvoke_flag = false;

    int link_flag;

    String linkurl;
    
    private String KeisenMode = "";
    
    static String media;
    static String shelf;
    static String object;
    
    static double x=0.0, y=0.5, z=0.0;
    static double bx, by, bz;
    static double vx, vy, vz;
    
    static double length;
    static double width;
    static double height = 16.0;
    static double[] len = new double[100];
    static double[] wid = new double[100];
    static double[] hei = new double[100];
    
    static double[] lx = new double[100];
    static double[] ly = new double[100];
    static double[] lz = new double[100];
    
    static double rwx, rwy = 7.5, rwz;
    static double lwx, lwy = 7.5, lwz;
    static double fwx, fwy = 7.5, fwz;
    static double bwx, bwy = 7.5, bwz;
    static double [] rwxl = new double[100];
    static double [] lwxl= new double[100];
    static double [] fwxl = new double[100];
    static double [] bwxl = new double[100];
    static double [] rwyl = new double[100];
    static double [] lwyl = new double[100];
    static double [] fwyl = new double[100];
    static double [] bwyl = new double[100];
    static double [] rwzl = new double[100];
    static double [] lwzl = new double[100];
    static double [] fwzl = new double[100];
    static double [] bwzl = new double[100];
    
    static int bookcount = 1;
    
    static double xmax = 0, ymax = 0, zmax = 0;
    
    static int groupflag = 1;
    static int linkflag = 1;
    static int yokoflag = 0;
    static int tateflag = 0;
    static int addflag =0;
    static int x3d_add = 0;
    static int[] c3 = new int[100];
    static int[] c2 = new int[100];
    
    {
    for(int i = 0; i < 100; i++){
    	c3[i] = 0;
    	c2[i] = 0;
    	}
    }
   
    static int lcount;
    
    static double xi = 0.0, yi = 0.50, zi = 0.0;
    static double[] xil = new double[100];
    static double dx = 0, dy = 0, dz = 0;
    
    /*entrance*/
    static double l = 0, r = 0;

    public X3DEnv() {
    }

    public void getHeader() {
        header.append("#X3D V3.0 utf8\n");
        header.append("PROFILE Interchange\n");
        Log.out("#X3D V3.0 utf8\n");
        Log.out("PROFILE Interchange\n");

        commonCSS();
        header.append(css);
        Log.out(css.toString());
    }


    private void commonCSS() {
        header.append("Background {\n");
        header.append("skyColor [\n");
        header.append("0.0 0.0 0.1,\n");
        header.append("0.1 0.1 0.2,\n");
        header.append("0.1 0.1 0.3,\n");
        header.append("0.1 0.1 0.4,\n");
        header.append("0.2 0.2 0.5,\n");
        header.append("0.2 0.2 0.6,\n");
        header.append("0.2 0.2 0.7\n");
        header.append("]\n");
        header.append("skyAngle[0.3,0.6,0.9,1.2,1.5,1.57]\n");
        header.append("}\n");
        header.append("DEF view Viewpoint{\n");
        header.append("position 0 3 35\n");
        header.append("orientation 1 0 3 0\n");
        header.append("description \"entrance\"\n");
        header.append("}\n");
        header.append("DEF view Viewpoint{\n");
        header.append("position 0 5 70\n");
        header.append("orientation 1 0 3 0\n");
        header.append("description \"outside\"\n");
        header.append("}\n");
        header.append("NavigationInfo{\n");
        header.append("avatarSize[0.5 20.0 10.0]\n");
        header.append("headlight TRUE\n");
        header.append("speed 3.0\n");
        header.append("type[\"FLY\", \"ANY\"]\n");
        header.append("}\n");
        header.append("DirectionalLight {direction 0 -1 0\n");
        header.append("intensity 0.5}\n");
        header.append("DirectionalLight {direction 0 1 0\n");
        header.append("intensity 0.5}n");
        header.append("DirectionalLight {direction -1 0 0\n");
        header.append("intensity 0.5}\n");
        header.append("DirectionalLight {direction 1 0 0\n");
        header.append("intensity 0.5}\n");
        header.append("DirectionalLight {direction 0 0 1\n");
        header.append("intensity 0.5}\n");
        header.append("DirectionalLight {direction 0 0 -1\n");
        header.append("intensity 0.1}\n");
        header.append("EXTERNPROTO Entrance\n");
        header.append("[field SFVec3f position\n");
        header.append("field SFVec3f frontwall\n");
        header.append("field SFVec3f floor\n");
        header.append("field SFVec3f roof\n");
        header.append("field SFVec3f rightp\n");
        header.append("field SFVec3f leftp]\n");
        header.append("[\"entrance.x3dv\"]\n");
        header.append("EXTERNPROTO Sign\n");
        header.append("[field SFVec3f position\n");
        header.append("field MFString image]\n");
        header.append("[\"sign.x3dv\"]\n");
 
    }

    public void getFooter() {
    	for (int i = 0 ; i < lcount ; i++) {
    		if(c3[i+2] == 1) {
    			footer.append(media + "{\n");
    			footer.append("position 0 0 0 \n");
    			footer.append("floor " + (wid[i]-1.0) + " 0.5 " + (len[i]-1.0) + " \n");
    			footer.append("roof " + (wid[i]-1.0) + " 0.5 " + (len[i]-1.0) + " \n");
    			footer.append("rightwall 0.5 " + hei[i] + " " + (len[i]-0.5) + " \n");
    			footer.append("leftwall 0.5 " + hei[i] + " " + (len[i]-0.5) + " \n");
    			footer.append("frontwall " + (wid[i]-13.0) + " " + hei[i] + " 0.5 \n");
    			footer.append("backwall " + 0 + " " + 0 + " 0 \n");
    			footer.append("floorp " + lx[i] + " " + ly[i] + " " + lz[i] + " \n");
    			footer.append("roofp " + lx[i] + " " + (ly[i] + 14.5) + " " + lz[i] + " \n");
    			footer.append("rightp " + rwxl[i] + " " + rwyl[i] + " " + rwzl[i] + " \n");
    			footer.append("leftp " + lwxl[i] + " " + lwyl[i] + " " + lwzl[i] + " \n");
    			footer.append("frontp " + xil[i] + " " + fwyl[i] + " " + fwzl[i] + " \n");
    			footer.append("backp " + bwxl[i] + " " + bwyl[i] + " " + bwzl[i] + " \n");
    			footer.append("frontp2 " + fwxl[i] + " " + fwyl[i] + " " + fwzl[i] + " \n");
    			footer.append("}\n");
    		}
    		else {
    			footer.append(media + "{\n");
    			footer.append("position 0 0 0 \n");
    			footer.append("floor " + (wid[i]-1.0) + " 0.5 " + (len[i]-1.0) + " \n");
    			footer.append("roof " + (wid[i]-1.0) + " 0.5 " + (len[i]-1.0) + " \n");
    			footer.append("rightwall 0.5 " + hei[i] + " " + (len[i]-0.5) + " \n");
    			footer.append("leftwall 0.5 " + hei[i] + " " + (len[i]-0.5) + " \n");
    			footer.append("frontwall " + (wid[i]-13.0) + " " + hei[i] + " 0.5 \n");
    			footer.append("backwall " + wid[i] + " " + hei[i] + " 0.5 \n");
    			footer.append("floorp " + lx[i] + " " + ly[i] + " " + lz[i] + " \n");
    			footer.append("roofp " + lx[i] + " " + (ly[i] + 14.5) + " " + lz[i] + " \n");
    			footer.append("rightp " + rwxl[i] + " " + rwyl[i] + " " + rwzl[i] + " \n");
    			footer.append("leftp " + lwxl[i] + " " + lwyl[i] + " " + lwzl[i] + " \n");
    			footer.append("frontp " + xil[i] + " " + fwyl[i] + " " + fwzl[i] + " \n");
    			footer.append("backp " + bwxl[i] + " " + bwyl[i] + " " + bwzl[i] + " \n");
    			footer.append("frontp2 " + fwxl[i] + " " + fwyl[i] + " " + fwzl[i] + " \n");
    			footer.append("}\n");
    		}
    		if (c2[i+1] == 1 && i != 0) {
    			footer.append("Entrance{\n");
    	    	footer.append("position " + (l+r)/2.0 + " " + ly[i] + " 20.0\n");
    	    	footer.append("frontwall " + ((r-l) - 1.0) + " 15 0.5\n");
    	    	footer.append("floor " + ((r-l) - 1.0) + " 0.5 40\n");
    	    	footer.append("roof " + ((r-l) - 1.0) + " 0.5 40\n");
    	    	footer.append("rightp " + (r - (l+r)/2.0) + " 7.5 5\n");
    	    	footer.append("leftp " + (l - (l+r)/2.0) + " 7.5 5}\n");
    	    	footer.append("DEF view Viewpoint{\n");
    	    	footer.append("position 0 " + (ly[i] + 3) + " 35\n");
    	    	footer.append("description \"entrance" + (i+1) + "\"}\n");
    		}
    	}
    	footer.append("Entrance{\n");
    	footer.append("position " + (l+r)/2.0 + " 0 20.0\n");
    	footer.append("frontwall " + ((r-l) - 1.0) + " 15 0.5\n");
    	footer.append("floor " + ((r-l) - 1.0) + " 0.5 40\n");
    	footer.append("roof " + ((r-l) - 1.0) + " 0.5 40\n");
    	footer.append("rightp " + (r - (l+r)/2.0) + " 7.5 5\n");
    	footer.append("leftp " + (l - (l+r)/2.0) + " 7.5 5}\n");
    	footer.append("#��t\n");
    	footer.append("Transform{\n");
    	footer.append("translation 5 0 4\n");
    	footer.append("children[\n");
    	footer.append("Transform\n");
    	footer.append("{\n");
    	footer.append("translation 5 2 15\n");
    	footer.append("children Shape\n");
    	footer.append("{\n");
    	footer.append("geometry Box\n");
    	footer.append("{\n");
    	footer.append("size 12 4.0 2\n");
    	footer.append("}\n");
    	footer.append("appearance Appearance\n");
    	footer.append("{\n");
    	footer.append("material Material\n");
    	footer.append("{\n");
    	footer.append("diffuseColor 0.7 0.5 0\n");
    	footer.append("}}}}");
    	footer.append("Transform\n");
    	footer.append("{translation 0 2 11.0\n");
    	footer.append("children Shape\n");
    	footer.append("{geometry Box\n");
    	footer.append("{size 2 4 6}\n");
    	footer.append("appearance Appearance{\n");
    	footer.append("material Material {diffuseColor 0.7 0.5 0}}}}]}\n");
    }

    public void append_css_def_td(String classid, DecorateList decos) {
    	
        StringBuffer cssbuf = new StringBuffer();

        	shelf=decos.getStr("shelf");
            css.append("EXTERNPROTO " + decos.getStr("shelf") + "\n");
            css.append("[field SFVec3f position]\n");
            css.append("[\""+ decos.getStr("shelf")+".x3dv\"]\n");
            
            object=decos.getStr("object");
            css.append("EXTERNPROTO " + decos.getStr("object") + " \n");
            css.append("[field SFVec3f position\n");
            css.append("field MFString image\n");
            css.append(" field MFString data]\n");
            css.append("[\""+decos.getStr("object")+".x3dv\"]\n");
            
            media=decos.getStr("media");
            css.append("EXTERNPROTO " + decos.getStr("media") + " \n");
            css.append("[field SFVec3f position\n"); 
            css.append(" field SFVec3f floor\n");
            css.append(" field SFVec3f roof\n");
            css.append(" field SFVec3f rightwall\n");
            css.append(" field SFVec3f leftwall\n");
            css.append(" field SFVec3f frontwall\n");
            css.append(" field SFVec3f backwall\n");
            css.append(" field SFVec3f floorp\n");
            css.append(" field SFVec3f roofp\n");
            css.append(" field SFVec3f rightp\n");
            css.append(" field SFVec3f leftp\n");
            css.append(" field SFVec3f frontp\n");
            css.append(" field SFVec3f backp\n");
       	 	css.append(" field SFVec3f frontp2]\n");
            css.append("[\""+decos.getStr("media")+".x3dv\"]\n");
            css.append("PROTO Book\n");
            css.append("[field SFVec3f position 0 0 0\n");
            css.append("field MFString image \"\"\n");
            css.append("field MFString data \"\"\n");
            css.append("]\n");
            css.append("{\n");
            css.append("Transform {\n");
            css.append("children Anchor {\n");
            css.append("url IS data\n");
            css.append("children [\n");
            css.append("Transform\n");
            css.append("{\n");
            css.append("translation -1.2 0.8 0\n");
            css.append("rotation 1.0 0.0 0.0 -0.4\n");
            css.append("children Shape\n");
            css.append("{\n");
            css.append("geometry Box\n");
            css.append("{\n");
            css.append("size 0.8 1.5 0.1\n");
            css.append("}\n");
            css.append("appearance Appearance\n");
            css.append("{\n");
            css.append("material Material\n");
            css.append("{\n");
            css.append("diffuseColor 1 1 1\n");
            css.append("}\n");
            css.append("texture ImageTexture\n");
            css.append("{ url IS image }\n");
            css.append("}}}]}\n");
            css.append("translation IS position\n");
            css.append("}}\n");
            css.append("PROTO Shelf2\n");
            css.append("[field SFVec3f position 0 0 0]\n");
            css.append("{\n");
            css.append("Transform {\n");
            css.append("translation IS position\n");
            css.append("children [\n");
            css.append(decos.getStr("shelf") + "{position 0 0 0}\n");
            css.append(decos.getStr("shelf") + "{position 0 1.5 0}\n");
            css.append(decos.getStr("shelf") + "{position 0 3.0 0}\n");
            css.append(decos.getStr("shelf") + "{position 0 4.5 0}\n");
            css.append(decos.getStr("shelf") + "{position 0 6.0 0}\n");
            css.append("]\n");
            css.append("}\n");
            css.append("}\n");
    }
    
    
    
    public static String getClassID(ITFE tfe) {
        if (tfe instanceof X3DC3) {
            return getClassID(((ITFE) ((X3DC3) tfe).tfes.get(0)));
        }
        if (tfe instanceof X3DG3) {
            return getClassID(((ITFE) ((X3DG3) tfe).tfe));
        }
        return "TFE" + tfe.getId();
    }

}
