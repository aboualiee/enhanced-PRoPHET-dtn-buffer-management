package buffermanagement;
/**import the necessary class
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.Message;
import core.Settings;
import routing.ActiveRouter;

public class DLDropPolicy extends DropPolicy{
	/**
	  *Constructor for this class
	*/
	public DLDropPolicy(Settings s){
		super(s);
		//this doesn't need specific settings
	}
	
	//override makeRoomForMessage
	@Override
	public boolean makeRoomForMessage(ActiveRouter router,Message incomingMessage){
		int size = incomingMessage.getSize();
		
		if(size>router.getBufferSize()){
			return false;//Message is too big for the buffer
		}
		
		/**
		  *Get the free buffer 
		*/
		long freeBuffer = router.getFreeBufferSize();
		
		//check if there is enough space to receive the incomingMessage
		if(freeBuffer>=size){
			return true; //there is enough space
		}
		
		//Sorting the messaqe by last message sent 
		ArrayList<Message> messages = new ArrayList<Message>(router.getMessageCollection());
		Collections.sort(messages, new DLComparator());
		
		
		/*Delete messaqes from the buffer until there is enough space*/
		while(freeBuffer<size){
			if(messages.size()==0){
				return false;//couldn't remove more messages 
			}
			
			//Get the last messages that was sent
			Message msg = messages.remove(1);
			//check if the router is sending the message
			
			 //Check if the router is sending this message
		 if(this.dropMsgBeingSent || !router.isSending(msg.getId())){
			 //Delete the message and send signal "drop"
			 router.deleteMessage(msg.getId(),true);
			 freeBuffer += msg.getSize();
		 }
		 
			
		}
		
		return true;
		
	}
	
	public class DLComparator implements Comparator<Message>{
		@Override 
		public int compare(Message m1,Message m2){
			return ((Double) m1.getReceiveTime()).compareTo(m2.getReceiveTime());
		}
	}
		
}