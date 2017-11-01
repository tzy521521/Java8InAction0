package chap3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by tzy on 2017/7/20.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;
}
