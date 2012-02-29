/**
 * Established a connection to a remote server.
 * Throws if it fails to do so.
 *
 * @param url address that can either be resolved
 *            via hosts.conf or DNS or is an IP 
 *            address.
 * @param port port to connect to on the server. A
 *             positive integer, typically above 1024.
 *             Must be the same as the {@see Server}
 *             uses with its {@see Server#listenOn} method.
 * @throws NetworkConnectionException if it was
 *            not able to initiate a connection.
 */
public ServerProxy(String url, int port) 
	throws NetworkConnectionException {
	// ...
}
