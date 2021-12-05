package zookeeper;

import kafka.admin.RackAwareMode;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import org.apache.kafka.common.utils.Time;
import scala.collection.Iterator;
import scala.collection.Set;

import java.util.Properties;

public class zookeeperTest {
    public static void main(String[] args){
        String zookeeperHost = "localhost:2181";
        Boolean isSecure = false;
        int sessionTimeoutMs = 200000;
        int connectionTimeoutMs = 15000;
        int maxInFlightRequests = 10;
        Time time = Time.SYSTEM;

        // metricGroup, Type의 역할은 무엇인가?, apply 마지막 parameter name은 어떤 값을 넣어야하지?
        String metricGroup = "myGroup";
        String metricType = "myType";
        KafkaZkClient zkClient = KafkaZkClient.apply(zookeeperHost, isSecure, sessionTimeoutMs, connectionTimeoutMs,
                maxInFlightRequests, time, metricGroup, metricType, null);
        AdminZkClient adminZkClient = new AdminZkClient(zkClient);

        String topicName1 = "myTopic";
        int partitions = 3;
        int replication = 1; // you should have replication factor less than or equal to number of nodes in Kafka cluster
        Properties topicConfig = new Properties();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Create Topic
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // RackAwareMode는 뭐지?
        try{
            adminZkClient.createTopic(topicName1,partitions,replication,topicConfig, RackAwareMode.Disabled$.MODULE$);
        }catch(Exception e){
            System.out.println(e);
        }

        // scala의 set, java util의 set과 사용법이 조금 다름
        Set<String> allTopic = zkClient.getAllTopicsInCluster();
        System.out.println("Cluster has " + allTopic.size() + " topics");

        Iterator<String> topicList = allTopic.iterator();

        while(topicList.hasNext()){
            System.out.println("topic : " + topicList.next());
        }
    }
}
