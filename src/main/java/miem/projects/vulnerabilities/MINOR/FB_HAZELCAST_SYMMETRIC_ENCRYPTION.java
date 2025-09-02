package miem.projects.vulnerabilities.MINOR.FB;

import com.hazelcast.config.Config;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class FB_HAZELCAST_SYMMETRIC_ENCRYPTION {

    public void unsafe() {
        com.hazelcast.config.SymmetricEncryptionConfig sec = new com.hazelcast.config.SymmetricEncryptionConfig()
                .setAlgorithm("DES")
                .setKey("s3cr3tKey");
        Config config = new Config();
        config.getNetworkConfig().setSymmetricEncryptionConfig(sec);
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
    }

    public void safe() {
        SSLConfig sslConfig = new SSLConfig()
                .setEnabled(true)
                .setProperty("keyStore", "keystore.jks")
                .setProperty("keyStorePassword", "password");

        Config config = new Config();
        config.getNetworkConfig().setSSLConfig(sslConfig);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
    }
}
