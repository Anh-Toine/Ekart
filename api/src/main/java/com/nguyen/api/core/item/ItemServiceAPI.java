package com.nguyen.api.core.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemServiceAPI {
    @GetMapping(
            value = "/item",
            produces = "application/json"
    )
    Item getItem(@RequestParam(value = "itemId",required = true)int itemId);
}
