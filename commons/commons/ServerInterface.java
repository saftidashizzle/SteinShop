package commons;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface ServerInterface {
	
	public SessionInterface login(ClientInterface client);

	public void safe() throws FileNotFoundException, IOException;
	
}
