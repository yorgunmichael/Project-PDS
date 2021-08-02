package episen.backend.server;

public class PropertiesClass {

    private boolean testMode;
    private Integer maxCo;
    private String sqlReq;

    public PropertiesClass() {
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public Integer getMaxCo() {
        return maxCo;
    }

    public void setMaxCo(Integer maxCo) {
        this.maxCo = maxCo;
    }

    public String getSqlReq() {
        return sqlReq;
    }

    public void setSqlReq(String sqlReq) {
        this.sqlReq = sqlReq;
    }
}
