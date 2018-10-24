package com.gary.demo.crud.api.exception;

import com.google.common.base.MoreObjects;

public class DemoCrudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final Object objectThatCausedException;

    public DemoCrudException(String msg, Object objectThatCausedException) {
        super(msg);
        this.objectThatCausedException = objectThatCausedException;
    }

    public DemoCrudException(String msg, Object objectThatCausedException, Throwable causedBy) {
        super(msg, causedBy);
        this.objectThatCausedException = objectThatCausedException;
    }

    public DemoCrudException(String msg, Throwable causedBy) {
        super(msg, causedBy);
        this.objectThatCausedException = null;
    }

    public DemoCrudException(String msg) {
        super(msg);
        this.objectThatCausedException = null;
    }


    public Object getObjectThatCausedException() {
        return objectThatCausedException;
    }

    protected MoreObjects.ToStringHelper toStringHelper() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("objectThatCausedException", objectThatCausedException);
    }

    @Override
    public String toString() {
        return toStringHelper().toString();
    }
}
