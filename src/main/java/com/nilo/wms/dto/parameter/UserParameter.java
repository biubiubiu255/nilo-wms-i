package com.nilo.wms.dto.parameter;

import com.nilo.wms.dto.Page;

/**
 * Created by admin on 2018/4/26.
 */
public class UserParameter {

    private String username;

    private String nickname;

    private Page page;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOffset() {
        return this.page.getOffset();
    }

    public int getLimit() {
        return this.page.getLimit();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
