package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:business.properties"})
public interface AppConfig extends Config {
    @Key("url")
    String url();

    @Key("port")
    @DefaultValue("123")
    String port();

    @Key("supported.operations")
    String supportedOperations();

}
