package commons;


import java.io.Serializable;

import valueobjects.User;

import de.root1.simon.SimonUnreferenced;

public interface ClientInterface extends Serializable, SimonUnreferenced {

	public void receiveMessage(String message);

	public User getUser();

	public void userLogout();

	
}
