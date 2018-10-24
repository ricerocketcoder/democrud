package com.gary.demo.crud.config;

import com.google.common.base.MoreObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        AppProperties.SwaggerProperties.class
})
public class AppProperties {

    @Autowired
    private SwaggerProperties swaggerProperties;

    public SwaggerProperties getSwaggerProperties() {
        return swaggerProperties;
    }

    @ConfigurationProperties(prefix = "com.gary.demo.crud.swagger")
    public static class SwaggerProperties {
        private String group;
        private String docsTitle;
        private String docsDescription;
        private String supportUrl;
        private String supportEmail;
        private String apiVersion;

        public String getGroup() {
            return group;
        }

        public void setGroup(final String group) {
            this.group = group;
        }

        public String getDocsTitle() {
            return docsTitle;
        }

        public void setDocsTitle(String docsTitle) {
            this.docsTitle = docsTitle;
        }

        public String getDocsDescription() {
            return docsDescription;
        }

        public void setDocsDescription(String docsDescription) {
            this.docsDescription = docsDescription;
        }

        public String getSupportUrl() {
            return supportUrl;
        }

        public void setSupportUrl(String supportUrl) {
            this.supportUrl = supportUrl;
        }

        public String getSupportEmail() {
            return supportEmail;
        }

        public void setSupportEmail(String supportEmail) {
            this.supportEmail = supportEmail;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("group", group)
                    .add("docsTitle", docsTitle)
                    .add("docsDescription", docsDescription)
                    .add("supportUrl", supportUrl)
                    .add("supportEmail", supportEmail)
                    .add("apiVersion", apiVersion)
                    .toString();
        }
    }

}
