package io.netty.util;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class NoteLogger {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance("note");
    public static boolean doLogging = false;

    static public void logNote(Object... notes) {
        if (!doLogging)
            return;
        StringBuilder sb = new StringBuilder();
        for (Object n : notes) {
            sb.append(null == n ? "null" : n.toString()).append(" ");
        }
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String callerClass = ste.getClassName();
        String callerMethod = ste.getMethodName();
        String[] tmp = callerClass.split("\\.");
        logger.info("[" + tmp[tmp.length - 1] + " " + callerMethod + "]" + " " + sb.toString().trim());
    }
}