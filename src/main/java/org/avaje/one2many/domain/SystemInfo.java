
package org.avaje.one2many.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Encapsulates various system information such as version and build time.
 * 
 * @author ywang29
 *
 */
@XmlRootElement
public class SystemInfo {

    private String version;
    private String buildNumber;
    private String buildTime;
    private String schemaVersion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

}
