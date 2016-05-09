package com.couchbase.devguide;

import java.util.concurrent.TimeoutException;

import com.couchbase.client.core.ReplicaNotAvailableException;
import com.couchbase.client.core.ReplicaNotConfiguredException;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.ReplicateTo;
import com.couchbase.client.java.document.JsonStringDocument;
import com.couchbase.client.java.error.DurabilityException;

/**
 * Example of Durability in Java for the Couchbase Developer Guide.
 */
public class Durability extends ConnectionBase {

    @Override
    protected void doWork() {
        String key = "javaDevguideExampleDurability";
        JsonStringDocument doc = JsonStringDocument.create(key, "a String is valid JSON");

        LOGGER.info("Storing with maximum factor");
        // In the Java SDK you must specify a factor matching the number of replicas you have configured
        // if you want "maximum" persistence or replication
        // Here we expect 3 replicas configured so we can wait for persistence on 4 nodes total, replication on 3 replicas.
        try {
            bucket.upsert(doc, PersistTo.FOUR, ReplicateTo.THREE);
        } catch (DurabilityException e) { //if the durability cannot be met
            if (e.getCause() instanceof ReplicaNotConfiguredException) {
                //this exception is a fail fast if not enough replicas are configured on the bucket
                LOGGER.info("Couldn't persist to FOUR nor replicate to THREE, not enough replicas configured");
            } else if (e.getCause() instanceof ReplicaNotAvailableException) {
                //this exception occurs if enough replica are configured on the bucket but currently not enough are online
                //eg. during a failover
                LOGGER.info("Couldn't persist/replicate on 1 replica, not enough replicas online");
            } else {
                LOGGER.error("Durability Exception", e);
            }
        }

        // Store with persisting to master node
        LOGGER.info("Storing with waiting for persistence on MASTER");
        bucket.upsert(doc, PersistTo.MASTER);

        LOGGER.info("Storing with waiting for persistence on any two nodes, replication on one replica node");
        try {
            bucket.upsert(doc, PersistTo.TWO, ReplicateTo.ONE);
        } catch (DurabilityException e) {
            //if the durability cannot be met (eg. if the cluster detected that the replica wasn't available due to failover)
            LOGGER.error("Durability Exception", e);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof TimeoutException) {
                //if one of the nodes isn't responsive, a TimeoutException rather than DurabilityException may occur
                LOGGER.warn("The replica didn't notify us in time");
            }
        }
    }

    public static void main(String[] args) {
        new Durability().execute();
    }
}
