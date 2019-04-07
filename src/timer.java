
public class timer extends Thread
{
	public void run()
	{
		try
		{
			for(int i=1;i<10;i++)
			{
				System.out.println(i);
				Thread.sleep(1000);
				System.out.println("CLS");
				
			}
		}
		catch(InterruptedException ie)
		{
			System.out.println(ie);
		}
	}
}
