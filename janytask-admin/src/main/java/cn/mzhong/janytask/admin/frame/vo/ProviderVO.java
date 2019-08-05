package cn.mzhong.janytask.admin.frame.vo;

import java.io.Serializable;

public class ProviderVO implements Serializable {

    private static final long serialVersionUID = 2208455634091794914L;
    protected String name;
    protected String url;

    public ProviderVO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProviderVO{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
