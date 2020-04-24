package concurrent.snowflake;

/**
 * @author yogurtzzz
 * @date 2020/4/17 16:45
 *
 * 简易版snowflake算法
 * 1毫秒内最多支持4096个id
 **/
public class SnowFlake {

    private static long sequenceBits = 12L;

    private static final long sequenceMask = ~(-1L << sequenceBits);  //aka 4095

    private static long lastTimestamp = -1L;

    private static long sequence = 0L;


    public static String nextCid(){
        return nextId("Y");
    }

    private synchronized static String nextId(String idType){
        long timestamp = timeGen();
        if (timestamp < lastTimestamp){
            throw new RuntimeException("Clock move backwards. Refuse to generate id");
        }
        if (timestamp == lastTimestamp){
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0){
                timestamp = tillNextSecond(lastTimestamp);
            }
        }else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return String.format(idType + "-%d-%04d",lastTimestamp,sequence);
    }

    private static long timeGen(){
        return System.currentTimeMillis();
    }

    private static long tillNextSecond(long lastTimestamp){
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }
}
