package com.nguyen.api.core.supplier;

import com.nguyen.api.core.item.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SupplierServiceAPI {
    @GetMapping(
            value = "/supplier",
            produces = "application/json"
    )
    Supplier getSupplier(@RequestParam(value = "supplierId",required = true)int supplierId);
}
