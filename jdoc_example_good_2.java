
/**
 * Ends the connection. After this call, no other
 * method call is valid, including this one. The
 * server is not affected by this.
 */
public void disconnect() throws DeadConnectionException {
	// ...
}

/**
 * Returns the number of jobs running on the server.
 *
 * @return a non-negative integer that is the
 *         number of jobs that are alive.
 */
public int getJobCount() throws DeadConnectionException {
	// ...
}
