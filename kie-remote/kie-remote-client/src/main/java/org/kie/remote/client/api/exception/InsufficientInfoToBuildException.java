package org.kie.remote.client.api.exception;

import org.kie.remote.client.api.RemoteRuntimeEngineBuilder;

/**
 * This exception is thrown by the *RuntimeFactoryBuilder classes
 * when insufficient information is called during 
 * {@link RemoteRuntimeEngineBuilder#buildFactory()} and similar methods.
 * </p>
 * It indicates that the builder can not create an instance because
 * there is insufficient information. 
 */
public class InsufficientInfoToBuildException extends org.kie.services.client.api.builder.exception.InsufficientInfoToBuildException {

    /** generated serial version UID */
    private static final long serialVersionUID = 7415935205523780077L;

    public InsufficientInfoToBuildException() {
        super();
    }
    
    public InsufficientInfoToBuildException(String msg) { 
        super(msg);
    }
}
