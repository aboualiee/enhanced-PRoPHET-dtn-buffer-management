
package buffermanagement;

import core.Message;
import core.Settings;
import routing.ActiveRouter;

/**
 *This abstract class define the basic structure for implementing a message drop policy. 
 *These policies can be used by different hosts to drop messages when their buffer size is 
 *not enough to receive incoming messages
*/
public abstract class DropPolicy{
	/**
	 *Drop message being sent -setting id({@value}). The configuration that defines if message being sent can be dropped.
	*/
	private static final String DROP_MSG_BEING_SENT = "dropMsgBeingSent";
	
	/**
	 *Define if messages being sent can be dropped
	*/
	protected boolean dropMsgBeingSent= true;
	
	/**
	 *Constructor with the signature required to be instantiated by the simulator @param s
	*/
	public DropPolicy(Settings s){
		if(s.contains(DROP_MSG_BEING_SENT)){
			this.dropMsgBeingSent = s.getBoolean(DROP_MSG_BEING_SENT);
		}
	}
	/**
	 *Try to remove message from the buffer until enough space for receiving the incoming message is freed
	 *@param router A reference to the receiver router
	 @param incomingMessage The incoming message.
	 @return True if it was possible to freed enough space. false otherwise.
	*/
	public abstract boolean makeRoomForMessage(ActiveRouter router, Message incomingMessage);
	
}









