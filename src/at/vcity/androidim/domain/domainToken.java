package at.vcity.androidim.domain;

/**
 * Created by root on 24/02/16.
 */
public class domainToken {
    String accesToken;
    String tokeType;
    String expiresIn;
    String scope;
    String accessTokenRefresh;

    public String getAccessTokenRefresh() {
        return accessTokenRefresh;
    }

    public void setAccessTokenRefresh(String accessTokenRefresh) {
        this.accessTokenRefresh = accessTokenRefresh;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    public String getTokeType() {
        return tokeType;
    }

    public void setTokeType(String tokeType) {
        this.tokeType = tokeType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
