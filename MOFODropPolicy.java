package buffermanagement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import core.Message;
import core.Settings;
import routing.ActiveRouter;

/**
  *Drop the messaqe with the maximum number of transmissions first.i.e., the most forwarded messaqes
*/
public class MOFODropPolicy extends DropPolicy{
	/**
	  *Constructor for this class
	*/
	public MOFODropPolicy(Settings s){
		super(s);
		//this doesn't need specific settings
	}
	
	//override makeRoomForMessage()
	@Override
	public boolean makeRoomForMessage(ActiveRouter router,Message incomingMessage){
		int size = incomingMessage.getSize();
		
		if(size>router.getBufferSize()){
			return false;//messaqe too big for the buffer
		}
		
		long freeBuffer = router.getFreeBufferSize();
		
		//check if there is enough space to receive the incomingMessage before sorting the buffer
		if(freeBuffer>=size){
			return true;//There is enough space 
		}
		
		//Sorting the messaqe by forward count 
		ArrayList<Message> messages = new ArrayList<Message>(router.getMessageCollection());
		Collections.sort(messages, new MOFOComparator());
		
		/*Delete messaqes from the buffer until there is enough space*/
		while(freeBuffer<size){
			if(messages.size()==0){
				return false;//couldn't remove more messages 
			}
			//Get the messages that was most forwarded 
			Message msg = messages.remove(messages.size()-1);
			
			//check if the router is sending the message
			
			if(this.dropMsgBeingSent|| !router.isSending(msg.getId())){
				//Delete the message and send signal "drop"
				router.deleteMessage(msg.getId(), true);
				freeBuffer += msg.getSize();
			}
		}
		
		return true;
		
	}//ends makeRoomForMessage()
	
	
	/**
	  * The MOFOComparator Comparator
	*/
	private class MOFOComparator implements Comparator<Message>{
		@Override 
		public int compare(Message msg0, Message msg1){
			return ((Integer)msg0.getForwardCount()).compareTo(msg1.getForwardCount());
		}
	}
	
	
}













