package com.nguyenhangan.k22411csampleproject.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrierUtils {
    private static final Map<String, List<String>> CARRIER_PREFIXES = new HashMap<>();

    static {
        CARRIER_PREFIXES.put("VIETTEL", Arrays.asList(
                "086", "096", "097", "098", "032",
                "033", "034", "035", "036", "037",
                "038", "039"
        ));

        CARRIER_PREFIXES.put("MOBIFONE", Arrays.asList(
                "089", "090", "093", "070", "079",
                "077", "076", "078"
        ));

        CARRIER_PREFIXES.put("VINAPHONE", Arrays.asList(
                "088", "091", "094", "083", "084",
                "085", "081", "082"
        ));
    }

    public static String detectCarrier(String phoneNumber) {
        // Chỉ xóa các ký tự không phải số
        String normalized = phoneNumber.replaceAll("[^0-9]", "");
        // Kiểm tra đầu số
        for (Map.Entry<String, List<String>> entry : CARRIER_PREFIXES.entrySet()) {
            for (String prefix : entry.getValue()) {
                if (normalized.startsWith(prefix)) {
                    return entry.getKey();
                }
            }
        }
        return "OTHER";
    }
}
