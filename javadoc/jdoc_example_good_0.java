/**
 * Relays method calls to a remote {@see Server}.
 * <p>
 * The proxy is responsible for establishing and
 * keeping a connection to the server. The caller
 * must ensure that a connection is destroyed with
 * the {@see #disconnect} method.
 */
public class ServerProxy implements IServer {
