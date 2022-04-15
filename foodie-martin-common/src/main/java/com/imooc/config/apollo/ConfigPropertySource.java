package com.imooc.config.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Set;

public class ConfigPropertySource extends EnumerablePropertySource<Config> {
    private static final String[] EMPTY_ARRAY = new String[0];

    ConfigPropertySource(String name, Config source) {
        super(name, source);
    }

    public String[] getPropertyNames() {
        Set<String> propertyNames = ((Config) this.source).getPropertyNames();
        return propertyNames.isEmpty() ? EMPTY_ARRAY : (String[]) propertyNames.toArray(new String[propertyNames.size()]);
    }

    public Object getProperty(String name) {
        return ((Config) this.source).getProperty(name, (String) null);
    }

    public void addChangeListener(ConfigChangeListener listener) {
        ((Config) this.source).addChangeListener(listener);
    }
}
