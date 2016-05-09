package com.ccnu.test.couchbase.plain;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gongyb08837 on 2015/11/27.
 */
public class CouchbaseHelper {
    //the IPs / hostnames would be obtained from configuration file
    private static final List<String> SEED_IPS = Arrays.asList("127.0.0.1");

    //the environment configuration
    private static final CouchbaseEnvironment ENV = DefaultCouchbaseEnvironment.builder()
            .connectTimeout(8 * 1000) // 8 Seconds in milliseconds
            .keepAliveInterval(3600 * 1000) // 3600 Seconds in milliseconds
            .build();

    public static  Cluster CLUSTER = CouchbaseCluster.create(ENV, SEED_IPS);

    public static  Bucket DEFAULT_BUCKET = CLUSTER.openBucket("default", null);

    public static void disConnect(){
        CouchbaseHelper.DEFAULT_BUCKET.close();
        CouchbaseHelper.CLUSTER.disconnect();
        CouchbaseHelper.DEFAULT_BUCKET = null;
        CouchbaseHelper.CLUSTER = null;
    }
}
