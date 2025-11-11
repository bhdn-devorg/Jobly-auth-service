package dev.bhdn.jobly.auth.service.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {
    private static final long CREDENTIAL_EXPIRES_AT = 0L;

    @Value("${dropbox.access.token}")
    private String dropboxAccessToken;

    @Value("${dropbox.app.key}")
    private String dropBoxAppKey;

    @Value("${dropbox.app.secret}")
    private String dropboxAppSecret;

    @Value("${dropbox.refresh.token}")
    private String dropboxRefreshToken;

    @Value("${dropbox.client.id}")
    private String dropboxClientId;

    @Bean
    public DbxClientV2 dbxClientV2() {
        DbxCredential credential = new DbxCredential(
                dropboxAccessToken,
                CREDENTIAL_EXPIRES_AT,
                dropboxRefreshToken,
                dropBoxAppKey,
                dropboxAppSecret
        );

        DbxRequestConfig config = new DbxRequestConfig(dropboxClientId);
        return new DbxClientV2(config, credential);
    }
}
