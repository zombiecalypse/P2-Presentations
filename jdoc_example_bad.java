public class ServerProxy implements IServer{
	private String url;
	/**
	 * Constructor.
	 */
	public ServerProxy(String url, int port) throws NetworkConnectionException {
		// ...
	}

	/**
	 * Ends the connection.
	 */
	public void disconnect() throws DeadConnectionException {
		// ...
	}

	/**
	 * Returns the number of jobs.
	 */
	public int getJobCount() throws DeadConnectionException {
		// ...
	}

	/**
	 * Returns the url of the server.
	 */
	public String getUrl() {
		return url;
	}

	// ...
}
