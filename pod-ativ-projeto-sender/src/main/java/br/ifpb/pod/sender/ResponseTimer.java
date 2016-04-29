package br.ifpb.pod.sender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ResponseTimer extends Thread {
	
	private Date start;
	private SenderImpl sender;
	private Date response;
	

	private int MAX_RESP_TIME = 3; //minutes
	
	public ResponseTimer(Date start, SenderImpl sender){
		this.start = start;
		this.sender = sender;
	}
	
	public void end(Date response){
		this.response = response;
	}
	
	public void run(){
		while(response == null){
			try {
				//se ainda nao teve resposta, então verificar quantos minutos se passaram
				
				Date now = new Date();
					
				if( getDateDiff( now, start, TimeUnit.MINUTES ) >= MAX_RESP_TIME  ){
					//passou do tempo maximo se resposta.

				}
				
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	

	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
