import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 这点代码乍一看没什么问题，看明白了就是代码里下砒霜！它的目的就一个，要让所有的key成一个链表放到HashMap中，而且把有用的key放到链表的最后，增加get时的耗时！
 * 首先，new HashMap<>(64);为啥默认初始化64个长度？因为默认长度是8，插入元素时，当链表长度为8时候会进行扩容和链表树化判断，此时就会把原有的key散列了，不能让所有key构成一个时间复杂度较高的链表。
 * 其次，所有的 key 都是刻意选出来的，因为他们在 HashMap 计算下标时，下标值都为0，idx = (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16))，这样就能让所有 key 都散列到同一个位置进行碰撞。而且单词 insincere 的意思是；不诚恳的、不真诚的！
 * 最后，前7个key其实都是废 key，不起任何作用，只有最后一个 key 有服务。那么这样就可以在HashMap中建出来很多这样耗时的碰撞链表，当然要满足0.75的负载因子，不要让HashMap扩容。
 * </p>
 *
 * @since 2020/9/7
 */
public class MapThunder {

    @Test
    public void hashCodeTest(){
        Map<String, String> map = new HashMap<>(64);
        map.put("alderney", "未实现服务");
        map.put("luminance", "未实现服务");
        map.put("chorology", "未实现服务");
        map.put("carline", "未实现服务");
        map.put("fluorosis", "未实现服务");
        map.put("angora", "未实现服务");
        map.put("insititious", "未实现服务");
        map.put("insincere", "已实现服务");


        map.forEach((key,value)->{
            int keyHash=key.hashCode();
            int idx=(key.length() - 1) & (keyHash ^ (keyHash >>> 16));
            System.err.println(key+" ---> hashcode: "+keyHash+" ---> idx: "+idx);
        });
    }
}
