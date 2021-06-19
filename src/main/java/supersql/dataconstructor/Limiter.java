package supersql.dataconstructor;

import java.util.ArrayList;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class Limiter{
  private int att;
  private int value;
  private int maxDepth = 0;
  private int limitDepth;
  private int grouperNum;
  private boolean limitFlag = false;

  public Limiter(){

  }

  public Limiter(int a, int v){
    att = a - 1;
    value = v;

    Log.out("Create Limiter Object.");
    Log.out("Attribute Number is " + att);
    Log.out("Limit value is " + value);
  }

  public void initMaxDepth(){
    maxDepth = 0;
  }

  public int getLimitDepth(){
    return limitDepth;
  }

  public boolean getLimitFrag(){
    if (limitFlag == false){
      return limitFlag;
    }
    GlobalEnv.limit.get(0).selfDestory();
    return limitFlag;
  }

  public void findGrouper(String tfe_tree){
    String grouper = "grouper";
    grouperNum = (tfe_tree.length() - tfe_tree.replaceAll(grouper, "").length()) / grouper.length();
    // Log.out("Grouper which @{limit} has " + grouperNum);
  }

  @SuppressWarnings("rawtypes")
  public void haveLimitAttribute(ExtList sch){
    maxDepth++;
    Object obj;
    for (int idx = 0; idx < sch.size(); idx++) {
      obj = sch.get(idx);

      if (obj instanceof ExtList) {
        haveLimitAttribute((ExtList) obj);
      }
      else if(((Integer) obj) == att){
        // Log.out("This sch has limit attribute! Attribute is " + att);
        // Log.out("The maxDepth of this sch is " + maxDepth);
        // Log.out("The grouperNum is " + grouperNum);

        limitDepth = maxDepth - grouperNum;
        // Log.out("The limitDepth is " + limitDepth);
        GlobalEnv.realLimit.addElement(value, limitDepth);
        limitFlag = true;
      }
      else {
      }
    }
  }

  public void selfDestory(){
    // Log.out("Destory Limiter Object.");
    GlobalEnv.limit.remove(0);
    GlobalEnv.limit.remove(0);
  }

  public class RealLimiter {
    private ArrayList<Integer> realValue = new ArrayList<Integer>();
    private ArrayList<Integer> realLimitDepth = new ArrayList<Integer>();
    private boolean limitFlag = false;

    public RealLimiter() {

    }

    public boolean getLimitFrag(){
      return limitFlag;
    }

    public void addElement(int value, int depth){
      limitFlag = true;
      realValue.add(value);
      realLimitDepth.add(depth);
    }

    public void logStatus(){
      if(limitFlag == true){
        for (int i = 0; i < realValue.size(); i++){
          Log.out("realValue is " + realValue.get(i));
        }
        for (int i = 0; i < realLimitDepth.size(); i++){
          Log.out("realLimitDepth is " + realLimitDepth.get(i));
        }
      } else {
        Log.out("This sch dose not have the attribute which must be limited.");
      }
    }

    public ArrayList<Integer> getRealDepth(){
      return realLimitDepth;
    }

    private int depth = -1;
    @SuppressWarnings("rawtypes")
    public void limitTuple(ExtList tuple){
      depth++;
      // Log.out("The depth of here is " + depth);
      // Log.out("tuple : "+tuple);
      // Log.out("tuple.size() (depth "+(depth+1)+") : "+tuple.size());

      // optimized version
      if(depth % 2 == 0 &&
      realLimitDepth.contains((depth / 2) + 1) &&
      tuple.size() > realValue.get(realLimitDepth.indexOf((depth / 2) + 1))){
        tuple.extRemoveRange(realValue.get(realLimitDepth.indexOf((depth / 2) + 1)), tuple.size());
      }

      // // not optimized version
      // for (int i = 0; i < realLimitDepth.size(); i++){
      //   if(depth % 2 == 0 &&
      //   realLimitDepth.get(i) == (depth / 2) + 1 &&
      //   tuple.size() > realValue.get(i)){
      //     for (int j = tuple.size() - 1; j > realValue.get(i) - 1; j--) {
      //       tuple.remove(j);
      //     }
      //   }
      // }

      Object obj;
      for (int idx = 0; idx < tuple.size(); idx++) {
        obj = tuple.get(idx);

        if (obj instanceof ExtList) {
          limitTuple((ExtList) obj);
        }
        else {
        }
      }
      depth--;
    }
  }
}
