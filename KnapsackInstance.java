
import java.util.*;

public class KnapsackInstance implements java.io.Closeable
{
	private int itemCnt; //Number of items
	private int cap; //The capacity
	public int[] weights; //An array of weights
	public int[] values; //An array of values
    private double[] rt;
    double temp3;
	public KnapsackInstance(int itemCnt_)
	{
		itemCnt = itemCnt_;

		weights = new int[itemCnt + 1];
		values = new int[itemCnt + 1];
        rt = new double[itemCnt+1];
		cap = 0;
	}
	public void close()
	{
		weights = null;
		values = null;
	}

	public void Generate()
	{
		int i;
        int wghtSum;

		weights[0] = 0;
		values[0] = 0;

		wghtSum = 0;
		for(i=1; i<= itemCnt; i++)
		{
			weights[i] = Math.abs(RandomNumbers.nextNumber()%100 + 1) ;
			values[i] = weights[i] + 10;
			wghtSum += weights[i];
		}
		cap = wghtSum/2;
	}

	public int GetItemCnt()
	{
		return itemCnt;
	}
	public int GetItemWeight(int itemNum)
	{
		return weights[itemNum];
	}
	public int GetItemValue(int itemNum)
	{
		return values[itemNum];
	}
	public int GetCapacity()
	{
		return cap;
	}
	public void Print()
	{
		int i;

		System.out.printf("Number of items = %d, Capacity = %d\n",itemCnt, cap);
		System.out.print("Weights: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",weights[i]);
		}
		System.out.print("\nValues: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",values[i]);
		}
		System.out.print("\n");
	}



    void sortinf_method() 
        {
            
        int v1, v2;
        
        int con = GetItemCnt();	
            
        for(int i=1; i<=con; i++){
        rt[i] = ((double)values[i] / (double)weights[i]);
	   }

        int i,j;
        
        for( i=2; i<=GetItemCnt(); i++)
	      {			
	        temp3=rt[i];
	        v2=values[i];
	        v1=weights[i];
	         for(j=i-1;j>=1;)
	         {
	            if(rt[j]<temp3)
	            {
	               rt[j+1]=rt[j]; 
	               values[j+1]=values[j];
	               weights[j+1]=weights[j];
	               j--;
	            }
	            else
	            {
	               break;
	            }
	         }
	         rt[j+1]=temp3;
	         values[j+1]=v2;
	         weights[j+1]=v1;
	      }   
        } 

}
