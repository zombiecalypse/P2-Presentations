/**
 * Relays method calls to a remote {@see Server}. <p>
 * The proxy is responsible for establishing and keeping a connection to the
 * server. The caller must ensure that a connection is destroyed with the
 * {@see #disconnect} method.
 */
public class ServerProxy implements IServer {
	private String url;
	/**
	 * Established a connection to a remote server. Throws if it fails to do
	 * so.
	 *
	 * @param url address that can either be resolved via hosts.conf or DNS or
	 *            is an IP address.
	 * @param port port to connect to on the server. A positive integer,
	 *             typically above 1024. Must be the same as the {@see Server}
	 *             uses with its {@see Server#listenOn} method.
	 * @throws NetworkConnectionException if it was not able to initiate a
	 *                                    connection.
	 */
	public ServerProxy(String url, int port) throws NetworkConnectionException {
		// ...
	}

	/**
	 * Ends the connection. After this call, no other method call is valid,
	 * including this one. The server is not affected by this.
	 */
	public void disconnect() throws DeadConnectionException {
		// ...
	}

	/**
	 * Returns the number of jobs running on the server.
	 *
	 * @return a non-negative integer that is the number of jobs that are
	 *         alive.
	 */
	public int getJobCount() throws DeadConnectionException {
		// ...
	}

	public String getUrl() {
		return url;
	}

	// ...
}
