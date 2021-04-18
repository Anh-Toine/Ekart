package com.nguyen.api.core.supplier;

import com.nguyen.api.core.item.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SupplierServiceAPI {
    @GetMapping(
            value = "/supplier",
            produces = "application/json"
    )
    List<Supplier> getSuppliers(@RequestParam(value = "customerId",required = true)int customerId);
}
