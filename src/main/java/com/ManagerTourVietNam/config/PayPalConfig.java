package com.ManagerTourVietNam.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "paypal")
public class PayPalConfig {

    private String clientId = "AcJhOJijUOh-8--xpH8aKtr0IinVn3R0FWbz_q7eLG4x4AD0kjK8S77Yiw9OXcuZx99EBrYJFwKOXJt0"; // Thay bằng Client ID của bạn
    private String clientSecret = "EIMH8BCkO227zdGLsnfPl1zUHn246CU6oZiX_EeXxrJ565A_UwvY_7KmXjSHsJLsu-45uZuCt-w8YurU"; // Thay bằng Secret Key của bạn
    private String mode = "sandbox"; // Chọn "sandbox" hoặc "live"

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}
