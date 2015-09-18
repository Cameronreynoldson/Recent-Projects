import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.TreeMap;


public class MemSearch
{
	
	
	public static void main(String[] args)
	{
		System.out.print("Enter the amount of files you want to scan for excessive memory: ");
		Comparator memComp = new MemoryComparator();
		Scanner scanner = new Scanner(System.in);
		int amount = scanner.nextInt();
		System.out.println();
		System.out.print("Please enter the name of your computer account: ");
		String name = scanner.next();
		String start = "/Users/"+name+"/Documents";
		TreeMap<Long,String> tm = new TreeMap<Long,String>();
		findMemoryUsage(tm,amount,start);
		for(int i = 0; i < amount; ++i)
		{
			String memory = humanReadableByteCount(tm.lastKey(),true);
			System.out.println();
			System.out.println(tm.get(tm.lastKey()) +" "+ memory);
			tm.remove(tm.lastKey());
		}
	}
	
	public static void findMemoryUsage(TreeMap<Long,String> tm, int amount,String start)
	{
		File dir = new File(start);
		File[] list = dir.listFiles();
		for(File child: list){
			if(child.isDirectory())
			{
				findMemoryUsage(tm,amount,child.toString());
			}
			
			
			else
			{
				tm.put(child.length(),child.toString()); 
			}
		}	
	}
	
	
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	
}

class MemoryComparator implements Comparator<Long>
{
	@Override
	public int compare(Long x, Long y) {
		if(x.compareTo(y) > 0)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
}

