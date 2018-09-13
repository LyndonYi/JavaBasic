package mutilThread;

/**
 * 生产者---消费者
 */
public class Day06_producer_Communication_Consumer {
public static void main(String[] args) 
	{
	Resource r=new Resource();
	Producer pro =new Producer(r);
	
	Consumer con=new Consumer(r);
	Thread t1=new Thread(pro);
	Thread t2=new Thread(con);
	Thread t3=new Thread(pro);
	Thread t4=new Thread(con);
	t1.start();
	t2.start();
	t3.start();
	t4.start();
}

}
class Resource{
	private String name;
	private int count=1;
	private boolean flag=false;
	public synchronized void set(String name){
			while(flag)//  if(flag)只用于一个生产一个消费
			try{wait();}catch(Exception e){}
				
		this.name=name+"--"+count++;
	System.out.println(Thread.currentThread().getName()+"...生产者.."+this.name);
		      flag=true;
		      this.notifyAll();	//this.notify()唤醒线程池中的第一个等待的线程，只是唤醒一个	
		
	}
	public synchronized void out(){
		  while(!flag)//if(flag)只用于一个生产一个消费
		  try{wait();}catch(Exception e){}
		 
	System.out.println(Thread.currentThread().getName()+"          消费者......."+this.name);
		  flag=false;
			this.notifyAll();//this.notify();唤醒线程池中的第一个等待的线程，只是唤醒一个	
}
}
class Producer implements Runnable{
	private Resource res;
	Producer(Resource res){
		this.res=res;
	}
	public void run()
	{
	   while(true)
	    {
		res.set("+商品+");
		
	    }
    }
}
class Consumer implements Runnable{
	private Resource res;
	Consumer(Resource res){
		this.res=res;
	}
	public void run()
	{
	   while(true)
	    {
		res.out();
		
	    }
    }
}
