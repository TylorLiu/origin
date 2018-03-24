package org.tylor.origin.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liubin10  2018/3/5
 */
public class NamedThreadFactory implements ThreadFactory {

    private AtomicInteger num = new AtomicInteger(1);

    private String prefix;

    public NamedThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,prefix+num.getAndIncrement());
    }
}
