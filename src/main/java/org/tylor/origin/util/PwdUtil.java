package org.tylor.origin.util;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liubin10  2018/3/20
 */
@Slf4j
public class PwdUtil {

    //SHA256摘要
    public static  String encrypt(String source) {
        try {
            MessageDigest
                digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(source.getBytes("UTF-8"));
            return ByteArrayUtil.toHexString(result);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("加密失败", e);
        }
        return source;
    }
}
