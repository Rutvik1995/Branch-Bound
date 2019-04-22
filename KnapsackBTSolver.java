
import java.util.*;

// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{
        public void FindSolns(int itemNum)
	{
        			int itemCnt = inst.GetItemCnt();
                int cap = inst.GetCapacity();

               // System.out.println("Item count"+itemCnt);
                //System.out.println("Item Capacity "+capacity);
                //System.out.println("");
                //System.out.println("Curent solution weight  "+crntSoln.wght);
                //System.out.println("");
                //System.out.println("Current item is : "+itemNum);
                
                if(crntSoln.Tweight > cap)
                {
                	//	System.out.println("In retuen loop1");
                		return;
                }
                if(itemNum == itemCnt + 1)
                {
                        CheckCrntSoln();
                        return;
                }
                
                
            		//System.out.println("");
                //System.out.println("before don't take");
        			//System.out.println("Not Takking item "+itemNum);
                
        			crntSoln.DontTakeItem(itemNum);
                
        			//System.out.println("After Don't take");
        			//System.out.println("");
        			//System.out.println();
        			//System.out.println("don takeitemno "+itemNum);
        			//System.out.println("before )( find solution "+(itemNum + 1));
                
              
                FindSolns(itemNum+1);
                
                //System.out.println("");
        		//System.out.println("after )( find solution "+(itemNum + 1));
        		
        		
                
        		
        		
                
                crntSoln.UndoDontTakeItem(itemNum);

                
              //  System.out.println("");
        			//System.out.println("Before take Item:  "+itemNum);
                
                crntSoln.TakeItem(itemNum);
                FindSolns(itemNum+1);

                crntSoln.UndoTakeItem(itemNum);
	}
        
        
        
        
	public KnapsackBTSolver()
	{
		crntSoln = null;
	}
	public void close()
	{
		if (crntSoln != null)
		{
			crntSoln = null;
		}
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
                bestSoln = soln_;
                crntSoln = new KnapsackSolution(inst);
                crntSoln.Tweight = 0;
                FindSolns(1);
	}
}