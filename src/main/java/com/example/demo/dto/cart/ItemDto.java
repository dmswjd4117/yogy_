package com.example.demo.dto.cart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDto {


    @NoArgsConstructor
    @Getter
    @Builder
    @AllArgsConstructor
    public static class StoreInfoDto{
        private Long id;
        private String name;
    }

    @NoArgsConstructor
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MenuInfoDto{
        private Long id;
        private String name;
        private Long price;
    }

    @NoArgsConstructor
    @Getter
    @Builder
    @AllArgsConstructor
    public static class OptionInfoDto{
        private Long id;
        private String name;
        private Long price;
    }


    private StoreInfoDto storeInfo;
    private MenuInfoDto menuInfo;
    private List<OptionInfoDto> optionList;
    private int count;
    private Long price;



    @Override
    public String toString(){
        return new ToStringBuilder(this,  ToStringStyle.SHORT_PREFIX_STYLE)
                .append("storeId", storeInfo.getId())
                .append("menuId", menuInfo.getId())
                .append("count", count)
                .append("optionList count", optionList.size())
                .build();
    }
}
