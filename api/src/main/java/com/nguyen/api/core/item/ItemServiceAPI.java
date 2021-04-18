package com.nguyen.api.core.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemServiceAPI {
    @GetMapping(
            value = "/item",
            produces = "application/json"
    )
    List<Item> getItems(@RequestParam(value = "customerId",required = true)int customerId);
}
