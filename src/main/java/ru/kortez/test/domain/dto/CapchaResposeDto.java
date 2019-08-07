package ru.kortez.test.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CapchaResposeDto {
    private boolean success;
    @JsonAlias("error-codes")
    private Set<String> errorcodes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Set<String> getErrorcodes() {
        return errorcodes;
    }

    public void setErrorcodes(Set<String> errorcodes) {
        this.errorcodes = errorcodes;
    }
}
