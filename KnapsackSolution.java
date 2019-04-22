import java.util.*;

public class KnapsackSolution implements java.io.Closeable
{
	private boolean [] isTaken;
	private int value;
	int Tweight;
	int takenValue=0;
    int untaken_value=0;
    int total_value;
    int remaining_Cap;
     int fract;
     int undecided_Value;
	KnapsackInstance inst;
    
    

	public KnapsackSolution(KnapsackInstance inst_)
	{
		int i;
		int itemCnt = inst_.GetItemCnt();
    
		inst = inst_;
		isTaken = new boolean[itemCnt + 1];
		value = DefineConstants.INVALID_VALUE;
    
		for (i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = false;
		}
	}
	public void close()
	{
		isTaken = null;
	}

	public void TakeItem(int itemNum)
	{
		
				isTaken[itemNum] = true;
				Tweight += inst.GetItemWeight(itemNum);
                takenValue  += inst.GetItemValue(itemNum);
                remaining_Cap -= inst.GetItemWeight(itemNum);
	}
	public void DontTakeItem(int itemNum)
	{
		//System.out.println("in don take item");
				isTaken[itemNum] = false;
				
				
				untaken_value += inst.GetItemValue(itemNum);
	}
        
        public void UndoTakeItem(int itemNum)
        {
              isTaken[itemNum] = false;
              Tweight -= inst.GetItemWeight(itemNum);
              takenValue  -= inst.GetItemValue(itemNum);
              remaining_Cap += inst.GetItemWeight(itemNum);
        }
        public void UndoDontTakeItem(int itemNum) 
        {
            isTaken[itemNum] = false;	
            untaken_value -=inst.GetItemValue(itemNum);
            
        }
   
        
        
	public int ComputeValue()
	{
		int i;
		int itemCnt = inst.GetItemCnt();
		int weight = 0;
    
		value = 0;
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				weight += inst.GetItemWeight(i);
				if (weight > inst.GetCapacity())
				{
					value = DefineConstants.INVALID_VALUE;
					break;
				}
				value += inst.GetItemValue(i);
			}
		}
		return value;
	}
	public int GetValue()
	{
		return value;
	}
	public void Print(String title)
	{
		int i;
		int itemCnt = inst.GetItemCnt();
    
		System.out.printf("\n%s: ",title);
		for (i = 1; i <= itemCnt; i++)
		{
			if (isTaken[i] == true)
			{
				System.out.printf("%d ",i);
			}
		}
		System.out.printf("\nValue = %d\n",value);
    
	}
	public void Copy(KnapsackSolution otherSoln)
	{
		int i;
		int itemCnt = inst.GetItemCnt();
    
		for (i = 1; i <= itemCnt; i++)
		{
			isTaken[i] = otherSoln.isTaken[i];
		}
		value = otherSoln.value;
	}
	public boolean equalsTo (KnapsackSolution otherSoln)
	{
		return value == otherSoln.value;
	}
	
	public void dispose()
	{
		isTaken = null;
	}	 
    }

   

    
