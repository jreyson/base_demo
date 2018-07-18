package com.ms.base.domain;

/**
 * @author JamelHuang
 * @date 2018/3/7
 */
public class CaptchaTO {
    private Long id;
    private String image_base64;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }
}
