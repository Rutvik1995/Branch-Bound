
// Branch-and-Bound solver
public class KnapsackBBSolver extends KnapsackBFSolver
{
        protected UPPER_BOUND ub;
      

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
                	
                			return;
                }
             
                else if(ub == UPPER_BOUND.UB1)
                {

                			if((crntSoln.total_value - crntSoln.untaken_value) <= bestSoln.GetValue())
                				return;
                }

                else if(ub == UPPER_BOUND.UB2)
                {
                
                	
                	crntSoln.undecided_Value=0;
                	for(int j = itemNum; j <= itemCnt ; j++)
                	{
                		//System.out.println("in llop");
                		if(inst.GetItemWeight(j) <= crntSoln.remaining_Cap)
                		{
            
                			crntSoln.undecided_Value += inst.GetItemValue(j);
                		}
                	}
                	
                	if((crntSoln.takenValue + crntSoln.undecided_Value) <= bestSoln.GetValue())
                		return;
                }

                else if(ub == UPPER_BOUND.UB3)
                {
               
                		crntSoln.fract = crntSoln.remaining_Cap;
                	
                		crntSoln.undecided_Value=0; 
                	
                	for(int i=itemNum; i <= itemCnt; i++) 
                	{
                		
                		if(inst.GetItemWeight(i) <= (crntSoln.fract))
        				{
        					
                			crntSoln.undecided_Value += inst.GetItemValue(i);
        					crntSoln.fract -= inst.GetItemWeight(i);
        				}
        				else
        				{
        					
        					crntSoln.undecided_Value +=
        							((float)inst.GetItemValue(i) * ((crntSoln.fract) / (float) inst.GetItemWeight(i)));
        					crntSoln.fract=0;
        					break;
        				}
                	}
                	
                	
                	if( (crntSoln.takenValue +  crntSoln.undecided_Value) <= bestSoln.GetValue())
                		return;

                }
                

                

                if(itemNum == itemCnt + 1)
                {
                	//System.out.println("same item");
                		CheckCrntSoln();
                	
                	
                		return;
                }

                
              
                crntSoln.DontTakeItem(itemNum);
                FindSolns(itemNum+1);

                crntSoln.UndoDontTakeItem(itemNum);

                crntSoln.TakeItem(itemNum);
                FindSolns(itemNum+1);

                crntSoln.UndoTakeItem(itemNum);

                
	}
        
        

	public KnapsackBBSolver(UPPER_BOUND ub_)
	{
		ub = ub_;

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
                crntSoln.total_value = 0;
                crntSoln.takenValue=0;
                crntSoln.untaken_value=0;
                crntSoln.remaining_Cap = inst.GetCapacity();
                crntSoln.undecided_Value=0;
                
                if(ub == UPPER_BOUND.UB3)
                {
                	inst.sortinf_method();
                }
               
                int itemCnt = inst.GetItemCnt();
                
                for(int i=1; i <= itemCnt; i++)
                {
                    crntSoln.total_value  += inst.GetItemValue(i);
                }
                FindSolns(1);    
        }
	
	
//	public void totalwgth()
//	{
//		int itemCnt = inst.GetItemCnt();
//        
//        for(int i=1; i <= itemCnt; i++)
//        {
//            crntSoln.total_value  += inst.GetItemValue(i);
//        }
//	}

}
