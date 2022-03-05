package com.example.demo.dto.menu;


import com.example.demo.model.menu.GroupMenu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
public class GroupMenuRequest {

    private final String title;
    private final String description;
    private final Long storeId;

    public GroupMenuRequest(String title, String description, Long storeId) {
        this.title = title;
        this.description = description;
        this.storeId = storeId;
    }

    public GroupMenu newGroupMenu() {
        return GroupMenu.builder()
                .title(title)
                .description(description)
                .storeId(storeId)
                .build();
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("title", title)
                .append("description", description)
                .append("storeId", storeId)
                .build();
    }
}
