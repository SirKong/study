package com.ccnu.test.couchbase.plain;

import com.couchbase.client.java.query.Index;
import com.couchbase.client.java.query.dsl.path.index.IndexType;

public class IndexTest {
	public static void main(String[] args) {
//		Index.dropPrimaryIndex(CouchbaseHelper.DEFAULT_BUCKET.name());
//		Index.dropIndex(CouchbaseHelper.DEFAULT_BUCKET.name(), "idxs");
		CouchbaseHelper.DEFAULT_BUCKET.query(Index.createPrimaryIndex()
				.on(CouchbaseHelper.DEFAULT_BUCKET.name()).using(IndexType.GSI)
				.withDefer());
		CouchbaseHelper.DEFAULT_BUCKET.close();
		CouchbaseHelper.CLUSTER.disconnect();
	}
}
